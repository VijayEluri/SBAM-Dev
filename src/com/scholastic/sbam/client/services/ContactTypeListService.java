package com.scholastic.sbam.client.services;

import java.util.List;

import com.extjs.gxt.ui.client.data.LoadConfig;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.scholastic.sbam.shared.objects.ContactTypeInstance;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("getContactTypes")
public interface ContactTypeListService extends RemoteService {
	List<ContactTypeInstance> getContactTypes(LoadConfig loadConfig) throws IllegalArgumentException;
}
