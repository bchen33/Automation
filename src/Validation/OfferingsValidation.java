package Validation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;

import PageObjects.OfferingsAdditionalDetailsPage;
import Utility.CSV;
import Utility.PropDetails;
import test.java.RepoSmokeTest;

public class OfferingsValidation {
	
	public static void getOfferingsPropDetailResults(JSONObject ESresults, OfferingsAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> header = new ArrayList<PropDetails>();
		//check for header
		String ESpropNameHeader, UIpropNameHeader = "";
		if (ESresults.has("propertyName_tx") && !ESresults.isNull("propertyName_tx")) {
			ESpropNameHeader = ESresults.getString("propertyName_tx");
			UIpropNameHeader = propDetails.returnPropNameHeader();
			header.add(new PropDetails(String.valueOf(caseID), "Property Name Header", ESpropNameHeader, UIpropNameHeader));
		}
		
		CSV.appendToFileExcel("offeringsSearch" + RepoSmokeTest.currentDateEnv, header);
		
		try {
			getOfferingsLocationDetails(ESresults, propDetails, caseID);
			getOfferingsPropDetails(ESresults, propDetails, caseID);
			getOfferingsTransactionDetails(ESresults, propDetails, caseID);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void getOfferingsLocationDetails(JSONObject ESresults, OfferingsAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> results = new ArrayList<PropDetails>(); 
		
		//ES
		String ESadd, EScity, ESstate, ESpostalCode, EScountry; 
		String ESlat, ESlong, ESlatLong;
		
		//UI
		String UIadd, UIcity, UIstate, UIpostalCode, UIcountry; 
		String UIlatLong;
		
		System.out.println("Validating Offerings Location Data");
		
		//Address
		if (ESresults.has("addr1_tx") && !ESresults.isNull("addr1_tx")){
			ESadd = ESresults.getString("addr1_tx");
		} else { ESadd = ""; }
		if (!propDetails.returnAdd().isEmpty()) {
			UIadd = propDetails.returnAdd();
		} else { UIadd = ""; }
		results.add(new PropDetails(String.valueOf(caseID), "Address", ESadd, UIadd));
	
		//City
		if (ESresults.has("city_tx") && !ESresults.isNull("city_tx")){
			EScity = ESresults.getString("city_tx");
		} else { EScity = ""; }
		if (!propDetails.returnCity().isEmpty()) {
			UIcity = propDetails.returnCity();
		} else { UIcity = ""; }
		results.add(new PropDetails(String.valueOf(caseID), "City", EScity, UIcity));
		
		//State
		if (ESresults.has("state_tx") && !ESresults.isNull("state_tx")){
			ESstate = ESresults.getString("state_tx");
		} else { ESstate = ""; }
		if (!propDetails.returnState().isEmpty()) {
			UIstate = propDetails.returnState();
		} else { UIstate = ""; }
		results.add(new PropDetails(String.valueOf(caseID), "State", ESstate, UIstate));
		
		//PostalCode
		if (ESresults.has("zipCode_tx") && !ESresults.isNull("zipCode_tx")){
			ESpostalCode = ESresults.getString("zipCode_tx");
		} else { ESpostalCode = ""; }
		if (!propDetails.returnPostalCode().isEmpty()) {
			UIpostalCode = propDetails.returnPostalCode();
		} else { UIpostalCode = ""; }
		results.add(new PropDetails(String.valueOf(caseID), "Postal Code", ESpostalCode, UIpostalCode));
		
		Thread.sleep(1000);
		
		//Country
		if (ESresults.has("country_tx") && !ESresults.isNull("country_tx")){
			EScountry = ESresults.getString("country_tx");
		} else { EScountry = ""; }
		if (!propDetails.returnCountry().isEmpty()) {
			UIcountry = propDetails.returnCountry();
		} else { UIcountry = ""; }
		results.add(new PropDetails(String.valueOf(caseID), "Country", EScountry, UIcountry));
		
		//Lat/Long
		if (ESresults.has("latitude_dbl") && !ESresults.isNull("latitude_dbl")) {
			ESlat = String.valueOf(ESresults.get("latitude_dbl"));
		} else { ESlat = ""; }
		if (ESresults.has("longitude_dbl") && !ESresults.isNull("longitude_dbl")) {
			ESlong = String.valueOf(ESresults.get("longitude_dbl"));
		} else { ESlong = ""; }
		if (ESlat == "" && ESlong == "") {
			ESlatLong = "";
		} else { ESlatLong = ESlat + ", " + ESlong; }
		if (!propDetails.returnLatLong().isEmpty()) {
			UIlatLong = propDetails.returnLatLong();
		} else { UIlatLong = ""; }
		results.add(new PropDetails(String.valueOf(caseID), "Lat/Long", ESlatLong, UIlatLong));
		
		CSV.appendToFileExcel("offeringsSearch" + RepoSmokeTest.currentDateEnv, results);
	}
	
	public static void getOfferingsPropDetails(JSONObject ESresults, OfferingsAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> strs = new ArrayList<PropDetails>(); 
		List<PropDetails> nums = new ArrayList<PropDetails>(); 
		
		System.out.println("Validating Offerings Property Details Data");
		
		//ES
		String ESpropType = "", ESpropSubType = "", ESstatus = "", ESloanType = "", ESinterestType = "", ESloanClass = "", ESwebsite = ""; 
		double ESarea = -9999, ESlandArea = -9999, ESoccupancy = -9999;
		int ESunits = -9999, ESyearBuilt = -9999;
				
		//UI
		String UIpropType = "", UIpropSubType = "", UIstatus = "", UIloanType = "", UIinterestType = "", UIloanClass = "", UIwebsite = ""; 
		double UIarea = -9999, UIlandArea = -9999, UIoccupancy = -9999; 
		int UIunits = -9999, UIyearBuilt = -9999;  
		
		//Type
		if (ESresults.has("propertyType_tx") && !ESresults.isNull("propertyType_tx")){
			ESpropType = ESresults.getString("propertyType_tx");
		} 
		if (!propDetails.returnPropType().isEmpty()) {
			UIpropType = propDetails.returnPropType();
		}
		strs.add(new PropDetails(String.valueOf(caseID), "Prop Type", ESpropType, UIpropType));
				
		//Prop Subtype
		if (ESresults.has("propertySubType_tx") && !ESresults.isNull("propertySubType_tx")){
			ESpropSubType = ESresults.getString("propertySubType_tx");
		}
		if (!propDetails.returnPropSubtype().isEmpty()) {
			UIpropSubType = propDetails.returnPropSubtype();
		}
		strs.add(new PropDetails(String.valueOf(caseID), "Prop Subtype", ESpropSubType, UIpropSubType));

		//Area
		if (ESresults.has("area_dbl") && !ESresults.isNull("area_dbl")){
			ESarea = ESresults.getDouble("area_dbl");
		}
		if (!propDetails.returnArea().isEmpty()) {
			UIarea = Double.valueOf(propDetails.returnArea().replaceAll(",", ""));
		}
		nums.add(new PropDetails(String.valueOf(caseID), "Area", ESarea, UIarea));
		
		//Units
		if (ESresults.has("units_nb") && !ESresults.isNull("units_nb")){
			ESunits = ESresults.getInt("units_nb");
		}
		if (!propDetails.returnUnits().isEmpty()) {
			UIunits = Integer.valueOf(propDetails.returnUnits());
		}
		nums.add(new PropDetails(String.valueOf(caseID), "Units", ESunits, UIunits));
		
		//Land Area
		if (ESresults.has("parcelSize_dbl") && !ESresults.isNull("parcelSize_dbl")){
			ESlandArea = ESresults.getDouble("parcelSize_dbl");
		}
		if (!propDetails.returnLandArea().isEmpty()) {
			UIlandArea = Double.valueOf(propDetails.returnLandArea());
		}
		nums.add(new PropDetails(String.valueOf(caseID), "Land Area", ESlandArea, UIlandArea));
		
		//Status
		if (ESresults.has("status_tx") && !ESresults.isNull("status_tx")){
			ESstatus = ESresults.getString("status_tx");
		}
		if (!propDetails.returnStatus().isEmpty()) {
			UIstatus = propDetails.returnStatus();
		}
		strs.add(new PropDetails(String.valueOf(caseID), "Status", ESstatus, UIstatus));
		
		//Occupancy
		if (ESresults.has("occupancy_dbl") && !ESresults.isNull("occupancy_dbl")){
			ESoccupancy = ESresults.getDouble("occupancy_dbl");
		}
		if (!propDetails.returnOccupancy().isEmpty()) {
			if (propDetails.returnOccupancy().equals("N/A")) {
				UIoccupancy = 0.0;
			} else {	
				UIoccupancy = Double.valueOf(propDetails.returnOccupancy().replaceAll("%", "")) / 100.0;
			}
		}
		nums.add(new PropDetails(String.valueOf(caseID), "Occupancy", ESoccupancy, UIoccupancy)); 
		
		//Year Built
		if (ESresults.has("yearBuilt_nb") && !ESresults.isNull("yearBuilt_nb")){
			ESyearBuilt = ESresults.getInt("yearBuilt_nb");
		}
		if (!propDetails.returnYearBuilt().isEmpty()) {
			UIyearBuilt = Integer.valueOf(propDetails.returnYearBuilt());
		}
		nums.add(new PropDetails(String.valueOf(caseID), "Year Built", ESyearBuilt, UIyearBuilt));
		
		//Loan Type
		if (ESresults.has("loanType_tx") && !ESresults.isNull("loanType_tx")){
			ESloanType = ESresults.getString("loanType_tx");
		}
		if (!propDetails.returnLoanType().isEmpty()) {
			UIloanType = propDetails.returnLoanType();
		}
		strs.add(new PropDetails(String.valueOf(caseID), "Loan Type", ESloanType, UIloanType));
		
		//Interest Type
		if (ESresults.has("loanInterestType_tx") && !ESresults.isNull("loanInterestType_tx")){
			ESinterestType = ESresults.getString("loanInterestType_tx");
		}
		if (!propDetails.returnInterestType().isEmpty()) {
			UIinterestType = propDetails.returnInterestType();
		}
		strs.add(new PropDetails(String.valueOf(caseID), "Interest Type", ESinterestType, UIinterestType));
		
		//Loan Class
		if (ESresults.has("loanClass_tx") && !ESresults.isNull("loanClass_tx")){
			ESloanClass = ESresults.getString("loanClass_tx");
		}
		if (!propDetails.returnLoanClass().isEmpty()) {
			UIloanClass = propDetails.returnLoanClass();
		}
		strs.add(new PropDetails(String.valueOf(caseID), "Loan Class", ESloanClass, UIloanClass));
		
		//Website
		if (ESresults.has("link_tx") && !ESresults.isNull("link_tx")){
			ESwebsite = ESresults.getString("link_tx");
		}
		if (propDetails.isWebsiteExist()) {
			UIwebsite = propDetails.returnWebsite().trim();
		} else {
			UIwebsite = "";
		}
		//if (!propDetails.returnWebsite().isEmpty()) {
		//	UIwebsite = propDetails.returnWebsite();
		//}
		strs.add(new PropDetails(String.valueOf(caseID), "Website", ESwebsite, UIwebsite));
		
		CSV.appendToFileExcel("offeringsSearch" + RepoSmokeTest.currentDateEnv, strs);
		CSV.appendToFileExcelNums("offeringsSearch" + RepoSmokeTest.currentDateEnv, nums);
	}
	
	public static void getOfferingsTransactionDetails(JSONObject ESresults, OfferingsAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> str = new ArrayList<PropDetails>(); 
		List<PropDetails> nums = new ArrayList<PropDetails>();

		System.out.println("Validating Offerings Transaction Details Data");
		
		//ES
		String ESprojectID = "", ESprojectType = "", ESofferedDate = "", ESvalueType = "", EScompany = "", ESfirstName = "", ESlastName = "", ESname = "", ESphoneNumber = "", ESsummary = "";  
		double ESvalue = -9999, EScapRate = -9999;
		
		//UI
		String UIprojectID = "", UIprojectType = "", UIofferedDate = "", UIvalueType = "", UIcompany = "", UIname = "", UIphoneNumber = "", UIsummary = "";
		double UIvalue = -9999, UIcapRate = -9999;
		
		//ProjectID
		if (ESresults.has("project_id") && !ESresults.isNull("project_id")){
			ESprojectID = ESresults.get("project_id").toString();
		}
		if (!propDetails.returnProjectID().isEmpty()) {
			UIprojectID = propDetails.returnProjectID();
		}
		str.add(new PropDetails(String.valueOf(caseID), "Project ID", ESprojectID, UIprojectID));
		
		//Project Type
		if (ESresults.has("projectType_tx") && !ESresults.isNull("projectType_tx")){
			ESprojectType = ESresults.getString("projectType_tx");
		}
		if (!propDetails.returnProjectType().isEmpty()) {
			UIprojectType = propDetails.returnProjectType();
		}
		str.add(new PropDetails(String.valueOf(caseID), "Project Type", ESprojectType, UIprojectType));
		
		//Offered Date
		if (ESresults.has("blastMinDate_dt") && !ESresults.isNull("blastMinDate_dt")) {
			ESofferedDate = ESresults.getString("blastMinDate_dt");
			String year = ESofferedDate.substring(0, 4);
			String month = ESofferedDate.substring(5, 7);
			String date = ESofferedDate.substring(8, 10);
			ESofferedDate = month + "/" + date + "/" + year;
		}
		if (!propDetails.returnOfferedDate().isEmpty()) {
			UIofferedDate = propDetails.returnOfferedDate();
		}
		str.add(new PropDetails(String.valueOf(caseID), "Report Date", ESofferedDate, UIofferedDate));
		
		//Value
		if (ESresults.has("value_dbl") && !ESresults.isNull("value_dbl")){
			ESvalue = ESresults.getDouble("value_dbl");
		}
		if (!propDetails.returnValue().isEmpty()) {
			UIvalue = Double.valueOf(propDetails.returnValue().replaceAll("\\$", "").replaceAll(",", ""));
		}
		nums.add(new PropDetails(String.valueOf(caseID), "Value", ESvalue, UIvalue));
		
		//Value Type
		if (ESresults.has("currencyCode_tx") && !ESresults.isNull("currencyCode_tx")){
			ESvalueType = ESresults.getString("currencyCode_tx");
		}
		if (!propDetails.returnValueType().isEmpty()) {
			UIvalueType = propDetails.returnValueType();
		}
		str.add(new PropDetails(String.valueOf(caseID), "Value Type", ESvalueType, UIvalueType));
		
		//Cap Rate
		if (ESresults.has("capRate_dbl") && !ESresults.isNull("capRate_dbl")){
			EScapRate = ESresults.getDouble("capRate_dbl");
		}
		if (!propDetails.returnCapRate().isEmpty()) {
			UIcapRate = Double.valueOf(propDetails.returnOccupancy().replaceAll("%", "")) / 100.0;
		}
		nums.add(new PropDetails(String.valueOf(caseID), "Cap Rate", EScapRate, UIcapRate)); 
		
		//Company
		if (ESresults.has("company_tx") && !ESresults.isNull("company_tx")){
			EScompany = ESresults.getString("company_tx");
		}
		if (!propDetails.returnCompany().isEmpty()) {
			UIcompany = propDetails.returnCompany();
		}
		str.add(new PropDetails(String.valueOf(caseID), "Company", EScompany, UIcompany));
		
		//Name
		if (ESresults.has("firstName_tx") && !ESresults.isNull("firstName_tx")){
			ESfirstName = ESresults.getString("firstName_tx");
		}
		if (ESresults.has("lastName_tx") && !ESresults.isNull("lastName_tx")) {
			ESlastName = ESresults.getString("lastName_tx");
		}
		ESname = ESfirstName + " " + ESlastName;
		if (!propDetails.returnName().isEmpty()) {
			UIname = propDetails.returnName();
		}
		str.add(new PropDetails(String.valueOf(caseID), "Name", ESname, UIname));
		
		//Phone Number
		if (ESresults.has("phone_tx") && !ESresults.isNull("phone_tx")){
			ESphoneNumber = ESresults.getString("phone_tx");
		}
		if (!propDetails.returnPhoneNumber().isEmpty()) {
			UIphoneNumber = propDetails.returnPhoneNumber();
		}
		str.add(new PropDetails(String.valueOf(caseID), "Phone Number", ESphoneNumber, UIphoneNumber));
		
		//Summary
		if (ESresults.has("summary_tx") && !ESresults.isNull("summary_tx")){
			ESsummary = ESresults.getString("summary_tx");
		}
		if (!propDetails.returnSummary().isEmpty()) {
			UIsummary = propDetails.returnSummary();
		}
		str.add(new PropDetails(String.valueOf(caseID), "Summary", ESsummary, UIsummary));
		
		CSV.appendToFileExcel("offeringsSearch" + RepoSmokeTest.currentDateEnv, str);
		CSV.appendToFileExcelNums("offeringsSearch" + RepoSmokeTest.currentDateEnv, nums);
	}
}
