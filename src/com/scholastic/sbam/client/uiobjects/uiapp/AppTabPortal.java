package com.scholastic.sbam.client.uiobjects.uiapp;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.scholastic.sbam.shared.objects.UserPortletCacheInstance;

/**
 * A "portal" with a tab layout.
 * 
 * To maintain a similarity to a real Portal, with columns, this tab layout also lays it's tabs out, at least organizationally, in columns
 * 
 * @author Bob Lacatena
 *
 */
public class AppTabPortal  extends Composite implements AppPortletPresenter {
	
	protected int 					nextPortalId = 0;
	protected TabPanel 				tabPanel;
	protected List<List<TabItem>>	portletTabs	=	new ArrayList<List<TabItem>>();
	
	public AppTabPortal() {
		tabPanel = new TabPanel();
		tabPanel.setTabScroll(true);
		
		initComponent(tabPanel);
	}

	@Override
	public void add(Portlet portlet, int column) {
		insert(portlet, 0, column);
	}

	@Override
	public void insert(Portlet portlet, int index, int column) {
		int portletId = nextPortalId++;
		if (reinsert(portlet, index, column, portletId)) {
			if (portlet instanceof AppPortlet) {
				AppPortlet appPortlet = (AppPortlet) portlet;
				appPortlet.registerUserPortlet(portletId, index, column);
			}
			scrollToPortlet(portlet);
		}
	}

	@Override
	public void scrollToPortlet(Portlet portlet) {
		TabItem tab = getTab(portlet);
		if (tab != null)
			tabPanel.setSelection(tab);
	}

	@Override
	public boolean reinsert(Portlet portlet, int index, int column, int portletId) {
		//	Do any AppPortlet specific tasks
		if (portlet instanceof AppPortlet) {
			AppPortlet appPortlet = (AppPortlet) portlet;
			//	Make sure there's not already a duplicate portlet, and if so, don't add this one, just jump to that one
			AppPortlet existingPortlet = (AppPortlet) getByIdentity( appPortlet.getPortletIdentity() );
			if (existingPortlet != null && !existingPortlet.allowDuplicatePortlets()) {
				scrollToPortlet(existingPortlet);
				return false;
			}
			//	Otherwise, set the id and stuff
			appPortlet.setPortletId(portletId);
			appPortlet.setPresenter(this);
		}
		
		if (index < 0)
			index = 0;
		if (column < 0)
			column = 0;
		
		//	Get the tab we'll add (with the portlet in it)
		TabItem tab = getNewPortletTab(portlet, index, column);
		
		//	Create all the columns we need
		while (portletTabs.size() <= column) {
			portletTabs.add(new ArrayList<TabItem>());
		}
		
		//	Get the column we'll add to
		List<TabItem> tabColumn = portletTabs.get(column);
		
		//	Now we'll add the tab to the proper column, and to the display
		
		//	If this is at the end of the column, the tab has to go before the first item in the next column
		if (index >= tabColumn.size()) {
			//	Append to end of the column
			tabColumn.add(tab);
			
			//	Find the next actual tab item
			TabItem nextTab = null;
			int nextColumn = column + 1;

			while (nextTab == null && nextColumn < portletTabs.size()) {
				//	Insert before first tab in next column
				List<TabItem> nextTabColumn = portletTabs.get(nextColumn);
				nextColumn++;
				if (nextTabColumn.size() > 0) {
					nextTab = nextTabColumn.get(0);
					break;
				}
			}
			
			//	Add the tab
			if (nextTab == null) {
				//	No next column, just add to end
				tabPanel.add(tab);
			} else {
				tabPanel.insert(tab, tabPanel.indexOf(nextTab));
			}
		} else {
			//	Insert before the specified tab in this column
			TabItem beforeTab = tabColumn.get(index);
			if (beforeTab != null) {
				tabColumn.add(index, tab);
				int tabIndex = tabPanel.indexOf(beforeTab);
				if (tabIndex < 0)
					tabIndex = 0;
				tabPanel.insert(tab, tabIndex);
			} else {
				tabColumn.add(0, tab);
				tabPanel.insert(tab, 0);
			}
		}
		
		//	Lastly, set the row and column for the portlet, and fix the local next portlet ID
		if (portlet instanceof AppPortlet) {
			AppPortlet appPortlet = (AppPortlet) portlet;

			// Set the tooltip for the tab
			tab.getHeader().setToolTip( appPortlet.getPresenterToolTip() );
		//	appPortlet.registerUserPortlet(appPortlet.getPortletId(), index, column);
			appPortlet.setPortalColumn(column);
			appPortlet.setPortalRow(index);
			if (appPortlet.getPortletId() >= nextPortalId)
				nextPortalId = appPortlet.getPortletId() + 1;
		}
		
		return true;
	}
	
	public TabItem getNewPortletTab(Portlet portlet, int index, int column) {
		TabItem tab = new TabItem(getTabLabel(portlet, index, column));
		tab.setLayout(new FitLayout());
		tab.setClosable(true);
		tab.setScrollMode(Scroll.NONE);
		tab.add(portlet);
		
		if (portlet instanceof AppPortlet) {
			final AppPortlet appPortlet = (AppPortlet) portlet;
			tab.addListener(Events.Close, new Listener<BaseEvent>() {
	            public void handleEvent(BaseEvent be)
	            {
	            	if (be.getType().getEventCode() == Events.Close.getEventCode()) {
	            		appPortlet.closePortlet();
	            	}
	            };
	        });
		}
		
		return tab;
	}
	
	public String getTabLabel(Portlet portlet, int index, int column) {
		String label = null;
		if (portlet instanceof AppPortlet)
			label = ( (AppPortlet) portlet).getShortPortletName();
		else
			label = portlet.getHeading();
		if (label != null && label.length() > 0)
			return label;
		return index + "-" + column + " : ";
	}
	
	@Override
	public void close(Portlet portlet) {
		TabItem tab = getTab(portlet);
		if (tab != null) {
			if (tab.getTabPanel() != null)
				tab.close();
		
			//	Take the tab out of the list of tabs
			for (List<TabItem> column : portletTabs) {
				for (TabItem targetTab : column) {
					if (tab.hashCode() == targetTab.hashCode()) {
						column.remove(targetTab);
						break;
					}
				}
			}
		}
		
		//	Make sure the portlet is out of the tab itself
		if (portlet.getParent() != null)
			portlet.removeFromParent();
		
		//	Update the portlet as "closed" in the user cache
		if (portlet instanceof AppPortlet)
			((AppPortlet) portlet).updateUserPortlet();
	}
	
//	private void dumpTabLists() {
//		System.out.println("=========== portletTabs");
//		for (List<TabItem> column : portletTabs) {
//			System.out.println("Column...");
//			for (TabItem tab : column) {
//				System.out.println("Tab: " + tab.getText());
//			}
//		}
//		System.out.println("========================");
//	}
	
	@Override
	public void updateLabel(Portlet portlet) {
		TabItem tab = getTab(portlet);
		if (tab != null) {
			tab.setText(getTabLabel(portlet, 0, 0));
			if (portlet instanceof AppPortlet)
				tab.getHeader().setToolTip( ( (AppPortlet) portlet).getPresenterToolTip() );
		}
	}

	@Override
	public void refreshAllPortletStates() {
		for (int col = 0; col < portletTabs.size(); col++) {
		    List<TabItem> list = new ArrayList<TabItem>(portletTabs.get(col));
		    for (int row = 0; row < list.size(); row++) {
		    	TabItem tab = list.get(row);
		    	if (tab.getItemCount() > 0 && tab.getItem(0) instanceof AppPortlet) {
					AppPortlet appPortlet = (AppPortlet) tab.getItem(0);
					appPortlet.updateUserPortlet(row, col);
		    	}
		    }
		}
	}
	
	/**
	 * Remove all portlets (without recording them as closed in the user portlet cache)
	 */
	public void removeAllPortlets() {
		for (int col = 0; col < portletTabs.size(); col++) {
		    List<TabItem> list = new ArrayList<TabItem>(portletTabs.get(col));
		    for (int row = 0; row < list.size(); row++) {
		    	TabItem tab = list.get(row);
		    	if (tab.getItemCount() > 0 && tab.getItem(0) instanceof AppPortlet) {
					AppPortlet appPortlet = (AppPortlet) tab.getItem(0);
					tab.remove(appPortlet);
		    	}
				tabPanel.remove(tab);
		    }
		}
		portletTabs = new ArrayList<List<TabItem>>();
	}
	
	public TabItem getTab(Portlet portlet) {
		//	Search using the internal list, in case it's already been removed from the tabPanel by GXT
		for (List<TabItem> tabs : portletTabs) {
			for (TabItem tab : tabs) {
				for (Component component : tab.getItems()) {
					if (component.hashCode() == portlet.hashCode())
						if (component.getClass().getName().equals(portlet.getClass().getName())) {
							return tab;
						}
				}
			}
		}

		return null;
	}

	@Override
	public void restorePresentationState(List<UserPortletCacheInstance> list) {
		// Nothing to do
	}

	@Override
	public Portlet getByIdentity(String identity) {
		if (identity == null)
			return null;
		
		for (List<TabItem> tabs : portletTabs) {
			for (TabItem tab : tabs) {
				for (Component component : tab.getItems()) {
					if (component instanceof AppPortlet) {
						AppPortlet appPortlet = (AppPortlet) component;
						if (identity.equals(appPortlet.getPortletIdentity()))
							return appPortlet;
					}
				}
			}
		}
		
		return null;
	}

	/**
	 * Widths do not matter to this presenter.
	 */
	@Override
	public boolean updatePortletCacheWidths() {
		return false;
	}
}
