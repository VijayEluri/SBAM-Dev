package com.scholastic.sbam.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.scholastic.sbam.shared.objects.UpdateResponse;
import com.scholastic.sbam.shared.objects.PreferenceCodeInstance;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("updatePreferenceCode")
public interface UpdatePreferenceCodeService extends RemoteService {
	UpdateResponse<PreferenceCodeInstance> updatePreferenceCode(PreferenceCodeInstance instance) throws IllegalArgumentException;
}
