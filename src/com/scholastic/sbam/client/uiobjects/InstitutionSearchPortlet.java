package com.scholastic.sbam.client.uiobjects;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.LiveGridView;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.LiveToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scholastic.sbam.client.services.InstitutionSearchService;
import com.scholastic.sbam.client.services.InstitutionSearchServiceAsync;
import com.scholastic.sbam.client.services.InstitutionWordService;
import com.scholastic.sbam.client.services.InstitutionWordServiceAsync;
import com.scholastic.sbam.client.uiobjects.AppPortletIds;
import com.scholastic.sbam.client.uiobjects.AppSleeper;
import com.scholastic.sbam.client.util.IconSupplier;
import com.scholastic.sbam.shared.exceptions.ServiceNotReadyException;
import com.scholastic.sbam.shared.objects.FilterWordInstance;
import com.scholastic.sbam.shared.objects.InstitutionInstance;

public class InstitutionSearchPortlet extends AppPortlet implements AppSleeper {
	
	protected static final int FILTER_LISTEN_PERIOD = 250;
	
	protected final InstitutionSearchServiceAsync institutionSearchService = GWT.create(InstitutionSearchService.class);
	protected final InstitutionWordServiceAsync   institutionWordService   = GWT.create(InstitutionWordService.class);
	
	protected CardLayout   cards;
	protected ContentPanel searchPanel;
//	protected CardPanel	 searchCard;
	protected FormPanel	 displayCard;
	protected Grid<ModelData> grid;
	protected LiveGridView liveView;
	
	protected ListStore<ModelData> store;
	protected ComboBox<ModelData> combo;
	protected Timer filterListenTimer;
	protected String filter = "";
	
	protected PagingLoader<PagingLoadResult<InstitutionInstance>> institutionLoader;

	protected LabelField ucn;
	protected LabelField address;
	protected LabelField type;
//	protected LabelField group;
	protected LabelField altIds;
	
	public InstitutionSearchPortlet() {
		super(AppPortletIds.FULL_INSTITUTION_SEARCH.getHelpTextId());
	}

	@Override  
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		
		setTitle("Full Institution Search");
		setHeading("Full Institution Search");
		
		setLayout(new FitLayout());
		LayoutContainer outerContainer = new LayoutContainer();
		add(outerContainer);
		
		cards = new CardLayout();
		outerContainer.setLayout(cards);
		
		//	We need this to be able to put the tool bar into the top
		searchPanel = new ContentPanel(new FitLayout());
		searchPanel.setHeaderVisible(false);
		searchPanel.setBorders(false);
		searchPanel.setHeight(540);
		
		setThis();
		addGrid();
		setFilter();
		
		outerContainer.add(searchPanel);
		
		createDisplayCard();
		outerContainer.add(displayCard);
	}
	
	private void createDisplayCard() {
		FormData formData = new FormData("100%");
		displayCard = new FormPanel();

		displayCard.setPadding(40);  
		displayCard.setFrame(true); 
		displayCard.setHeaderVisible(false);  
		displayCard.setBodyBorder(true);
		displayCard.setBodyStyleName("subtle-form");
		displayCard.setButtonAlign(HorizontalAlignment.CENTER);
		displayCard.setLabelAlign(LabelAlign.RIGHT);
		displayCard.setLabelWidth(200);

		//		displayCard.setLayout(new FlowLayout());
		//		displayCard.add(new Html("<b>Let's just display this, okay?</b>"));

		ucn = new LabelField();  
		ucn.setFieldLabel("UCN :");
		displayCard.add(ucn, formData);
		
		address = new LabelField(); 
//		address.setFieldLabel("");
		displayCard.add(address, formData); 
		
		altIds = new LabelField(); 
		altIds.setFieldLabel("Alternate IDs :");
		displayCard.add(altIds, formData); 
		
		type = new LabelField(); 
		type.setFieldLabel("Type :");
		displayCard.add(type, formData); 
		
//		group = new LabelField(); 
//		group.setFieldLabel("");
//		displayCard.add(group, formData); 
		
		Button returnButton = new Button("Return");
		returnButton.addSelectionListener(new SelectionListener<ButtonEvent>() {  
				@Override  
					public void componentSelected(ButtonEvent ce) {
						cards.setActiveItem(searchPanel);
					}  
			});
		displayCard.addButton(returnButton);
	}
	
	protected String plusIfNotEmpty(String value, String prefix) {
		if (value == null || value.length() == 0)
			return "";
		return prefix + value;
	}
	
	protected String brIfNotEmpty(String value) {
		return plusIfNotEmpty(value, "<br/>");
	}
	
	protected String commaIfNotEmpty(String value) {
		return plusIfNotEmpty(value, ", ");
	}
	
	protected String spaceIfNotEmpty(String value) {
		return plusIfNotEmpty(value, "&nbsp;&nbsp;&nbsp;");
	}
	
	protected String brIfNotUsa(String value) {
		if (value == null || value.length() == 0)
			return "";
		if (value.equalsIgnoreCase("USA"))
			return "";
		return "<br/>" + value;
	}
	
	protected void showInstitution(BeanModel model) {
		InstitutionInstance instance = model.getBean();

		ucn.setValue(instance.getUcn());
		address.setValue("<b>" + instance.getInstitutionName() + "</b><br/>" +
				instance.getAddress1() + brIfNotEmpty(instance.getAddress2()) + brIfNotEmpty(instance.getAddress3()) + "<br/>" +
				instance.getCity() + commaIfNotEmpty(instance.getState()) + spaceIfNotEmpty(instance.getZip()) + 
				brIfNotUsa(instance.getCountry()));
		
		if (instance.getAlternateIds() == null || instance.getAlternateIds().length() == 0)
			altIds.setValue("None");
		else
			altIds.setValue(instance.getAlternateIds().replace(",", ", "));
		type.setValue(instance.getGroupDescription() + " &rArr; " + instance.getTypeDescription());
//		group.setValue(instance.getGroupDescription());
		
		cards.setActiveItem(displayCard);
	}
	
	
	protected void setFilter() {
		
		ToolBar toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.LEFT);
		toolBar.getAriaSupport().setLabel("Filters");

		LiveToolItem item = new LiveToolItem();  
		item.bindGrid(grid); 
		toolBar.add(item);  
		
		toolBar.add(new FillToolItem());  
		
		toolBar.add(new LabelToolItem("Filter by: "));
		
		ComboBox<ModelData> filter = getFilterBox();
		filter.getAriaSupport().setLabelledBy(toolBar.getItem(0).getId());
		toolBar.add(filter);  
		
		searchPanel.setTopComponent(toolBar);
	}
	
	protected ComboBox<ModelData> getFilterBox() {

		PagingLoader<PagingLoadResult<FilterWordInstance>> loader = getWordLoader(); 
		
		ListStore<ModelData> wordStore = new ListStore<ModelData>(loader);  
		
		combo = new ComboBox<ModelData>();  
		combo.setWidth(250); 
		combo.setDisplayField("word");  
		combo.setEmptyText("Enter search criteria here...");
		combo.setStore(wordStore);
		combo.setMinChars(1);
		combo.setHideTrigger(true);  
		combo.setPageSize(10);
		combo.setAllowBlank(true);
		combo.setEditable(true);
//		combo.setTypeAhead(true);
		
//		addComboListeners();			// This method sends messages by listening for keypresses
		
		setFilterListenTimer(combo);	// This method sends messages using a timer... it is less responsive, but so bothers the server less, and is a little more reliable
		
		return combo;
	}

//	This method was abandoned, because the key press fires before the combo field value is changed, and change fires only after the user hits tab or return
//	Applying the raw key code in the key press event (with tab, backspace, and such) is too complex to be worth the time.
//	protected void addComboListeners() {
//		combo.addListener(Events.Change, new Listener<FieldEvent>() {
//			public void handleEvent(FieldEvent be) {
//				System.out.println("change to " + be.getValue().toString());
//				loadFiltered(be.getValue().toString());
//			}
//		});
//		
//		combo.addListener(Events.KeyPress, new Listener<FieldEvent>() {
//			public void handleEvent(FieldEvent be) {
//				String value = (be.getField().getRawValue() == null)?"":be.getField().getRawValue().trim();
//				System.out.println("raw value " + value);
//				System.out.println("plus key press " + value);
//				if (be.getField().getRawValue() == null || be.getField().getRawValue().trim().length() == 0)
//					clearInstitutions("Enter filter criteria to search for institutions.");
//				else
//					loadFiltered(be.getField().getRawValue());
//			}
//		});
//	}
	
	protected void setFilterListenTimer(final ComboBox<ModelData> combo) {
		filterListenTimer = new Timer() {
			  @Override
			  public void run() {
				  String value = (combo.getRawValue() == null)?"":combo.getRawValue().trim();
				  if (!value.equals(filter)) {
					  if (!value.equals(filter.trim()))
						  loadFiltered(combo.getRawValue());
					  // else do nothing, the filter hasn't changed
				  }
			  }
			};

			filterListenTimer.scheduleRepeating(FILTER_LISTEN_PERIOD);
	}
	
	protected void addGrid() {
		institutionLoader = getInstitutionLoader();  

//		institutionLoader.addListener(Loader.BeforeLoad, new Listener<LoadEvent>() {  
//		  public void handleEvent(LoadEvent be) {  
//		    BasePagingLoadConfig m = be.<BasePagingLoadConfig> getConfig();  
//		    m.set("start", m.get("offset"));  
//		    m.set("ext", "js");  
//		    m.set("lightWeight", true);  
//		    m.set("sort", (m.get("sortField") == null) ? "" : m.get("sortField"));  
//		    m.set("dir", (m.get("sortDir") == null || (m.get("sortDir") != null && m.<SortDir> get("sortDir").equals(  
//		        SortDir.NONE))) ? "" : m.get("sortDir"));  
//
//		  }  
//		});  
		institutionLoader.setSortDir(SortDir.ASC);  
		institutionLoader.setSortField("institutionName");  

		institutionLoader.setRemoteSort(true);  

		store = new ListStore<ModelData>(institutionLoader);  
 
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  
		   
		columns.add(getHiddenColumn("ucn",		"UCN", 		80));  
		ColumnConfig name = new ColumnConfig("institutionName", "Name", 250);  
		name.setRenderer(new GridCellRenderer<ModelData>() {  

		  public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,  
		      ListStore<ModelData> store, Grid<ModelData> grid) {  
		    return "<b>"  
		        + model.get("institutionName")  
		        + "</b>";  
		  }  

		});  
		columns.add(name);  
		columns.add(new ColumnConfig("address1",			"Street",			150));  
		columns.add(new ColumnConfig("city",				"City",				100));  
		columns.add(new ColumnConfig("state",				"State",			30));   
		columns.add(new ColumnConfig("zip",					"Zip",				50));      
		columns.add(getHiddenColumn("typeCode",				"Type Code", 		50));
		columns.add(getHiddenColumn("typeDescription",		"Type", 			100));    
		columns.add(getHiddenColumn("groupCode",			"Type Group Code", 	50));  
		columns.add(getHiddenColumn("groupDescription",		"Type Group", 		100)); 
		columns.add(getHiddenColumn("alternateIds",			"Alternate IDs", 	100));    

//		ColumnConfig last = new ColumnConfig("lastpost", "Last Post", 200);  
//		last.setRenderer(new GridCellRenderer<ModelData>() {  
//
//		  public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,  
//		      ListStore<ModelData> store, Grid<ModelData> grid) {  
//		    return model.get("lastpost") + "<br/>by " + model.get("lastposter");  
//		  }  
//
//		});  
//		columns.add(last);  

		ColumnModel cm = new ColumnModel(columns);  

		grid = new Grid<ModelData>(store, cm);  
		grid.setBorders(true);  
		grid.setAutoExpandColumn("institutionName");  
		grid.setLoadMask(true);  
		grid.setStripeRows(true);
		
		//	Switch to the display card when a row is selected
		grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);  
		grid.getSelectionModel().addListener(Events.SelectionChange,  
				new Listener<SelectionChangedEvent<ModelData>>() {  
					public void handleEvent(SelectionChangedEvent<ModelData> be) {  
						if (be.getSelection().size() > 0) {
							showInstitution((BeanModel) be.getSelectedItem());
						} 
					}  
			});  

		liveView = new LiveGridView();  
		liveView.setEmptyText("Enter filter criteria to search for institutions.");
//		liveView.setRowHeight(32);
		grid.setView(liveView);
//		grid.setHeight(550);
		grid.getAriaSupport().setLabelledBy(this.getHeader().getId() + "-label"); 
		searchPanel.add(grid);   
		  
	}
	
	protected ColumnConfig getHiddenColumn(String column, String heading, int size) {
		ColumnConfig cc = new ColumnConfig(column,		heading, 		size);
		cc.setHidden(true);
		return cc;
	}
	
	protected void setThis() {
//		this.setFrame(true);  
//		this.setCollapsible(true);  
//		this.setAnimCollapse(false);  
//		this.setIcon(Resources.ICONS.table()); 
		this.setLayout(new FitLayout());
		this.setHeight(550);
		IconSupplier.setIcon(this, IconSupplier.getInstitutionIconName());
//		this.setSize(grid.getWidth() + 50, 400);  
	}
	
//	/**
//	 * Clear the contents of the grid, and display the live text message.  NOT WORKING
//	 * @param message
//	 */
//	protected void clearInstitutions(String message) {
//		liveView.setEmptyText(message);
//		filter=(combo.getRawValue() == null)?"":combo.getRawValue();
//	//	liveView.refresh();
//		store.removeAll();
//		store.commitChanges();
//		liveView.refresh(false);	// THIS WON'T CLEAR THE GRID!!!  WHY NOT?
//	}
	
	/**
	 * Instigate an asynchronous load with a filter value.
	 * @param filter
	 */
	protected void loadFiltered(String filter) {
		this.filter = filter;
		institutionLoader.load();
	}
	
	/**
	 * Construct and return a loader to handle returning a list of institutions.
	 * @return
	 */
	protected PagingLoader<PagingLoadResult<InstitutionInstance>> getInstitutionLoader() {
		// proxy and reader  
		RpcProxy<PagingLoadResult<InstitutionInstance>> proxy = new RpcProxy<PagingLoadResult<InstitutionInstance>>() {  
			@Override  
			public void load(Object loadConfig, final AsyncCallback<PagingLoadResult<InstitutionInstance>> callback) {
		    	
				// This could be as simple as calling userListService.getUsers and passing the callback
				// Instead, here the callback is overridden so that it can catch errors and alert the users.  Then the original callback is told of the failure.
				// On success, the original callback is just passed the onSuccess message, and the response (the list).
				
				AsyncCallback<PagingLoadResult<InstitutionInstance>> myCallback = new AsyncCallback<PagingLoadResult<InstitutionInstance>>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						if (caught instanceof IllegalArgumentException)
							MessageBox.alert("Alert", caught.getMessage(), null);
						else if (caught instanceof ServiceNotReadyException)
								MessageBox.alert("Alert", "The " + caught.getMessage() + " is not available at this time.  Please try again in a few minutes.", null);
						else {
							MessageBox.alert("Alert", "Institution load failed unexpectedly.", null);
							System.out.println(caught.getClass().getName());
							System.out.println(caught.getMessage());
						}
						callback.onFailure(caught);
					}

					public void onSuccess(PagingLoadResult<InstitutionInstance> result) {
						if ( result.getData() == null || result.getData().size() == 0 ) {
							if (result.getTotalLength() > 0)
								liveView.setEmptyText(result.getTotalLength() + " institutions qualify (too many to display).<br/>Please enter filter criteria to narrow your search.");
							else if (filter.length() == 0)
								liveView.setEmptyText("Enter filter criteria to search for institutions.");
							else
								liveView.setEmptyText("Please enter filter criteria to narrow your search.");
						}
						callback.onSuccess(result);
					}
				};

				institutionSearchService.getInstitutions((PagingLoadConfig) loadConfig, filter, myCallback);
				
		    }  
		};
		BeanModelReader reader = new BeanModelReader();
		
		// loader and store  
		PagingLoader<PagingLoadResult<InstitutionInstance>> loader = new BasePagingLoader<PagingLoadResult<InstitutionInstance>>(proxy, reader);
		return loader;
	}
	
	/**
	 * Construct and return a loader to return a list of words.
	 * 
	 * @return
	 */
	protected PagingLoader<PagingLoadResult<FilterWordInstance>> getWordLoader() {
		// proxy and reader  
		RpcProxy<PagingLoadResult<FilterWordInstance>> proxy = new RpcProxy<PagingLoadResult<FilterWordInstance>>() {  
			@Override  
			public void load(Object loadConfig, final AsyncCallback<PagingLoadResult<FilterWordInstance>> callback) {
		    	
				// This could be as simple as calling userListService.getUsers and passing the callback
				// Instead, here the callback is overridden so that it can catch errors and alert the users.  Then the original callback is told of the failure.
				// On success, the original callback is just passed the onSuccess message, and the response (the list).
				
				AsyncCallback<PagingLoadResult<FilterWordInstance>> myCallback = new AsyncCallback<PagingLoadResult<FilterWordInstance>>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						if (caught instanceof IllegalArgumentException)
							MessageBox.alert("Alert", caught.getMessage(), null);
						else if (caught instanceof ServiceNotReadyException)
								MessageBox.alert("Alert", "The " + caught.getMessage() + " is not available at this time.  Please try again in a few minutes.", null);
						else {
							MessageBox.alert("Alert", "Word load failed unexpectedly.", null);
							System.out.println(caught.getClass().getName());
							System.out.println(caught.getMessage());
						}
						callback.onFailure(caught);
					}

					public void onSuccess(PagingLoadResult<FilterWordInstance> result) {
						callback.onSuccess(result);
					}
				};

				institutionWordService.getInstitutionWords((PagingLoadConfig) loadConfig, myCallback);
				
		    }  
		};
		BeanModelReader reader = new BeanModelReader();
		
		// loader and store  
		PagingLoader<PagingLoadResult<FilterWordInstance>> loader = new BasePagingLoader<PagingLoadResult<FilterWordInstance>>(proxy, reader);
		return loader;
	}

	/**
	 * Go to sleep when collapsed.
	 */
	@Override
	public void onCollapse() {
		super.onCollapse();
		sleep();
	}
	
	/**
	 * Wake up when expanded.
	 */
	@Override
	public void onExpand() {
		super.onExpand();
		awaken();
	}
	
	/**
	 * Turn on the listener timer when waking up.
	 */
	@Override
	public void awaken() {
		if (this.isExpanded())
			if (filterListenTimer != null)
				filterListenTimer.scheduleRepeating(FILTER_LISTEN_PERIOD);
	}

	/**
	 * Turn off the listener timer when going to sleep.
	 */
	@Override
	public void sleep() {
		if (filterListenTimer != null) {
			filterListenTimer.cancel();
		}
	}

}