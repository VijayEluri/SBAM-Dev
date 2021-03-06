package com.scholastic.sbam.client.uiobjects.fields;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Container;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;

/**
 * A field set with the ability to be enabled or disabled separately from it's component fields.
 * 
 * Component fields are always disabled when the field set is collapsed.
 * 
 * Component fields are enabled independently using enableFields methods.
 * 
 * There is also a (redundant) locking mechanism to keep fields from being enabled.
 * 
 * So, to enable fields, the field set must be:
 * 	1. enabled
 * 	2. expanded
 *  3. unlocked
 *  4. have fields enabled
 *  
 * @author Bob Lacatena
 *
 */
public class LockableFieldSet extends FieldSet {
	protected boolean locked;
	protected boolean enableFields;
	protected boolean disableButtons	=	false;
	
	@Override
	public void enable() {
	    if (rendered) {
	        onEnable();
	    }
	    disabled = false;
	    fireEvent(Events.Enable);

		enableFields();
	}
	
	@Override
	public void onExpand() {
		super.onExpand();;
		enableFields();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		if (enabled)
			enable();
		else
			disable();
	}
	
	@Override
	public void disable() {
		super.disable();
		setEnabledFields(this, false);
	}
	
	@Override
	public void onCollapse() {
		super.onCollapse();
		setEnabledFields(this, false);
//		for (Component component : this.getItems()) {
//			if (component instanceof Field) {
//				Field<?> field = (Field<?>) component;
//				field.disable();
//			}
//		}
	}
	
	public void markLocked(boolean locked) {
		this.locked = locked;
	}
	
	public void lock() {
		locked = true;
		setEnabledFields(this, false);
//		for (Component component : this.getItems()) {
//			if (component instanceof Field) {
//				Field<?> field = (Field<?>) component;
//				field.disable();
//			}
//		}
	}

	public void unlock() {
		locked = false;
		enableFields();
	}
	
	public void setLocked(boolean locked) {
		if (locked)
			lock();
		else
			unlock();
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public void enableFields() {
		if (enableFields && !locked && isExpanded()) {
			setEnabledFields(this, true);
		}
	}
	
	public void setEnabledFields(Container<?> comp, boolean enabled) {
		for (Object component : comp.getItems()) {
			if (component instanceof Field) {
				Field<?> field = (Field<?>) component;
				field.setEnabled(enabled);
			} else if (disableButtons && component instanceof Button) {
				((Button) component).setEnabled(enabled);
			} else if (component instanceof Container) {
				setEnabledFields((Container<?>) component, enabled);
			}
		}
	}
	
	public void dumpStatus() {
		System.out.println("....." + this.getId() + " enableFields " + enableFields + ", locked " + locked + ", expanded " + isExpanded() + ", enabled " + isEnabled()); 
		
		for (Component component : this.getItems()) {
			if (component instanceof Field) {
				Field<?> field = (Field<?>) component;
				System.out.println("....." + field.getId() + " : enabled " + field.isEnabled() + ", read only " + field.isReadOnly());
			}
		}
	}
	
	public void enableFields(boolean enableFields) {
		this.enableFields = enableFields;
		if (enableFields)
			enableFields();
		else
			setEnabledFields(this, false);
	}
	
	public boolean canEnableFields() {
		return enableFields;
	}

	public boolean isDisableButtons() {
		return disableButtons;
	}

	public void setDisableButtons(boolean disableButtons) {
		this.disableButtons = disableButtons;
	}
	
}
