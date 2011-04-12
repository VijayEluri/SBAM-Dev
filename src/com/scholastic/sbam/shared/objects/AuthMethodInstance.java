package com.scholastic.sbam.shared.objects;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.scholastic.sbam.shared.util.AppConstants;

public class AuthMethodInstance extends BetterRowEditInstance implements BeanModelTag, IsSerializable {

	private static BeanModelFactory beanModelfactory;
	
	public static final String AM_IP 	= "ip";
	public static final String AM_URL	= "url";
	public static final String AM_UID	= "uid";

	private int		agreementId;
	private int		ucn;
	private int		ucnSuffix;
	private String	siteLocCode;
	
	private String	methodType;
	private String	methodKey;
	
	private String	url;
	private	String	userId;
	private String	password;
	private char	userType;
	private long	ipLo;
	private long	ipHi;
	
	private int		proxyId;
	
	private char	remote;
	private char	approved;
	private char	validated;
	private char	activated;

	private Date	activatedDatetime;
	private Date	deactivatedDatetime;
	private Date	reactivatedDatetime;
	
	private String	orgPath;
	
	private String	note;

	private char	status;
	private Date	createdDatetime;
	private Date	updatedDatetime;
	
	private SiteInstance site;
	
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
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public int getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public int getUcn() {
		return ucn;
	}

	public void setUcn(int ucn) {
		this.ucn = ucn;
	}

	public int getUcnSuffix() {
		return ucnSuffix;
	}

	public void setUcnSuffix(int ucnSuffix) {
		this.ucnSuffix = ucnSuffix;
	}

	public String getSiteLocCode() {
		return siteLocCode;
	}

	public void setSiteLocCode(String siteLocCode) {
		this.siteLocCode = siteLocCode;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getMethodKey() {
		return methodKey;
	}

	public void setMethodKey(String methodKey) {
		this.methodKey = methodKey;
	}
	
	public void setMethodKey() {
		if (methodType.equals(AM_IP))
			methodKey = ipLo + ":" + ipHi;
		else if (methodType.equals(AM_URL))
			methodKey = url;
		else
			methodKey = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		setMethodKey();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getUserType() {
		return userType;
	}

	public void setUserType(char userType) {
		this.userType = userType;
	}

	public long getIpLo() {
		return ipLo;
	}

	public void setIpLo(long ipLo) {
		this.ipLo = ipLo;
	}

	public long getIpHi() {
		return ipHi;
	}

	public void setIpHi(long ipHi) {
		this.ipHi = ipHi;
	}

	public int getProxyId() {
		return proxyId;
	}

	public void setProxyId(int proxyId) {
		this.proxyId = proxyId;
	}

	public char getRemote() {
		return remote;
	}

	public void setRemote(char remote) {
		this.remote = remote;
	}

	public char getApproved() {
		return approved;
	}

	public void setApproved(char approved) {
		this.approved = approved;
	}

	public char getValidated() {
		return validated;
	}

	public void setValidated(char validated) {
		this.validated = validated;
	}

	public char getActivated() {
		return activated;
	}

	public void setActivated(char activated) {
		this.activated = activated;
	}

	public Date getActivatedDatetime() {
		return activatedDatetime;
	}

	public void setActivatedDatetime(Date activatedDatetime) {
		this.activatedDatetime = activatedDatetime;
	}

	public Date getDeactivatedDatetime() {
		return deactivatedDatetime;
	}

	public void setDeactivatedDatetime(Date deactivatedDatetime) {
		this.deactivatedDatetime = deactivatedDatetime;
	}

	public Date getReactivatedDatetime() {
		return reactivatedDatetime;
	}

	public void setReactivatedDatetime(Date reactivatedDatetime) {
		this.reactivatedDatetime = reactivatedDatetime;
	}

	public Date getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(Date updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public String getOrgPath() {
		return orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public SiteInstance getSite() {
		return site;
	}

	public void setSite(SiteInstance site) {
		this.site = site;
	}
	
	public String getStatusDescription() {
		return AppConstants.getStatusDescription(status);
	}
	
	public String getMethodDisplay() {
		if (AM_IP.equals(methodType)) {
			if (ipHi == 0 || ipHi == ipLo)
				return getOctetForm(ipLo);
			if (ipLo == 0)
				return getOctetForm(ipHi);
			return getOctetForm(ipLo) + " - " + getOctetForm(ipHi);
		} else if (AM_UID.equals(methodType)) {
			return userId + " / " + password;
		} else if (AM_URL.equals(methodType)) {
			return url;
		}
		return "Unrecognized method type " + methodType;
	}
	
	public void setValuesFrom(AuthMethodInstance fromInstance) {
		this.agreementId				=	fromInstance.agreementId;
		this.ucn						=	fromInstance.ucn;
		this.ucnSuffix					=	fromInstance.ucnSuffix;
		this.siteLocCode				=	fromInstance.siteLocCode;
		
		this.methodType					=	fromInstance.	methodType;
			
		this.url						=	fromInstance.	url;
		this.userId						=	fromInstance.	userId;
		this.password					=	fromInstance.	password;
		this.userType					=	fromInstance.	userType;
		this.ipLo						=	fromInstance.	ipLo;
		this.ipHi						=	fromInstance.	ipHi;
			
		this.proxyId					=	fromInstance.	proxyId;
			
		this.remote						=	fromInstance.	remote;
		this.approved					=	fromInstance.	approved;
		this.validated					=	fromInstance.	validated;
		this.activated					=	fromInstance.	activated;
	
		this.activatedDatetime			=	fromInstance.	activatedDatetime;
		this.deactivatedDatetime		=	fromInstance.	deactivatedDatetime;
		this.reactivatedDatetime		=	fromInstance.	reactivatedDatetime;
			
		this.orgPath					=	fromInstance.	orgPath;
			
		this.note						=	fromInstance.	note;
	
		this.status						=	fromInstance.	status;
		this.createdDatetime			=	fromInstance.	createdDatetime;
		this.updatedDatetime			=	fromInstance.	updatedDatetime;
	}
	
	public String getUniqueKey() {
		return agreementId + ":" + ucn + ":" + ucnSuffix + ":" + siteLocCode + ":" + methodType + ":" + methodKey;
	}
	
	public static String [] [] getIpOctetStrings(long ipLo, long ipHi) {
		int [] lo = getIpOctets(ipLo);
		int [] hi = getIpOctets(ipHi);
		String [] strLo = new String [4];
		String [] strHi = new String [4];
		for (int i = 0; i < 4; i++) {
			strLo [i] = lo [i] + "";
			strHi [i] = hi [i] + "";
		}
		if (lo [0] == hi [0]) {
			if (lo [1] == hi [1]) {
				if (lo [2] == hi [2]) {
					if (lo [3] == hi [3]) {
						blankOctets(strHi);
					} else if (lo [3] == 0 && hi [3] == 255) {
						blankOctets(strHi);
						strLo [3] = "*";
					}
				} else if (lo [2] == 0 && lo [3] == 0 && hi [2] == 255 && hi [3] == 255) {
					blankOctets(strHi);
					strLo [2] = "*";
					strLo [3] = "";
				}
			} else if (lo [1] == 0 && lo [2] == 0 && lo [3] == 0 && hi [1] == 255 && hi [2] == 255 && hi [3] == 255) {
				blankOctets(strHi);
				strLo [1] = "*";
				strLo [2] = "";
				strLo [3] = "";
			}
		}
		
		return new String [] [] {strLo, strHi};
	}
	
	public static void blankOctets(String [] octets) {
		for (int i = 0; i < octets.length; i++)
			octets [i] = "";
	}
	
	public static int [] getIpOctets(long ip) {
		int o1 = (int) (ip % 256);
		ip = ip / 256;
		int o2 = (int) (ip % 256);
		ip = ip / 256;
		int o3 = (int) (ip % 256);
		int o4 = (int) (ip % 256);
		return new int [] {o4, o3, o2, o1};
	}
	
	public static String getOctetForm(int [] octets) {
		return octets [0] + "." + octets [1] + "." + octets [2] + "." + octets [3];
	}
	
	public static String getOctetForm(long ip) {
		return getOctetForm(getIpOctets(ip));
	}

	public static BeanModel obtainModel(AuthMethodInstance instance) {
		if (beanModelfactory == null)
			beanModelfactory  = BeanModelLookup.get().getFactory(AuthMethodInstance.class);
		BeanModel model = beanModelfactory.createModel(instance);
		return model;
	}

	public String toString() {
		return getUniqueKey();
	}
}
