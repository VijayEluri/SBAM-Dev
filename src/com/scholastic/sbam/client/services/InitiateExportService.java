package com.scholastic.sbam.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.scholastic.sbam.shared.objects.ExportProcessReport;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("initiateExport")
public interface InitiateExportService extends RemoteService {
	ExportProcessReport initiateExport(boolean consoleOutputOn) throws IllegalArgumentException;
}
