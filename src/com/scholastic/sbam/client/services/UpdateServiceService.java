package com.scholastic.sbam.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.scholastic.sbam.shared.objects.UpdateResponse;
import com.scholastic.sbam.shared.objects.ServiceInstance;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("updateService")
public interface UpdateServiceService extends RemoteService {
	UpdateResponse<ServiceInstance> updateService(ServiceInstance instance) throws IllegalArgumentException;
}
