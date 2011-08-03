package com.scholastic.sbam.server.reporting;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.hibernate.criterion.Order;

import com.scholastic.sbam.server.database.codegen.CancelReason;
import com.scholastic.sbam.server.database.codegen.CommissionType;
import com.scholastic.sbam.server.database.codegen.Institution;
import com.scholastic.sbam.server.database.codegen.InstitutionCountry;
import com.scholastic.sbam.server.database.codegen.InstitutionState;
import com.scholastic.sbam.server.database.codegen.Product;
import com.scholastic.sbam.server.database.codegen.Service;
import com.scholastic.sbam.server.database.codegen.Snapshot;
import com.scholastic.sbam.server.database.codegen.SnapshotParameter;
import com.scholastic.sbam.server.database.codegen.SnapshotProductService;
import com.scholastic.sbam.server.database.codegen.SnapshotTermData;
import com.scholastic.sbam.server.database.codegen.TermType;
import com.scholastic.sbam.server.database.codegen.User;
import com.scholastic.sbam.server.database.objects.DbCancelReason;
import com.scholastic.sbam.server.database.objects.DbCommissionType;
import com.scholastic.sbam.server.database.objects.DbInstitution;
import com.scholastic.sbam.server.database.objects.DbInstitutionCountry;
import com.scholastic.sbam.server.database.objects.DbInstitutionState;
import com.scholastic.sbam.server.database.objects.DbProduct;
import com.scholastic.sbam.server.database.objects.DbService;
import com.scholastic.sbam.server.database.objects.DbSnapshot;
import com.scholastic.sbam.server.database.objects.DbSnapshotParameter;
import com.scholastic.sbam.server.database.objects.DbSnapshotProductService;
import com.scholastic.sbam.server.database.objects.DbSnapshotTermData;
import com.scholastic.sbam.server.database.objects.DbTermType;
import com.scholastic.sbam.server.database.objects.DbUser;
import com.scholastic.sbam.server.database.util.HibernateUtil;
import com.scholastic.sbam.shared.objects.Authentication;
import com.scholastic.sbam.shared.objects.CancelReasonInstance;
import com.scholastic.sbam.shared.objects.CommissionTypeInstance;
import com.scholastic.sbam.shared.objects.InstitutionCountryInstance;
import com.scholastic.sbam.shared.objects.InstitutionInstance;
import com.scholastic.sbam.shared.objects.InstitutionStateInstance;
import com.scholastic.sbam.shared.objects.ProductInstance;
import com.scholastic.sbam.shared.objects.ServiceInstance;
import com.scholastic.sbam.shared.objects.SnapshotInstance;
import com.scholastic.sbam.shared.objects.SnapshotParameterValueObject;
import com.scholastic.sbam.shared.objects.SnapshotTermDataInstance;
import com.scholastic.sbam.shared.objects.TermTypeInstance;
import com.scholastic.sbam.shared.reporting.SnapshotParameterNames;
import com.scholastic.sbam.shared.util.AppConstants;

public class SnapshotExcelWorkbookMaker {
	
	//	Excel lookup =VLOOKUP(B2,Sheet1!$A$1:$B$22,2)
	
	protected static final int		DATE_WIDTH				= 12;
	protected static final int		DOLLAR_WIDTH			= 12;
	protected static final int		CODE_WIDTH				= 12;
	protected static final int		DESCRIPTION_WIDTH		= 30;
	protected static final int		CITY_WIDTH				= 20;
	protected static final int		STATE_WIDTH				= 6;
	protected static final int		COUNTRY_WIDTH			= 12;
	
	protected static final String	EXCEL_TEXT_FORMAT		=	"text";
	protected static final String	EXCEL_CURRENCY_FORMAT	=	BuiltinFormats.getBuiltinFormat(6);	//	"$#,##0.00;[Red]($#,##0.00)";	//	or "#,##0.00"
	protected static final String	EXCEL_FRACTION_FORMAT	=	"0.00%";
	protected static final String	EXCEL_NUMBER_FORMAT		=	"#,##0";
	protected static final String	EXCEL_INTEGER_FORMAT	=	"0";
	protected static final String	EXCEL_LONG_DATE_FORMAT	=	"d-mmm-yy";
	protected static final String	EXCEL_DATE_TIME_FORMAT	=	"m/d/yy h:mm";
	
	protected static final short	TEXT_FORMAT				=	HSSFDataFormat.getBuiltinFormat(EXCEL_TEXT_FORMAT);
	protected static final short	CURRENCY_FORMAT			=	HSSFDataFormat.getBuiltinFormat(EXCEL_CURRENCY_FORMAT);
	protected static final short	FRACTION_FORMAT			=	HSSFDataFormat.getBuiltinFormat(EXCEL_FRACTION_FORMAT);
	protected static final short	NUMBER_FORMAT			=	HSSFDataFormat.getBuiltinFormat(EXCEL_NUMBER_FORMAT);
	protected static final short	INTEGER_FORMAT			=	HSSFDataFormat.getBuiltinFormat(EXCEL_INTEGER_FORMAT);
	protected static final short	DATE_FORMAT				=	HSSFDataFormat.getBuiltinFormat(EXCEL_LONG_DATE_FORMAT);
	protected static final short	DATE_TIME_FORMAT		=	HSSFDataFormat.getBuiltinFormat(EXCEL_DATE_TIME_FORMAT);
	
	protected static final String	CELL_CODES				=	"ABCDEFGHIJKLMNOPQRSTUVWXYZ";	
	
	protected static final short	BACKGROUND_1_COLOR		=	HSSFColor.GREY_25_PERCENT.index;
	protected static final short	BACKGROUND_2_COLOR		=	HSSFColor.GREY_40_PERCENT.index;
	protected static final short	HEADER_COLOR			=	HSSFColor.GREY_50_PERCENT.index;
	
	protected int					snapshotId;
	protected SnapshotInstance		snapshot;
	
	protected boolean				useLookups				=	true;
	
	protected HSSFWorkbook			wb						=	new HSSFWorkbook();
	
	protected HSSFSheet				dataSheet				=	wb.createSheet("Snapshot Term Data");
	protected HSSFSheet				coverSheet				=	wb.createSheet("Snapshot Parameters");
	protected HSSFSheet				institutionSheet		=	wb.createSheet("Institutions");
	protected HSSFSheet				productSheet			=	wb.createSheet("Products");
	protected HSSFSheet				serviceSheet			=	wb.createSheet("Services");
	
	protected final HSSFFont		BOLD_FONT				=	createBoldFont();
	
	protected final HSSFCellStyle	TEXT_STYLE				=	createTextCellStyle(TEXT_FORMAT);	//	createTextStyle();
	protected final HSSFCellStyle	BOLD_STYLE				=	createBoldStyle();
	protected final HSSFCellStyle	HEADER_STYLE			=	createHeaderStyle();
	protected final HSSFCellStyle	BOLD_RIGHT_STYLE		=	createBoldRightStyle();
	protected final HSSFCellStyle	TEXT_FILL_1_STYLE		=	createTextFillStyle(BACKGROUND_1_COLOR);
	protected final HSSFCellStyle	TEXT_FILL_2_STYLE		=	createTextFillStyle(BACKGROUND_2_COLOR);
	protected final HSSFCellStyle	DATE_STYLE				=	createCellStyle(DATE_FORMAT);		//	createDateStyle();
	protected final HSSFCellStyle	DATE_TIME_STYLE			=	createCellStyle(DATE_TIME_FORMAT);	//	createDateTimeStyle();
	protected final HSSFCellStyle	FRACTION_STYLE			=	createCellStyle(FRACTION_FORMAT);	//	createFractionStyle();
	protected final HSSFCellStyle	NUMBER_STYLE			=	createCellStyle(NUMBER_FORMAT);		//	createNumberStyle();
	protected final HSSFCellStyle	INTEGER_STYLE			=	createCellStyle(INTEGER_FORMAT);	//	createIntegerStyle();
	protected final HSSFCellStyle	CURRENCY_STYLE			=	createCellStyle(CURRENCY_FORMAT);	//	createCurrencyStyle();
	
	protected int					rowNum					=	0;

	protected int					productCodeCell			=	0;
	protected int					serviceCodeCell			=	0;
	protected int					ucnCell					=	0;
	
	//	These maps are used so that we'll reuse all instances, to be sure to conserve space in serialization
	
	protected TreeMap<Integer, InstitutionInstance>			institutionMap		= new TreeMap<Integer, InstitutionInstance>();
	protected TreeMap<String, ProductInstance>				productMap			= new TreeMap<String, ProductInstance>();
	protected TreeMap<String, ServiceInstance>				serviceMap			= new TreeMap<String, ServiceInstance>();
	protected TreeMap<String, CancelReasonInstance>			cancelReasonMap 	= new TreeMap<String, CancelReasonInstance>();
	protected TreeMap<String, TermTypeInstance> 			termTypeMap			= new TreeMap<String, TermTypeInstance>();
	protected HashMap<String, CommissionTypeInstance>		commissionTypeMap	= new HashMap<String, CommissionTypeInstance>();
	protected HashMap<String, InstitutionStateInstance>		institutionStateMap	= new HashMap<String, InstitutionStateInstance>();
	protected HashMap<String, InstitutionCountryInstance>	institutionCountryMap	= new HashMap<String, InstitutionCountryInstance>();
	
	public SnapshotExcelWorkbookMaker(int snapshotId) {
		this.snapshotId = snapshotId;
	}
	
	public HSSFWorkbook getWorkbook() throws ServletException {
		if (wb.getSummaryInformation() == null 
		||  wb.getSummaryInformation().getAuthor() == null 
		||  wb.getSummaryInformation().getAuthor().length() == 0)
			populateWorkbook();
		
		return wb;
	}
	
	public void populateWorkbook() throws ServletException {
		setInfo();
		
		createCustomPalette();
		
		populateCoverSheet();
		
		startDataSheet();
		
		processTermData();
		
		addLookupSheets();
	}
	
	protected void setInfo() {
		wb.createInformationProperties();
		wb.getSummaryInformation().setTitle("Snapshot Name");
		wb.getSummaryInformation().setAuthor("SBAM Application Generated Spreadsheet");
	}
	
	protected void createCustomPalette() {
		HSSFPalette palette = wb.getCustomPalette();

		palette.setColorAtIndex(HEADER_COLOR, 		(byte) 210,	(byte) 210,	(byte) 210);
		palette.setColorAtIndex(BACKGROUND_1_COLOR, (byte) 255,	(byte) 245,	(byte) 235);
		palette.setColorAtIndex(BACKGROUND_2_COLOR, (byte) 230,	(byte) 220,	(byte) 210);
	}
	
	protected HSSFFont createBoldFont() {
		HSSFFont thisFont = wb.createFont();
		
		thisFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		return thisFont;
	}
	
	protected HSSFCellStyle createTextCellStyle(short style) {
		HSSFCellStyle thisStyle = wb.createCellStyle();
		
		thisStyle.setDataFormat(style);
		thisStyle.setWrapText(true);
		
		return thisStyle;
	}
	
	protected HSSFCellStyle createCellStyle(short style) {
		HSSFCellStyle thisStyle = wb.createCellStyle();
		
		thisStyle.setDataFormat(style);
		
		return thisStyle;
	}
	
	protected HSSFCellStyle createTextStyle() {
		HSSFCellStyle thisStyle = wb.createCellStyle();
		
		thisStyle.setDataFormat(TEXT_FORMAT);
		thisStyle.setWrapText(true);
		
		return thisStyle;
	}
	
	protected HSSFCellStyle createBoldStyle() {
		HSSFCellStyle thisStyle = createTextStyle();

		thisStyle.setFont(BOLD_FONT);
		
		return thisStyle;
	}
	
	protected HSSFCellStyle createHeaderStyle() {
		HSSFCellStyle thisStyle = createTextStyle();

		thisStyle.setFont(BOLD_FONT);
		thisStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		thisStyle.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
		thisStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		thisStyle.setFillForegroundColor(HEADER_COLOR);
		
		return thisStyle;
	}
	
	protected HSSFCellStyle createBoldRightStyle() {
		HSSFCellStyle thisStyle = createBoldStyle();
		
		thisStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		
		return thisStyle;
	}
	
	protected HSSFCellStyle createTextFillStyle(short color) {
		HSSFCellStyle thisStyle = createTextStyle();

		thisStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		thisStyle.setFillForegroundColor(color);
		
		return thisStyle;
	}
	
	protected void populateCoverSheet() throws ServletException {
		int coverRow = 0;
		
		setCoverSheetWidths();
		
		snapshot = getSnapshot(); 
		coverRow = addCoverCell(coverRow, "Snapshot ID", snapshotId + "  " + snapshot.getSnapshotName());
		coverRow = addCoverCell(coverRow, "Compiled", snapshot.getSnapshotTaken() + "");
		coverRow = addCoverCell(coverRow, "Rows Selected", snapshot.getSnapshotRows() + "");
		coverRow = addCoverCell(coverRow, "Created By", snapshot.getCreateDisplayName());
		
		if (snapshot.getNote() != null && snapshot.getNote().length() > 0) {
			coverRow++;	//	Blank line
			coverRow = addCoverCell(coverRow, "Notes", snapshot.getNote());
		}
		
		coverRow++;	//	Blank line
		
		coverRow = addParameters(snapshot, coverRow);
	}
	
	protected void setCoverSheetWidths() {
		for (int i = 0; i < 30; i++)
			coverSheet.setColumnWidth(i, getCharacterWidth(30));
	}
	
	protected SnapshotInstance getSnapshot() throws ServletException {
		Snapshot dbSnapshot = null;
		User     dbUser		= null;
		
		HibernateUtil.openSession();
		HibernateUtil.startTransaction();

		try {
			
			//	Get existing
			dbSnapshot = DbSnapshot.getById(snapshotId);
			if (dbSnapshot == null)
				throw new ServletException("Snapshot " + snapshotId + " not found.");
			
			if (dbSnapshot.getStatus() != AppConstants.STATUS_ACTIVE)
				if (dbSnapshot.getStatus() == AppConstants.STATUS_COMPILING)	
					throw new ServletException("INTERNAL SAFETY CHECK FAILED: Snapshot is currently compiling.");
				else	
					throw new ServletException("INTERNAL SAFETY CHECK FAILED: Invalid snapshot status " + dbSnapshot.getStatus() + ".");
			
			if (dbSnapshot.getSnapshotTaken() == null)
				throw new ServletException("INTERNAL SAFETY CHECK FAILED: Snapshot has not been taken.");
			
			dbUser = DbUser.getById(dbSnapshot.getCreateUserId());

		} finally {
			if (HibernateUtil.isTransactionInProgress())
				HibernateUtil.endTransaction();
			HibernateUtil.closeSession();
		}
		
		SnapshotInstance snapshot = DbSnapshot.getInstance(dbSnapshot);	
		if (dbUser != null) {
			snapshot.setCreateDisplayName(Authentication.getDisplayName(dbUser.getFirstName(), dbUser.getLastName()));
		} else {
			snapshot.setCreateDisplayName("Unknown creator " + dbSnapshot.getCreateUserId());
		}
		
		return snapshot;
	}
	
	protected int addCoverCell(int coverRow, String... cellContent) {
		HSSFRow row = coverSheet.createRow(coverRow);
		
		for (int cellNum = 0; cellNum < cellContent.length; cellNum++) {
			HSSFCell cell = row.createCell(cellNum);
			cell.setCellValue(cellContent [cellNum]);
			if (cellNum == 0)
				cell.setCellStyle(BOLD_RIGHT_STYLE);
			else
				cell.setCellStyle(TEXT_STYLE);
		}
		
		return ++coverRow;
	}
	
	protected int addCoverCell(int coverRow, String label, Date... cellContent) {
		HSSFRow row = coverSheet.createRow(coverRow);
		
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(BOLD_RIGHT_STYLE);
		cell.setCellValue(label);
		
		for (int cellNum = 1; cellNum < cellContent.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(cellContent [cellNum]);
			cell.setCellStyle(DATE_TIME_STYLE);
		}
		
		return ++coverRow;
	}
	
	public int addParameters(SnapshotInstance snapshot, int coverRow) throws ServletException {

		HibernateUtil.openSession();
		HibernateUtil.startTransaction();
		
		try {
			coverRow = addOtherParameters(snapshot, coverRow);
			coverRow = addProductParameters(snapshot, coverRow);
			coverRow = addServiceParameters(snapshot, coverRow);
			
			return coverRow;
		} finally {		
			HibernateUtil.endTransaction();
			HibernateUtil.closeSession();
		}
	}
	
	public int addOtherParameters(SnapshotInstance snapshot, int coverRow) throws ServletException {
		
		HSSFRow row = null;
		
		int cellNum = 0;
		
		String lastParameterName = null;
		
		List<SnapshotParameter> parameterValues = DbSnapshotParameter.findBySource(snapshotId, null);
		for (SnapshotParameter parameterValue : parameterValues) {
			
			//	Skip these... they aren't handled the same way
			if (parameterValue.getId().getParameterName().equals(SnapshotParameterNames.PRODUCT_CODE))
				continue;
			
			if (parameterValue.getId().getParameterName().equals(SnapshotParameterNames.SERVICE_CODE))
				continue;
			
			if (lastParameterName == null || !lastParameterName.equalsIgnoreCase(parameterValue.getId().getParameterName())) {
				row = coverSheet.createRow(coverRow++);
				
				cellNum = 0; 
				
				HSSFCell cell = row.createCell(cellNum++);
				cell.setCellStyle(BOLD_RIGHT_STYLE);
				cell.setCellValue(SnapshotParameterNames.getLabel(parameterValue.getId().getParameterName()));
				
				lastParameterName = parameterValue.getId().getParameterName();
			}

			if (parameterValue.getParameterType() == SnapshotParameterValueObject.INTEGER) {
				if (parameterValue.getIntFromValue() == null)
					throw new ServletException("Null value found for " + parameterValue.getId().getParameterName() + " for snapshot " + snapshotId + ".");
				if (parameterValue.getIntToValue() != null && parameterValue.getIntToValue().intValue() > parameterValue.getIntFromValue().intValue())
					addCell(row, cellNum++, parameterValue.getIntFromValue() + "<==>" + parameterValue.getIntToValue());
				else
					addCell(row, cellNum++, parameterValue.getIntFromValue());
				
			} else if (parameterValue.getParameterType() == SnapshotParameterValueObject.DOUBLE) {
				if (parameterValue.getDblFromValue() == null)
					throw new ServletException("Null value found for " + parameterValue.getId().getParameterName() + " for snapshot " + snapshotId + ".");
				if (parameterValue.getDblToValue().doubleValue() > parameterValue.getDblFromValue().doubleValue())
					addCell(row, cellNum++, parameterValue.getDblFromValue() + "<==>" + parameterValue.getDblToValue());
				else
					addCell(row, cellNum++, parameterValue.getDblFromValue());
				
			} else if (parameterValue.getParameterType() == SnapshotParameterValueObject.DATE) {
				if (parameterValue.getDateFromValue() == null)
					throw new ServletException("Null value found for " + parameterValue.getId().getParameterName() + " for snapshot " + snapshotId + ".");
				if (parameterValue.getDateToValue() != null && parameterValue.getDateToValue().after(parameterValue.getDateFromValue()))
					addCell(row, cellNum++, parameterValue.getDateFromValue() + "<==>" + parameterValue.getDateToValue());
				else
					addCell(row, cellNum++, parameterValue.getDateFromValue());
				
			} else if (parameterValue.getParameterType() == SnapshotParameterValueObject.STRING) {
				if (parameterValue.getStrFromValue() == null)
					throw new ServletException("Null value found for " + parameterValue.getId().getParameterName() + " for snapshot " + snapshotId + ".");
				if (parameterValue.getStrToValue() != null && parameterValue.getStrToValue().compareTo(parameterValue.getStrFromValue()) > 0)
					addCell(row, cellNum++, parameterValue.getStrFromValue() + "<==>" + parameterValue.getStrToValue());
				else
					addCell(row, cellNum++, getTranslatedValue(snapshot, parameterValue.getId().getParameterName(), parameterValue.getStrFromValue()));
				
			} else if (parameterValue.getParameterType() == SnapshotParameterValueObject.BOOLEAN) {
				if (parameterValue.getIntFromValue() == null)
					throw new IllegalArgumentException("Null value found for " + parameterValue.getId().getParameterName() + " for snapshot " + snapshotId + ".");
				addCell(row, cellNum++, (parameterValue.getIntFromValue() > 0) ? "TRUE" : "FALSE");
				
			} else 
				throw new ServletException("Unrecognized Parameter Type " + parameterValue.getParameterType() + " for " + parameterValue.getId().getParameterName() + " for snapshot " + snapshotId + ".");

		}
		
		return coverRow;
	}
	
	public int addProductParameters(SnapshotInstance snapshot, int coverRow) throws ServletException {
		List<SnapshotProductService> products = DbSnapshotProductService.findProductBySnapshot(snapshot.getSnapshotId(), (char) 0);
		
		addProductServiceParameters("Products", products, coverRow);
		
		return coverRow;
	}
	
	public int addServiceParameters(SnapshotInstance snapshot, int coverRow) throws ServletException {
		List<SnapshotProductService> services = DbSnapshotProductService.findServiceBySnapshot(snapshot.getSnapshotId(), (char) 0);
		
		addProductServiceParameters("Services", services, coverRow);
		
		return coverRow;
	}
	
	public int addProductServiceParameters(String header, List<SnapshotProductService> productServices, int coverRow) throws ServletException {
		if (productServices == null || productServices.size() == 0)
			return coverRow;
		
		coverRow++;	// Blank line
		
		for (SnapshotProductService productService : productServices) {
			String name;
			if (productService.getId().getProductServiceType() == SnapshotInstance.PRODUCT_TYPE) {
				name = getProduct(productService.getId().getProductServiceCode()).getDescription();
			} else {
				name = getService(productService.getId().getProductServiceCode()).getDescription();
			}
			addCoverCell(coverRow++, header, productService.getId().getProductServiceCode(), name);
			header = "";
		}
		
		return coverRow;
	}
	
	protected String getTranslatedValue(SnapshotInstance snapshot, String name, String value) {
		if (name.equals(SnapshotParameterNames.UCN_TYPE)) {
			return snapshot.getUcnTypeDescription();
		} else if (name.equals(SnapshotParameterNames.PRODUCT_SERVICE_TYPE)) {
			return snapshot.getProductServiceDescription();
		} else if (name.equals(SnapshotParameterNames.TERM_TYPES)) {
			return getTermType(value).getDescription();
		} else if (name.equals(SnapshotParameterNames.INSTITUTION_STATE)) {
			return getInstitutionState(value).getDescription();
		} else if (name.equals(SnapshotParameterNames.INSTITUTION_COUNTRY)) {
			return getInstitutionCountry(value).getDescription();
		} else if (name.equals(SnapshotParameterNames.PROD_COMM_CODES)
				|| name.equals(SnapshotParameterNames.AGREEMENT_COMM_CODES)
				|| name.equals(SnapshotParameterNames.TERM_COMM_CODES)) {
			return getCommissionType(value).getDescription();
		}
		return value;
	}
	
	protected void startDataSheet() {
//		dataSheet.setDefaultRowHeight((short) 50);
		
		int cellNum = 0;
		
		dataSheet.setColumnWidth(cellNum++, getCharacterWidth(12));
		dataSheet.setColumnWidth(cellNum++, getCharacterWidth(6));
		dataSheet.setColumnWidth(cellNum++, getCharacterWidth(12));
		dataSheet.setColumnWidth(cellNum++, getCharacterWidth(16));
		if (snapshot.getProductServiceType() == SnapshotInstance.SERVICE_TYPE) {
			dataSheet.setColumnWidth(cellNum++, getCharacterWidth(16));
		}
		dataSheet.setColumnWidth(cellNum++, getDollarWidth());
		dataSheet.setColumnWidth(cellNum++, getCharacterWidth(16));
		dataSheet.setColumnWidth(cellNum++, getCharacterWidth(CODE_WIDTH));
		dataSheet.setColumnWidth(cellNum++, getDateWidth());
		dataSheet.setColumnWidth(cellNum++, getDateWidth());
		dataSheet.setColumnWidth(cellNum++, getDateWidth());
		dataSheet.setColumnWidth(cellNum++, getDollarWidth());
		dataSheet.setColumnWidth(cellNum++, getDollarWidth());
		dataSheet.setColumnWidth(cellNum++, getDollarWidth());
		dataSheet.setColumnWidth(cellNum++, getDollarWidth());
		dataSheet.setColumnWidth(cellNum++, getDollarWidth());
		cellNum = addProductWidths(dataSheet, cellNum);
		if (snapshot.getProductServiceType() == SnapshotInstance.SERVICE_TYPE) {
			cellNum = addServiceWidths(dataSheet, cellNum);
		}
		cellNum = addInstitutionWidths(dataSheet, cellNum);
		
		createHeaders();
	}
	
	protected int getDateWidth() {
		return getCharacterWidth(DATE_WIDTH);
	}
	
	protected int getDollarWidth() {
		return getCharacterWidth(DOLLAR_WIDTH);
	}
	
	protected int getCharacterWidth(int characters) {
		return characters * 256;
	}
	
	protected void createHeaders() {
		HSSFRow row = dataSheet.createRow(rowNum);
		
		int cellNum = 0;
		
		addCell(row, cellNum++, "Agreement #",	HEADER_STYLE);
		addCell(row, cellNum++, "Term #", 		HEADER_STYLE);
		
		ucnCell = cellNum;
		addCell(row, cellNum++, "UCN", 			HEADER_STYLE);
		
		productCodeCell = cellNum;
		addCell(row, cellNum++, "Product Code", HEADER_STYLE);
		
		if (snapshot.getProductServiceType() == SnapshotInstance.SERVICE_TYPE) {
			serviceCodeCell = cellNum;
			addCell(row, cellNum++, "Service Code", HEADER_STYLE);
		}
		
		addCell(row, cellNum++, "Total Term Dollar Value",	HEADER_STYLE);
		addCell(row, cellNum++, "Term Type",				HEADER_STYLE);
		addCell(row, cellNum++, "Commission Type",			HEADER_STYLE);
		addCell(row, cellNum++, "Start Date",				HEADER_STYLE);
		addCell(row, cellNum++, "End Date",					HEADER_STYLE);
		addCell(row, cellNum++, "Terminate Date",			HEADER_STYLE);
		addCell(row, cellNum++, "Dollar Fraction",			HEADER_STYLE);
		addCell(row, cellNum++, "Service Fraction",			HEADER_STYLE);
		addCell(row, cellNum++, "Dollar Service Fraction",	HEADER_STYLE);
		addCell(row, cellNum++, "UCN Fraction",				HEADER_STYLE);
		addCell(row, cellNum++, "Dollar UCN Fraction",		HEADER_STYLE);
		cellNum = addProductHeadings(row, cellNum);
		if (snapshot.getProductServiceType() == SnapshotInstance.SERVICE_TYPE) {
			cellNum = addServiceHeadings(row, cellNum);
		}
		cellNum = addInstitutionHeadings(row, cellNum);
		
		rowNum++;
	}
	
	protected void processTermData() {
		HibernateUtil.openSession();
		HibernateUtil.startTransaction();

		try {
			
			List<Order> sorts = new ArrayList<Order>();
			sorts.add(Order.asc("id.agreementId"));
			sorts.add(Order.asc("startDate"));
			sorts.add(Order.asc("endDate"));
			sorts.add(Order.asc("id.serviceCode"));
			
			//	Find only undeleted cancel reasons
			List<SnapshotTermData> snapshotTermDatas = DbSnapshotTermData.findFiltered(snapshotId, -1, -1, null, null, -1, -1, sorts);

			//	First pass, just load the ancillary tables if it's needed for the main data sheet, so we know how many we have for the look up formulas
			
			if (useLookups) {
				for (SnapshotTermData snapshotTermData : snapshotTermDatas) {
					getInstitution(snapshotTermData.getId().getUcn());			
					getProduct(snapshotTermData.getId().getProductCode());
					getService(snapshotTermData.getId().getServiceCode());
					getCancelReason(snapshotTermData.getCancelReasonCode());
					getTermType(snapshotTermData.getTermType());
				}
			}
			
			//	Second pass, create the actual data sheet
			
			for (SnapshotTermData snapshotTermData : snapshotTermDatas) {
				SnapshotTermDataInstance instance = DbSnapshotTermData.getInstance(snapshotTermData);

				instance.setInstitution(getInstitution(instance.getUcn()));			
				instance.setProduct(getProduct(instance.getProductCode()));
				instance.setService(getService(instance.getServiceCode()));
				instance.setCancelReason(getCancelReason(instance.getCancelReasonCode()));
				instance.setTermType(getTermType(instance.getTermTypeCode()));
				
				if (instance.getProduct() != null) {
					instance.getProduct().setDefaultCommTypeInstance(getCommissionType(instance.getProduct().getDefaultCommissionCode()));
					instance.getProduct().setDefaultTermTypeInstance(getTermType(instance.getProduct().getDefaultTermType()));
				}
				
				processTermData(instance);
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		HibernateUtil.endTransaction();
		HibernateUtil.closeSession();
	}
	
	protected void processTermData(SnapshotTermDataInstance instance) {
		HSSFRow row = dataSheet.createRow(rowNum);
		
		int cellNum = 0;
		
		addCell(row, cellNum++, instance.getAgreementId(), INTEGER_STYLE);
		addCell(row, cellNum++, instance.getTermId(), INTEGER_STYLE);
		addCell(row, cellNum++, instance.getUcn(), INTEGER_STYLE);
		addCell(row, cellNum++, instance.getProductCode());
		if (snapshot.getProductServiceType() == SnapshotInstance.SERVICE_TYPE) {
			addCell(row, cellNum++, instance.getServiceCode());
		}
		addCell(row, cellNum++, instance.getDollarValue());
		addCell(row, cellNum++, instance.getTermType().getDescriptionAndCode());
		addCell(row, cellNum++, instance.getCommissionCode());
		addCell(row, cellNum++, instance.getStartDate());
		addCell(row, cellNum++, instance.getEndDate());
		addCell(row, cellNum++, instance.getTerminateDate());
		addCell(row, cellNum++, instance.getDollarFraction());
		addCell(row, cellNum++, instance.getServiceFraction(), FRACTION_STYLE);
		addCell(row, cellNum++, instance.getDollarServiceFraction());
		addCell(row, cellNum++, instance.getUcnFraction(), FRACTION_STYLE);
		addCell(row, cellNum++, instance.getDollarUcnFraction());
		if (useLookups) {
			for (int col = 2; col < 7; col++)
				addFormulaCell(row, cellNum++, getLookupFunction(productSheet, productCodeCell, row.getRowNum(), productMap.size(), col), TEXT_FILL_1_STYLE);
			if (snapshot.getProductServiceType() == SnapshotInstance.SERVICE_TYPE) {
				for (int col = 2; col < 5; col++)
					addFormulaCell(row, cellNum++, getLookupFunction(serviceSheet, serviceCodeCell, row.getRowNum(), serviceMap.size(), col), TEXT_FILL_2_STYLE);
			}
			HSSFCellStyle ucnStyle = (snapshot.getProductServiceType() == SnapshotInstance.SERVICE_TYPE) ? TEXT_FILL_1_STYLE : TEXT_FILL_2_STYLE;
			for (int col = 2; col < 8; col++)
				addFormulaCell(row, cellNum++, getLookupFunction(institutionSheet, ucnCell, row.getRowNum(), institutionMap.size(), col), ucnStyle);
		} else {
			cellNum = addProductCells(row, cellNum, instance.getProduct());
			if (snapshot.getProductServiceType() == SnapshotInstance.SERVICE_TYPE) {
				cellNum = addServiceCells(row, cellNum, instance.getService());
			}
			cellNum = addInstitutionCells(row, cellNum, instance.getInstitution());
		}
		
		rowNum++;
	}
	
	protected String getLookupFunction(HSSFSheet sheet, int codeCellNum, int row, int size, int col) {
		String codeCol = CELL_CODES.substring(codeCellNum, codeCellNum + 1);
		return "VLOOKUP(" + codeCol + (row + 1) + "," + sheet.getSheetName() + "!$A$2:$Z$" + (size + 1) + "," + col +")";
	}
	
	protected void addLookupSheets() {
		addProductSheet();
		addServiceSheet();
		addInstitutionSheet();
	}
	
	protected void addInstitutionSheet() {
		int rowNum = 0;
		int cellNum = 0;
		
		institutionSheet.setColumnWidth(cellNum++, getCharacterWidth(CODE_WIDTH));
		cellNum = addInstitutionWidths(institutionSheet, cellNum);
		
		HSSFRow row = institutionSheet.createRow(rowNum++);
		
		cellNum = 0;
		
		addCell(row, cellNum++, "UCN",			HEADER_STYLE);
		cellNum = addInstitutionHeadings(row, cellNum);
		
		for (Integer ucn : institutionMap.keySet()) {
			
			row = institutionSheet.createRow(rowNum++);
			
			cellNum = 0;
			
			addCell(row, cellNum++, ucn, INTEGER_STYLE);
			addInstitutionCells(row, cellNum, institutionMap.get(ucn));
		}
	}
	
	protected int addInstitutionWidths(HSSFSheet sheet, int cellNum) {
		sheet.setColumnWidth(cellNum++, getCharacterWidth(DESCRIPTION_WIDTH));
		sheet.setColumnWidth(cellNum++, getCharacterWidth(DESCRIPTION_WIDTH));
		sheet.setColumnWidth(cellNum++, getCharacterWidth(DESCRIPTION_WIDTH));
		sheet.setColumnWidth(cellNum++, getCharacterWidth(CITY_WIDTH));
		sheet.setColumnWidth(cellNum++, getCharacterWidth(STATE_WIDTH));
		sheet.setColumnWidth(cellNum++, getCharacterWidth(COUNTRY_WIDTH));
		return cellNum;
	}
	
	protected int addInstitutionHeadings(HSSFRow row, int cellNum) {
		addCell(row, cellNum++, "Name",			HEADER_STYLE);
		addCell(row, cellNum++, "Address 1",	HEADER_STYLE);
		addCell(row, cellNum++, "Address 2",	HEADER_STYLE);
		addCell(row, cellNum++, "City",			HEADER_STYLE);
		addCell(row, cellNum++, "State",		HEADER_STYLE);
		addCell(row, cellNum++, "Country",		HEADER_STYLE);
		return cellNum;
	}
	
	protected int addInstitutionCells(HSSFRow row, int cellNum, InstitutionInstance institution) {
		addCell(row, cellNum++, institution.getInstitutionName());
		addCell(row, cellNum++, institution.getAddress1());
		addCell(row, cellNum++, institution.getAddress2());
		addCell(row, cellNum++, institution.getCity());
		addCell(row, cellNum++, institution.getState());
		addCell(row, cellNum++, institution.getCountry());
		return cellNum;
	}
	
	protected void addProductSheet() {
		int rowNum = 0;
		int cellNum = 0;
		
		productSheet.setColumnWidth(cellNum++, getCharacterWidth(CODE_WIDTH));
		cellNum = addProductWidths(productSheet, cellNum);
		
		HSSFRow row = productSheet.createRow(rowNum++);
		
		cellNum = 0;
		
		addCell(row, cellNum++, "Product Code",			HEADER_STYLE);
		cellNum = addProductHeadings(row, cellNum);
		
		for (String productCode : productMap.keySet()) {
			
			row = productSheet.createRow(rowNum++);
			
			cellNum = 0;
			
			addCell(row, cellNum++, productCode);
			addProductCells(row, cellNum, productMap.get(productCode));
		}
	}
	
	protected int addProductWidths(HSSFSheet sheet, int cellNum) {
		sheet.setColumnWidth(cellNum++, getCharacterWidth(DESCRIPTION_WIDTH));
		sheet.setColumnWidth(cellNum++, getCharacterWidth(8));
		sheet.setColumnWidth(cellNum++, getCharacterWidth(16));
		sheet.setColumnWidth(cellNum++, getCharacterWidth(CODE_WIDTH));
		sheet.setColumnWidth(cellNum++, getCharacterWidth(DESCRIPTION_WIDTH));
		return cellNum;
	}
	
	protected int addProductHeadings(HSSFRow row, int cellNum) {
		addCell(row, cellNum++, "Product",							HEADER_STYLE);
		addCell(row, cellNum++, "Default Term Type Code",			HEADER_STYLE);
		addCell(row, cellNum++, "Default Term Type",				HEADER_STYLE);
		addCell(row, cellNum++, "Default Commission Type Code",		HEADER_STYLE);
		addCell(row, cellNum++, "Default Commission Type",			HEADER_STYLE);
		return cellNum;
	}
	
	protected int addProductCells(HSSFRow row, int cellNum, ProductInstance product) {
		addCell(row, cellNum++, product.getDescription());
		addCell(row, cellNum++, product.getDefaultTermType());
		addCell(row, cellNum++, (product.getDefaultTermTypeInstance() == null ? "" : product.getDefaultTermTypeInstance().getDescription()) );
		addCell(row, cellNum++, product.getDefaultCommissionCode());
		addCell(row, cellNum++, (product.getDefaultCommTypeInstance() == null ? "" : product.getDefaultCommTypeInstance().getDescription()));
		return cellNum;
	}
	
	protected void addServiceSheet() {
		int rowNum = 0;
		int cellNum = 0;
		
		serviceSheet.setColumnWidth(cellNum++, getCharacterWidth(CODE_WIDTH));
		cellNum = addServiceWidths(serviceSheet, cellNum);
		
		HSSFRow row = serviceSheet.createRow(rowNum++);
		
		cellNum = 0;
		
		addCell(row, cellNum++, "Service Code",			HEADER_STYLE);
		cellNum = addServiceHeadings(row, cellNum);
		
		for (String serviceCode : serviceMap.keySet()) {
			
			row = serviceSheet.createRow(rowNum++);
			
			cellNum = 0;
			
			addCell(row, cellNum++, serviceCode);
			addServiceCells(row, cellNum, serviceMap.get(serviceCode));
		}
	}
	
	protected int addServiceWidths(HSSFSheet sheet, int cellNum) {
		sheet.setColumnWidth(cellNum++, getCharacterWidth(DESCRIPTION_WIDTH));
		sheet.setColumnWidth(cellNum++, getCharacterWidth(8));
		sheet.setColumnWidth(cellNum++, getCharacterWidth(16));
		return cellNum;
	}
	
	protected int addServiceHeadings(HSSFRow row, int cellNum) {
		addCell(row, cellNum++, "Service",				HEADER_STYLE);
		addCell(row, cellNum++, "Service Type Code",	HEADER_STYLE);
		addCell(row, cellNum++, "Service Type",			HEADER_STYLE);
		return cellNum;
	}
	
	protected int addServiceCells(HSSFRow row, int cellNum, ServiceInstance service) {
		addCell(row, cellNum++, service.getDescription());
		addCell(row, cellNum++, service.getServiceType() + "");
		addCell(row, cellNum++, service.getServiceTypeName());
		return cellNum;
	}
	
	protected int addFormulaCell(HSSFRow row, int cellNum, String formula, HSSFCellStyle style) {
		HSSFCell cell = getCell(row, cellNum);
		cell.setCellStyle(style);
		cell.setCellFormula(formula);
		return ++cellNum;
	}
	
	protected int addCell(HSSFRow row, int cellNum, int data) {
		return addCell(row, cellNum, data, NUMBER_STYLE);
	}
	
	protected int addCell(HSSFRow row, int cellNum, int data, HSSFCellStyle style) {
		HSSFCell cell = getCell(row, cellNum);
		cell.setCellStyle(style);
		cell.setCellValue(data);
		return ++cellNum;
	}
	
	protected int addCell(HSSFRow row, int cellNum, double data) {
		return addCell(row, cellNum, data, CURRENCY_STYLE);
	}

	protected int addCell(HSSFRow row, int cellNum, double data, HSSFCellStyle style) {
		HSSFCell cell = getCell(row, cellNum, style);
		cell.setCellValue(data);
		return ++cellNum;
	}
	
	protected int addCell(HSSFRow row, int cellNum, String data) {
		getCell(row, cellNum).setCellValue(data);
		return ++cellNum;
	}
	
	protected int addCell(HSSFRow row, int cellNum, String data, HSSFCellStyle style) {
		getCell(row, cellNum, style).setCellValue(data);
		return ++cellNum;
	}
	
	protected int addCell(HSSFRow row, int cellNum, Date data) {
		HSSFCell cell = getCell(row, cellNum, DATE_STYLE);
		cell.setCellValue(data);	
		return ++cellNum;
	}
	
	protected HSSFCell getCell(HSSFRow row, int cellNum) {
		if (cellNum == 0 || row.getRowNum() == 0)
			return getCell(row, cellNum, BOLD_STYLE);
		else
			return getCell(row, cellNum, TEXT_STYLE);
	}
	
	protected HSSFCell getCell(HSSFRow row, int cellNum, HSSFCellStyle style) {
		HSSFCell cell = row.createCell(cellNum);
		cell.setCellStyle(style);
		return cell;
	}
	
	protected ServiceInstance getService(String serviceCode) {
		if (serviceCode == null || serviceCode.length() == 0)
			return ServiceInstance.getEmptyInstance();	//	Could reduce memory footprint by caching/returning one instance of this
		
		if (serviceMap.containsKey(serviceCode))
			return serviceMap.get(serviceCode);
		
		Service service = DbService.getByCode(serviceCode);
		if (service != null) {
			serviceMap.put(serviceCode, DbService.getInstance(service));
			return serviceMap.get(serviceCode);
		}
		
		return ServiceInstance.getUnknownInstance(serviceCode);
	}
	
	protected ProductInstance getProduct(String productCode) {
		if (productCode == null || productCode.length() == 0)
			return ProductInstance.getEmptyInstance();	//	Could reduce memory footprint by caching/returning one instance of this
		
		if (productMap.containsKey(productCode))
			return productMap.get(productCode);
		
		Product product = DbProduct.getByCode(productCode);
		if (product != null) {
			productMap.put(productCode, DbProduct.getInstance(product));
			return productMap.get(productCode);
		}
		
		return ProductInstance.getUnknownInstance(productCode);
	}
	
	protected CancelReasonInstance getCancelReason(String cancelReasonCode) {
		if (cancelReasonCode == null || cancelReasonCode.length() == 0)
			return CancelReasonInstance.getEmptyInstance();	//	Could reduce memory footprint by caching/returning one instance of this
		
		if (cancelReasonMap.containsKey(cancelReasonCode))
			return cancelReasonMap.get(cancelReasonCode);
		
		CancelReason cancelReason = DbCancelReason.getByCode(cancelReasonCode);
		if (cancelReason != null) {
			cancelReasonMap.put(cancelReasonCode, DbCancelReason.getInstance(cancelReason));
			return cancelReasonMap.get(cancelReasonCode);
		}
		
		return CancelReasonInstance.getUnknownInstance(cancelReasonCode);
	}
	
	protected InstitutionInstance getInstitution(int ucn) {
		if (ucn <= 0)
			return InstitutionInstance.getEmptyInstance();	//	Could reduce memory footprint by caching/returning one instance of this
		
		if (institutionMap.containsKey(ucn))
			return institutionMap.get(ucn);
		
		Institution institution = DbInstitution.getByCode(ucn);
		if (institution != null) {
			institutionMap.put(ucn, DbInstitution.getInstance(institution));
			return institutionMap.get(ucn);
		}
		
		return InstitutionInstance.getUnknownInstance(ucn);
	}
	
	protected TermTypeInstance getTermType(String termTypeCode) {
		if (termTypeCode == null || termTypeCode.length() == 0)
			return TermTypeInstance.getEmptyInstance();	//	Could reduce memory footprint by caching/returning one instance of this
		
		if (termTypeMap.containsKey(termTypeCode))
			return termTypeMap.get(termTypeCode);
		
		TermType termType = DbTermType.getByCode(termTypeCode);
		if (termType != null) {
			termTypeMap.put(termTypeCode, DbTermType.getInstance(termType));
			return termTypeMap.get(termTypeCode);
		}
		
		return TermTypeInstance.getUnknownInstance(termTypeCode);
	}
	
	protected CommissionTypeInstance getCommissionType(String commissionTypeCode) {
		if (commissionTypeCode == null || commissionTypeCode.length() == 0)
			return CommissionTypeInstance.getEmptyInstance();	//	Could reduce memory footprint by caching/returning one instance of this
		
		if (commissionTypeMap.containsKey(commissionTypeCode))
			return commissionTypeMap.get(commissionTypeCode);
		
		CommissionType commissionType = DbCommissionType.getByCode(commissionTypeCode);
		if (commissionType != null) {
			commissionTypeMap.put(commissionTypeCode, DbCommissionType.getInstance(commissionType));
			return commissionTypeMap.get(commissionTypeCode);
		}
		
		return CommissionTypeInstance.getUnknownInstance(commissionTypeCode);
	}
	
	protected InstitutionStateInstance getInstitutionState(String institutionStateCode) {
		if (institutionStateCode == null || institutionStateCode.length() == 0)
			return InstitutionStateInstance.getEmptyInstance();	//	Could reduce memory footprint by caching/returning one instance of this
		
		if (institutionStateMap.containsKey(institutionStateCode))
			return institutionStateMap.get(institutionStateCode);
		
		InstitutionState institutionState = DbInstitutionState.getByCode(institutionStateCode);
		if (institutionState != null) {
			institutionStateMap.put(institutionStateCode, DbInstitutionState.getInstance(institutionState));
			return institutionStateMap.get(institutionStateCode);
		}
		
		return InstitutionStateInstance.getUnknownInstance(institutionStateCode);
	}
	
	protected InstitutionCountryInstance getInstitutionCountry(String institutionCountryCode) {
		if (institutionCountryCode == null || institutionCountryCode.length() == 0)
			return InstitutionCountryInstance.getEmptyInstance();	//	Could reduce memory footprint by caching/returning one instance of this
		
		if (institutionCountryMap.containsKey(institutionCountryCode))
			return institutionCountryMap.get(institutionCountryCode);
		
		InstitutionCountry institutionCountry = DbInstitutionCountry.getByCode(institutionCountryCode);
		if (institutionCountry != null) {
			institutionCountryMap.put(institutionCountryCode, DbInstitutionCountry.getInstance(institutionCountry));
			return institutionCountryMap.get(institutionCountryCode);
		}
		
		return InstitutionCountryInstance.getUnknownInstance(institutionCountryCode);
	}

	public boolean usesLookups() {
		return useLookups;
	}

	public void setUseLookups(boolean useLookups) {
		this.useLookups = useLookups;
	}
	
	
}
