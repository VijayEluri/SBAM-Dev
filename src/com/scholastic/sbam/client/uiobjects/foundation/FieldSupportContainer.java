package com.scholastic.sbam.client.uiobjects.foundation;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.google.gwt.i18n.client.NumberFormat;
import com.scholastic.sbam.client.uiobjects.fields.BoundDateField;
import com.scholastic.sbam.client.uiobjects.fields.BoundSliderField;
import com.scholastic.sbam.client.uiobjects.fields.EnhancedCheckBoxGroup;
import com.scholastic.sbam.client.uiobjects.fields.EnhancedComboBox;
import com.scholastic.sbam.client.uiobjects.fields.SliderFieldWithDisable;
import com.scholastic.sbam.client.util.AddressFormatter;

/**
 * A base class which provides useful helper methods for any portlet that will be using fields.
 * 
 * Classes include methods for creating grid columns and for constructing addresses in HTML.
 * 
 * @author Bob Lacatena
 *
 * @param <I>
 */
public abstract class FieldSupportContainer extends LayoutContainer {
	
	public FieldSupportContainer() {
		super();
	}
	
	protected FormPanel getNewOuterFormPanel() {
		FormPanel newFormPanel = new FormPanel();
		
		newFormPanel.setFrame(false); // true
		newFormPanel.setHeaderVisible(false);  
		newFormPanel.setBodyBorder(false);	// true
		newFormPanel.setBorders(false);
		newFormPanel.setBodyStyleName("subtle-form");
		newFormPanel.setButtonAlign(HorizontalAlignment.CENTER);
		newFormPanel.setLabelAlign(LabelAlign.RIGHT);
		newFormPanel.setLabelWidth(80);
		
		return newFormPanel;
	}
	
//	protected FormInnerPanel getNewInnerFormPanel() {
//		FormInnerPanel newFormPanel = new FormInnerPanel();
//		
//		newFormPanel.setFrame(false); // true
//		newFormPanel.setHeaderVisible(false);  
//		newFormPanel.setBodyBorder(false);	// true
//		newFormPanel.setBorders(false);
//		newFormPanel.setBodyStyleName("subtle-form");
//		newFormPanel.setButtonAlign(HorizontalAlignment.CENTER);
//		newFormPanel.setLabelAlign(LabelAlign.RIGHT);
//		newFormPanel.setLabelWidth(80);
//		
//		return newFormPanel;
//	}
	
	protected FormInnerPanel getNewFormInnerPanel() {
		return getNewFormInnerPanel(75);
	}
	
	protected FormInnerPanel getNewFormInnerPanel(int labelWidth) {
		return getNewFormInnerPanel(false, labelWidth);
	}
	
	protected FormInnerPanel getNewFormInnerPanel(boolean outerForm) {
		return getNewFormInnerPanel(outerForm, 75);
	}
	
	protected FormInnerPanel getNewFormInnerPanel(boolean outerForm, int labelWidth) {
		FormInnerPanel newFormPanel = new FormInnerPanel(outerForm);
		
		newFormPanel.setFrame(false); // true
		newFormPanel.setHeaderVisible(false);  
		newFormPanel.setBodyBorder(false);	// true
		newFormPanel.setBorders(false);
		newFormPanel.setBodyStyleName("subtle-form");
		newFormPanel.setButtonAlign(HorizontalAlignment.CENTER);
		newFormPanel.setLabelAlign(LabelAlign.RIGHT);
		newFormPanel.setLabelWidth(labelWidth);
		
		return newFormPanel;
	}

	protected String plusIfNotEmpty(String value, String prefix) {
		return AddressFormatter.plusIfNotEmpty(value, prefix);
	}
	
	protected String brIfNotEmpty(String value) {
		return AddressFormatter.brIfNotEmpty(value);
	}
	
	protected String commaIfNotEmpty(String value) {
		return AddressFormatter.commaIfNotEmpty(value);
	}
	
	protected String spaceIfNotEmpty(String value) {
		return AddressFormatter.spaceIfNotEmpty(value);
	}
	
	protected String brIfNotUsa(String value) {
		return AddressFormatter.brIfNotUsa(value);
	}
	
	protected LabelField getLabelField() {
		return FieldFactory.getLabelField();
	}
	
	protected LabelField getLabelField(int width) {
		return FieldFactory.getLabelField(width);
	}
	
	protected NumberField getDollarField(String label) {
		return FieldFactory.getDollarField(label);
	}
	
	protected NumberField getIntegerField(String label) {
		return FieldFactory.getIntegerField(label);
	}
	
	protected NumberField getIntegerField(String label, int width) {
		return FieldFactory.getIntegerField(label, width);
	}
	
	protected NumberField getNumberField(String label, NumberFormat numberFormat) {
		return FieldFactory.getNumberField(label, numberFormat);
	}
	
	protected static EnhancedCheckBoxGroup getCheckBoxGroup(String label, CheckBox... boxes) {
		return FieldFactory.getCheckBoxGroup(label, boxes);
	}
	
	protected static CheckBox	getCheckBoxField(String label) {
		return FieldFactory.getCheckBoxField(label);
	}
	
	protected static TextArea getMultiLineField(String label, int lines) {
		return FieldFactory.getMultiLineField(label, lines);
	}
	
	protected TextField<String> getTextField(String label) {
		return FieldFactory.getTextField(label);
	}
	
	protected DateField getDateField(String label) {
		return FieldFactory.getDateField(label);
	}
	
	protected BoundDateField getBoundDateField(String label) {
		return FieldFactory.getBoundDateField(label);
	}
	
	protected SliderFieldWithDisable getSliderField(String label) {
		return FieldFactory.getSliderField(label);
	}
	
	protected BoundSliderField getBoundSliderField(String label) {
		return FieldFactory.getBoundSliderField(label);
	}
	
	protected EnhancedComboBox<BeanModel> getComboField(String name, String label, int width, ListStore<BeanModel> listStore, String displayField) {
		return FieldFactory.getComboField(name, label, width, listStore, displayField);
	}
	
	protected EnhancedComboBox<BeanModel> getComboField(String name, String label, int width, String toolTip, ListStore<BeanModel> listStore, String displayField) {
		return FieldFactory.getComboField(name, label, width, toolTip, listStore, displayField);
	}
	
	protected EnhancedComboBox<BeanModel> getComboField(String name, String label, int width, String toolTip, ListStore<BeanModel> listStore, String valueField, String displayField) {
		return FieldFactory.getComboField(name, label, width, toolTip, listStore, valueField, displayField);
	}
	
	public abstract void awaken();
	
	public abstract void sleep();

}