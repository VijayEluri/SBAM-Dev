package com.scholastic.sbam.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.scholastic.sbam.shared.objects.UpdateResponse;
import com.scholastic.sbam.shared.objects.ContactInstance;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("updateContactNote")
public interface UpdateContactNoteService extends RemoteService {
	UpdateResponse<ContactInstance> updateContactNote(ContactInstance instance) throws IllegalArgumentException;
}
