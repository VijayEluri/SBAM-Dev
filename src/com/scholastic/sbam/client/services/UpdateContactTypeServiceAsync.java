package com.scholastic.sbam.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scholastic.sbam.shared.objects.UpdateResponse;
import com.scholastic.sbam.shared.objects.ContactTypeInstance;

public interface UpdateContactTypeServiceAsync {

	void updateContactType(ContactTypeInstance beanModel, AsyncCallback<UpdateResponse<ContactTypeInstance>> callback);

}
