package com.scholastic.sbam.client.uiobjects.fields;

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
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scholastic.sbam.client.services.InstitutionSearchService;
import com.scholastic.sbam.client.services.InstitutionSearchServiceAsync;
import com.scholastic.sbam.client.uiobjects.uiapp.AppPortletIds;
import com.scholastic.sbam.client.uiobjects.uiapp.AppPortletProvider;
import com.scholastic.sbam.client.uiobjects.uiapp.InstitutionSearchPortlet;
import com.scholastic.sbam.client.util.IconSupplier;
import com.scholastic.sbam.shared.exceptions.ServiceNotReadyException;
import com.scholastic.sbam.shared.objects.InstitutionInstance;
import com.scholastic.sbam.shared.objects.SimpleKeyProvider;
import com.scholastic.sbam.shared.objects.SynchronizedPagingLoadResult;
import com.scholastic.sbam.shared.util.AppConstants;

public class InstitutionSearchField extends ComboBox<BeanModel> {
	
	protected final InstitutionSearchServiceAsync institutionSearchService = GWT.create(InstitutionSearchService.class);
	
	protected AppPortletProvider		appPortletProvider;
	
	private long					searchSyncId	=	0;

	private String					sortField		=	"institutionName";
	private SortDir					sortDir			=	SortDir.ASC;
	
	protected int					agreementId		=	0;
	
	protected Button				openButton		=	null;
	
	public InstitutionSearchField() {
		super();
		
		PagingLoader<PagingLoadResult<InstitutionInstance>> loader = getInstitutionLoader(); 
		
		ListStore<BeanModel> institutionStore = new ListStore<BeanModel>(loader);
		institutionStore.setKeyProvider(new SimpleKeyProvider("ucn"));
		
		this.setWidth(300);
		this.setValueField("ucn");
		this.setDisplayField("nameAndUcn");  
		this.setEmptyText("Enter institution search criteria here...");
		this.setStore(institutionStore);
		this.setMinChars(1);
		this.setHideTrigger(true); 
		this.setTriggerStyle("trigger-square"); 
		this.setPageSize(200);
		this.setAllowBlank(false);
		this.setEditable(true);
		this.setSimpleTemplate(getMultiLineAddressTemplate());
		
		this.addSelectionChangedListener(new SelectionChangedListener<BeanModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<BeanModel> se) {
				if (se.getSelectedItem() == null) {
					if (openButton != null)
						openButton.disable();
				} else {
					InstitutionInstance selected = (InstitutionInstance) se.getSelectedItem().getBean();
					if (openButton != null)
						openButton.setEnabled(selected.getUcn() > 0);
				}
			}
			
		});
	}
	
	@Override
	public boolean isDirty() {
		if (disabled || !rendered) {
	    	return false;
	    }
	    return !equalWithNull();
	}

	/**
	 * Like Util.equalWithNull, but if the values are ModelData with a Store and a KeyProvider, use the key values provided.
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public boolean equalWithNull() {
		if (getSelectedValue() == originalValue) {
			return true;
		} else if (getSelectedValue() == null) {
			return false;
		} else if (getSelectedValue() instanceof ModelData && originalValue instanceof ModelData && this.getStore() != null && this.getStore().getKeyProvider() != null) {
	    	if (originalValue == null)
	    		return true;
	    	String key1 = this.getStore().getKeyProvider().getKey(getSelectedValue());
	    	String key2 = this.getStore().getKeyProvider().getKey(originalValue);
	    	return (key1.equals(key2));
	    } else {
	    	return getSelectedValue().equals(originalValue);
	    }
	}
	
//	public void dumpValues(String tag) {
//		if (originalValue == null) System.out.println(tag + ": Original null"); else System.out.println(tag + ": Original " + originalValue.getProperties());
//		if (value == null) System.out.println(tag + ": Value null"); else System.out.println(tag + ": Value " + value.getProperties());
//	}
	
	public BeanModel getSelectedValue() {
		return value;
	}
	
	public InstitutionInstance getSelectedInstitution() {
		if (value != null)
			return (InstitutionInstance) value.getBean();
		return null;
	}
	
	public void onBlur(ComponentEvent ce) {
		super.onBlur(ce);
		if (this.value == null) {
			this.value = this.originalValue;
			if (this.value == null)
				setRawValue("");
			else
				setRawValue(this.value.get(this.getDisplayField()).toString());
		}
	}
	
	@Override
	public void onSelect(BeanModel model, int index) {
		if (model != null) {
			InstitutionInstance instance = model.getBean();
			if (instance.getStatus() == AppConstants.STATUS_ERROR) {
				//	Can't select the error message
				return;
			}
		}
		super.onSelect(model, index);
	}
	
	protected String getMultiLineAddressTemplate() {
		return "<div class=\"{listStyle}\"><span class=\"show-name\">{institutionName}</span><span class=\"show-ucn\"> {ucn}</span><span class=\"show-address\"><br/>{htmlAddress}</span></div>"; // {address1}<br/>{city}, {state} &nbsp;&nbsp;&nbsp; {zip}";
	}
	
	public void openInstitutionPortlet(InstitutionInstance instance) {
		if (instance == null)
			return;
		openInstitutionPortlet(instance.getUcn());
	}
	
	public void openInstitutionPortlet(int ucn) {
		if (appPortletProvider == null)
			return;
		InstitutionSearchPortlet portlet = (InstitutionSearchPortlet) appPortletProvider.getPortlet(AppPortletIds.FULL_INSTITUTION_SEARCH);
		portlet.setFocusUcn(ucn);
		appPortletProvider.addPortlet(portlet);
	}

	
	public Button createOpenButton() {
		if (openButton != null)
			return openButton;
		
		openButton = new Button();
		IconSupplier.forceIcon(openButton, IconSupplier.getGoOpenIconName());
		openButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				if (getSelectedInstitution() != null && getSelectedInstitution().getUcn() > 0) {
					openInstitutionPortlet(getSelectedInstitution());
				}
			}
			
		});
		
		final int WIDTH  = 24;
		final int HEIGHT = 22;
		openButton.setWidth(WIDTH);
		openButton.setHeight(HEIGHT);
		openButton.setPixelSize(WIDTH, HEIGHT);
		
		return openButton;
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
				
				AsyncCallback<SynchronizedPagingLoadResult<InstitutionInstance>> myCallback = new AsyncCallback<SynchronizedPagingLoadResult<InstitutionInstance>>() {
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

					public void onSuccess(SynchronizedPagingLoadResult<InstitutionInstance> syncResult) {
						if(syncResult.getSyncId() != searchSyncId)
							return;
						
						PagingLoadResult<InstitutionInstance> result = syncResult.getResult();
						
						//	This code was left, in case some way is determined to display this information when a search returns too many or no results
						if ( result.getData() == null || result.getData().size() == 0 ) {
							if (result.getTotalLength() > 0) {
								result.getData().add(InstitutionInstance.getErrorInstance(result.getTotalLength() + " institutions qualify.<br/>Please narrow your search."));
								result.setTotalLength(1);
							}
						}
//								liveView.setEmptyText(result.getTotalLength() + " institutions qualify (too many to display).<br/>Please enter filter criteria to narrow your search.");
//							else if (filter.length() == 0)
//								liveView.setEmptyText("Enter filter criteria to search for institutions.");
//							else
//								liveView.setEmptyText("Please enter filter criteria to narrow your search.");
//						}
						callback.onSuccess(result);
					}
				};
				
				( (PagingLoadConfig) loadConfig).set("sortField",	sortField);
				( (PagingLoadConfig) loadConfig).set("sortDir",		sortDir);
				
				searchSyncId = System.currentTimeMillis();
				institutionSearchService.getInstitutions((PagingLoadConfig) loadConfig, getQueryValue(loadConfig), false, searchSyncId, myCallback);
				
		    }  
		};
		BeanModelReader reader = new BeanModelReader();
		
		// loader and store  
		PagingLoader<PagingLoadResult<InstitutionInstance>> loader = new BasePagingLoader<PagingLoadResult<InstitutionInstance>>(proxy, reader);
		return loader;
	}
	
	public String getQueryValue(Object loadConfig) {
		String query = (loadConfig != null && loadConfig instanceof PagingLoadConfig) ? ((PagingLoadConfig) loadConfig).get("query").toString() : null;
		if (query == null)
			query = getRawValue();
		return query;
	}

	public AppPortletProvider getAppPortletProvider() {
		return appPortletProvider;
	}

	public void setAppPortletProvider(AppPortletProvider appPortletProvider) {
		this.appPortletProvider = appPortletProvider;
	}

	public Button getOpenButton() {
		return createOpenButton();
	}

	public void setOpenButton(Button openButton) {
		this.openButton = openButton;
	}

	public int getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public SortDir getSortDir() {
		return sortDir;
	}

	public void setSortDir(SortDir sortDir) {
		this.sortDir = sortDir;
	}
	
}
