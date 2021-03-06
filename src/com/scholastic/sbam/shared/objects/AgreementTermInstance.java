package com.scholastic.sbam.shared.objects;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.scholastic.sbam.shared.util.AppConstants;

public class AgreementTermInstance extends BetterRowEditInstance implements BeanModelTag, IsSerializable {

	private static BeanModelFactory beanModelfactory;

	private int		agreementId;
	private int		termId;
	
	private String	productCode;
	private Date	startDate;
	private Date	endDate;
	private Date	terminateDate;
	private String	termTypeCode;
	
	private String	commissionCode;

	private String	cancelReasonCode;
	private Date	cancelDate;
	
	private double	dollarValue;
	private int		workstations;
	private int		buildings;
	private int		population;
	private int		enrollment;
	
	private String	poNumber;
	private int		referenceSaId;
	
	private String	orgPath;
	private String	primaryOrgPath;
	private char	primary;
	private boolean primaryTerm;
	
	private String	note;
	
	private char	status;
	private boolean	active;
	private Date	createdDatetime;
	
	private TermTypeInstance		termType;
	private ProductInstance			product;
	private CancelReasonInstance	cancelReason;
	private CommissionTypeInstance	commissionType;
	
	@Override
	public void markForDeletion() {
		setStatus('X');
	}

	@Override
	public boolean thisIsDeleted() {
		return status == 'X';
	}

	@Override
	public boolean thisIsValid() {
		return true;
	}

	@Override
	public String returnTriggerProperty() {
		return "junk";
	}

	@Override
	public String returnTriggerValue() {
		return "junk";
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
		this.active = (this.status == 'A');
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		if (this.status == 'X')
			return;
		setStatus(active?'A':'I');
	}

	public int getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public int getTermId() {
		return termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getTerminateDate() {
		return terminateDate;
	}

	public void setTerminateDate(Date terminateDate) {
		this.terminateDate = terminateDate;
	}

	public String getTermTypeCode() {
		if (termType != null)
			return termType.getTermTypeCode();
		return termTypeCode;
	}

	public void setTermTypeCode(String termTypeCode) {
		this.termTypeCode = termTypeCode;
	}

	public String getCommissionCode() {
		return commissionCode;
	}

	public void setCommissionCode(String commissionCode) {
		this.commissionCode = commissionCode;
	}

	public String getCancelReasonCode() {
		return cancelReasonCode;
	}

	public void setCancelReasonCode(String cancelReasonCode) {
		this.cancelReasonCode = cancelReasonCode;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public double getDollarValue() {
		return dollarValue;
	}

	public void setDollarValue(double dollarValue) {
		this.dollarValue = dollarValue;
	}

	public int getWorkstations() {
		return workstations;
	}

	public void setWorkstations(int workstations) {
		this.workstations = workstations;
	}

	public int getBuildings() {
		return buildings;
	}

	public void setBuildings(int buildings) {
		this.buildings = buildings;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(int enrollment) {
		this.enrollment = enrollment;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public int getReferenceSaId() {
		return referenceSaId;
	}

	public void setReferenceSaId(int referenceSaId) {
		this.referenceSaId = referenceSaId;
	}

	public String getOrgPath() {
		return orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}

	public String getPrimaryOrgPath() {
		return primaryOrgPath;
	}

	public void setPrimaryOrgPath(String primaryOrgPath) {
		this.primaryOrgPath = primaryOrgPath;
	}

	public char getPrimary() {
		return primary;
	}

	public void setPrimary(char primary) {
		this.primary = primary;
		this.primaryTerm = (primary == 'y');
	}

	public boolean isPrimaryTerm() {
		return primaryTerm;
	}

	public void setPrimaryTerm(boolean primaryTerm) {
		setPrimary(primaryTerm ? 'y' : 'n');
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getStatusDescription() {
		return AppConstants.getStatusDescription(status);
	}

	public TermTypeInstance getTermType() {
		return termType;
	}

	public void setTermType(TermTypeInstance termType) {
		this.termType = termType;
		if (termType == null)
			this.termTypeCode = "";
		else
			this.termTypeCode = termType.getTermTypeCode();
	}

	public CancelReasonInstance getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(CancelReasonInstance cancelReason) {
		this.cancelReason = cancelReason;
		if (this.cancelReason == null)
			this.cancelReasonCode = "";
		else
			this.cancelReasonCode = cancelReason.getCancelReasonCode();
	}

	public ProductInstance getProduct() {
		return product;
	}

	public void setProduct(ProductInstance product) {
		this.product = product;
		if (this.product == null)
			this.productCode = "";
		else
			this.productCode = product.getProductCode();
	}

	public CommissionTypeInstance getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(CommissionTypeInstance commissionType) {
		this.commissionType = commissionType;
		if (this.commissionType == null)
			this.commissionCode = "";
		else
			this.commissionCode = commissionType.getCommissionCode();
	}
	
	public boolean deliverService() {
		return deliverService(new Date());
	}
	
	public boolean deliverService(Date testDate) {
		if (startDate == null)
			return false;
		if (endDate == null)
			return false;
		if (cancelReasonCode != null && cancelReasonCode.length() > 0)
			return false;
		if (startDate.after(testDate))
			return false;
		if (!endDate.after(testDate))
			return false;
		return true;
	}
	
	public void setValuesFrom(AgreementTermInstance fromInstance) {
		
		this.termType			=	fromInstance.termType;
		this.product			=	fromInstance.product;
		this.cancelReason		=	fromInstance.cancelReason;
		this.commissionType		=	fromInstance.commissionType;

		this.agreementId		=	fromInstance.agreementId;
		this.termId				=	fromInstance.termId;

		this.productCode		=	fromInstance.productCode;
		this.startDate			=	fromInstance.startDate;
		this.endDate			=	fromInstance.endDate;
		this.terminateDate		=	fromInstance.terminateDate;
		this.termTypeCode		=	fromInstance.termTypeCode;

		this.commissionCode		=	fromInstance.commissionCode;

		this.cancelReasonCode	=	fromInstance.cancelReasonCode;
		this.cancelDate			=	fromInstance.cancelDate;

		this.dollarValue		=	fromInstance.dollarValue;
		this.workstations		=	fromInstance.workstations;
		this.buildings			=	fromInstance.buildings;
		this.population			=	fromInstance.population;
		this.enrollment			=	fromInstance.enrollment;

		this.poNumber			=	fromInstance.poNumber;
		this.referenceSaId		=	fromInstance.referenceSaId;

		this.orgPath			=	fromInstance.orgPath;
		this.primaryOrgPath		=	fromInstance.primaryOrgPath;
		this.primary			=	fromInstance.primary;
		this.primaryTerm		=	fromInstance.primaryTerm;

		this.note				=	fromInstance.note;

		this.status				=	fromInstance.status;
		this.active				=	fromInstance.active;
		this.createdDatetime	=	fromInstance.createdDatetime;
	}
	
	public String getUniqueKey() {
		return agreementId + ":" + termId;
	}

	public static AgreementTermInstance getEmptyInstance() {
		AgreementTermInstance instance = new AgreementTermInstance();
		instance.setNewRecord(true);
		return instance;
	}

	public static BeanModel obtainModel(AgreementTermInstance instance) {
		if (beanModelfactory == null)
			beanModelfactory  = BeanModelLookup.get().getFactory(AgreementTermInstance.class);
		BeanModel model = beanModelfactory.createModel(instance);
		return model;
	}

	public String toString() {
		return "Term " + agreementId + "-" + termId;
	}
}
