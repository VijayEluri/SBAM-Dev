package com.scholastic.sbam.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.scholastic.sbam.shared.objects.UpdateResponse;
import com.scholastic.sbam.shared.objects.RemoteSetupUrlInstance;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("updateRemoteSetupUrl")
public interface UpdateRemoteSetupUrlService extends RemoteService {
	UpdateResponse<RemoteSetupUrlInstance> updateRemoteSetupUrl(RemoteSetupUrlInstance instance) throws IllegalArgumentException;
}
