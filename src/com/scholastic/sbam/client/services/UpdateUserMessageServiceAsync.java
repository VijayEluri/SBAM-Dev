package com.scholastic.sbam.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scholastic.sbam.shared.objects.UserMessageInstance;

public interface UpdateUserMessageServiceAsync {

	void updateUserMessage(UserMessageInstance instance, AsyncCallback<Integer> callback);

}
