package Validation;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;

import PageObjects.DodgeAdditionalDetailsPage;
import Utility.CSV;
import Utility.PropDetails;
import test.java.RepoSmokeTest;

public class DodgeValidation {
	
	public static void getDodgePropDetailResults(JSONObject ESresults, DodgeAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> header = new ArrayList<PropDetails>();
		//check for header
		String ESpropNameHeader, UIpropNameHeader = "";
		if (ESresults.has("title_tx") && !ESresults.isNull("title_tx")) {
			ESpropNameHeader = ESresults.getString("title_tx");
			UIpropNameHeader = propDetails.returnPropNameHeader();
			header.add(new PropDetails(String.valueOf(caseID), "Title (Property Name)", ESpropNameHeader, UIpropNameHeader));
		}
		CSV.appendToFileExcel("dodgeSearch" + RepoSmokeTest.currentDateEnv, header);
		try {
			getDodgeLocationDetails(ESresults, propDetails, caseID);
			getDodgePropCharacteristicsDetails(ESresults, propDetails, caseID);
			getDodgeCompanyDetails(ESresults, propDetails, caseID);
			getDodgeConstructionDetails(ESresults, propDetails, caseID);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void getDodgeLocationDetails(JSONObject ESresults, DodgeAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> results = new ArrayList<PropDetails>(); 
		
		//ES
		String ESsourceKey, ESadd1, ESadd2, EScity, ESstate, ESpostalCode, EScounty, ESFIPSName, ESFIPSCode, ESMSAName, ESMSACode, ESNCREIF; 
		String ESlat, ESlong, ESlatLong;
		
		//UI
		String UIrepnum, UIadd1, UIadd2, UIcity, UIstate, UIpostalCode, UIcounty, UIFIPSName, UIFIPSCode, UIMSAName, UIMSACode, UINCREIF; 
		String UIlatLong;
		
		System.out.println("Validating Construction Location Data");
		
		//Repnum
		if (ESresults.has("sourceKey_tx") && !ESresults.isNull("sourceKey_tx")){
			ESsourceKey = ESresults.getString("sourceKey_tx");
		} else { ESsourceKey = ""; }
		Thread.sleep(3500);
		UIrepnum = propDetails.returnRepnum();
		results.add(new PropDetails(String.valueOf(caseID), "Repnum", ESsourceKey, UIrepnum));
				
		//Address 1
		if (ESresults.has("addr1_tx") && !ESresults.isNull("addr1_tx")){
			ESadd1 = ESresults.getString("addr1_tx");
		} else { ESadd1 = ""; }
		UIadd1 = propDetails.returnAdd1();
		results.add(new PropDetails(String.valueOf(caseID), "Address 1", ESadd1, UIadd1));
	
		//Address 2
		if (ESresults.has("addr2_tx") && !ESresults.isNull("addr2_tx")){
			ESadd2 = ESresults.getString("addr2_tx");
		} else { ESadd2 = ""; }
		UIadd2 = propDetails.returnAdd2();
		results.add(new PropDetails(String.valueOf(caseID), "Address 2", ESadd2, UIadd2));
		
		//City
		if (ESresults.has("city_tx") && !ESresults.isNull("city_tx")){
			EScity = ESresults.getString("city_tx");
		} else { EScity = ""; }
		UIcity = propDetails.returnCity();
		results.add(new PropDetails(String.valueOf(caseID), "City", EScity, UIcity));
		
		//State
		if (ESresults.has("state_tx") && !ESresults.isNull("state_tx")){
			ESstate = ESresults.getString("state_tx");
		} else { ESstate = ""; }
		UIstate = propDetails.returnState();
		results.add(new PropDetails(String.valueOf(caseID), "State", ESstate, UIstate));
		
		//PostalCode
		if (ESresults.has("zipCode_tx") && !ESresults.isNull("zipCode_tx")){
			ESpostalCode = ESresults.getString("zipCode_tx");
		} else { ESpostalCode = ""; }
		UIpostalCode = propDetails.returnPostalCode();
		results.add(new PropDetails(String.valueOf(caseID), "Postal Code", ESpostalCode, UIpostalCode));
		
		//County & FIPSName
		if (ESresults.has("fipsName_tx") && !ESresults.isNull("fipsName_tx")){
			ESFIPSName = ESresults.getString("fipsName_tx");
			EScounty = ESFIPSName.substring(0, ESFIPSName.length() - 4);
		} else { ESFIPSName = ""; EScounty = "";}
		UIFIPSName = propDetails.returnFIPSName();
		UIcounty = propDetails.returnCounty();
		results.add(new PropDetails(String.valueOf(caseID), "County", EScounty, UIcounty));
		results.add(new PropDetails(String.valueOf(caseID), "FIPS Name", ESFIPSName, UIFIPSName));
		
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
		UIlatLong = propDetails.returnLatLong();
		results.add(new PropDetails(String.valueOf(caseID), "Lat/Long", ESlatLong, UIlatLong));
		
		//FIPS Code
		if (ESresults.has("fips_tx") && !ESresults.isNull("fips_tx")){
			ESFIPSCode = ESresults.getString("fips_tx");
		} else { ESFIPSCode = ""; }
		UIFIPSCode = propDetails.returnFIPSCode();
		results.add(new PropDetails(String.valueOf(caseID), "FIPS Code", ESFIPSCode, UIFIPSCode));
		
		//MSA Name
		if (ESresults.has("msaName_tx") && !ESresults.isNull("msaName_tx")){
			ESMSAName = ESresults.getString("msaName_tx");
		} else { ESMSAName = ""; }
		UIMSAName = propDetails.returnMSAName();
		results.add(new PropDetails(String.valueOf(caseID), "MSA Name", ESMSAName, UIMSAName));
		
		//MSA Code
		if (ESresults.has("msa_tx") && !ESresults.isNull("msa_tx")){
			ESMSACode = ESresults.getString("msa_tx");
		} else { ESMSACode = ""; }
		UIMSACode = propDetails.returnMSACode();
		results.add(new PropDetails(String.valueOf(caseID), "MSA Code", ESMSACode, UIMSACode));
		
		//NCREIF
		if (ESresults.has("ncreif_tx") && !ESresults.isNull("ncreif_tx")){
			ESNCREIF = ESresults.getString("ncreif_tx");
		} else { ESNCREIF = ""; }
		UINCREIF = propDetails.returnNCREIF();
		results.add(new PropDetails(String.valueOf(caseID), "NCREIF", ESNCREIF, UINCREIF));
		
		CSV.appendToFileExcel("dodgeSearch" + RepoSmokeTest.currentDateEnv, results);
	}
	
	public static void getDodgePropCharacteristicsDetails(JSONObject ESresults, DodgeAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> strs = new ArrayList<PropDetails>(); 
		List<PropDetails> nums = new ArrayList<PropDetails>();
		List<PropDetails> bool = new ArrayList<PropDetails>();
		
		System.out.println("Validating Construction Property Characteristics Data");
		
		//ES
		String ESpropType, ESpropSubType, EShotelBrands, EStenants = "", ESleedRegRating, ESleedIntendedRating; 
		double EShardCost, ESarea, ESparkingMeasure;
		int ESunits, ESbuildings, ESfloors;
		boolean ESleedRegistered = false, ESleedIntended = false;
		
		//UI
		String UIpropType, UIpropSubType, UIhotelBrands, UItenants, UIleedRegRating, UIleedIntendedRating; 
		double UIhardCost, UIarea, UIparkingMeasure; 
		int UIunits, UIbuildings, UIfloors;
		boolean UIleedRegistered, UIleedIntended;
		
		//Prop Type
		if (ESresults.has("primaryProperty_tx") && !ESresults.isNull("primaryProperty_tx")){
			ESpropType = ESresults.getString("primaryProperty_tx");
		} else { ESpropType = ""; }
		UIpropType = propDetails.returnPropType();
		strs.add(new PropDetails(String.valueOf(caseID), "Prop Type", ESpropType, UIpropType));
				
		//Prop Subtype
		if (ESresults.has("subclass_tx") && !ESresults.isNull("subclass_tx")){
			ESpropSubType = ESresults.getString("subclass_tx");
		} else { ESpropSubType = ""; }
		UIpropSubType = propDetails.returnPropSubtype();
		strs.add(new PropDetails(String.valueOf(caseID), "Prop Subtype", ESpropSubType, UIpropSubType));

		//Hard Cost
		if (ESresults.has("value_dbl") && !ESresults.isNull("value_dbl")){
			EShardCost = ESresults.getDouble("value_dbl");
		} else { EShardCost = 0; }
		if (!propDetails.returnHardCost().isEmpty()){
			UIhardCost = Double.valueOf(propDetails.returnHardCost().replaceAll("\\$", "").replaceAll(",", "").replaceAll(" est", ""));
		} else { UIhardCost = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Hard Cost", EShardCost, UIhardCost));
		
		//Area
		if (ESresults.has("area_dbl") && !ESresults.isNull("area_dbl")){
			ESarea = ESresults.getDouble("area_dbl");
		} else { ESarea = 0; }
		if (propDetails.returnArea().equals("N/A")) {
			UIarea = 0;
		} else if (!propDetails.returnArea().isEmpty()){
			UIarea = Double.valueOf(propDetails.returnArea().replaceAll(",", ""));
		} else {
			UIarea = 0;
		}
		nums.add(new PropDetails(String.valueOf(caseID), "Area", ESarea, UIarea));
		
		//Units
		if (ESresults.has("units_nb") && !ESresults.isNull("units_nb")){
			ESunits = ESresults.getInt("units_nb");
		} else { ESunits = 0; }
		if (!propDetails.returnUnits().isEmpty()){
			UIunits = Integer.valueOf(propDetails.returnUnits());
		} else { UIunits = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Units", ESunits, UIunits));
		
		//Buildings
		if (ESresults.has("buildings_nb") && !ESresults.isNull("buildings_nb")){
			ESbuildings = ESresults.getInt("buildings_nb");
		} else { ESbuildings = 0; }
		if (!propDetails.returnBuildings().isEmpty()) {
			UIbuildings = Integer.valueOf(propDetails.returnBuildings());
		} else { UIbuildings = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Buildings", ESbuildings, UIbuildings));
		
		//Floors
		if (ESresults.has("stories_nb") && !ESresults.isNull("stories_nb")){
			ESfloors = ESresults.getInt("stories_nb");
		} else { ESfloors = 0; }
		if (!propDetails.returnFloors().isEmpty()) {
			UIfloors = Integer.valueOf(propDetails.returnFloors());
		} else { UIfloors = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Floors", ESfloors, UIfloors));
		
		//Parking/Measure
		if (ESresults.has("parkingArea_dbl") && !ESresults.isNull("parkingArea_dbl")){
			ESparkingMeasure = ESresults.getDouble("parkingArea_dbl");
		} else { ESparkingMeasure = 0; }
		if (!propDetails.returnParkingMeasure().isEmpty()) {
			UIparkingMeasure = Double.valueOf(propDetails.returnParkingMeasure().replaceAll(",", ""));
		} else { UIparkingMeasure = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Parking/Measure", ESparkingMeasure, UIparkingMeasure)); 
		
		//Hotel Brands
		if (ESresults.has("drBrand_json") && !ESresults.isNull("drBrand_json")) {
			JSONObject brand_json = ESresults.getJSONArray("drBrand_json").getJSONObject(0);
			if (ESresults.has("hotel_fg") && !ESresults.isNull("hotel_fg") && ESresults.getBoolean("hotel_fg")) {
				EShotelBrands = brand_json.getString("name_tx");
			} else { EShotelBrands = ""; } 
		} else { EShotelBrands = ""; }
		UIhotelBrands = propDetails.returnHotelBrands();
		strs.add(new PropDetails(String.valueOf(caseID), "Hotel Brands", EShotelBrands, UIhotelBrands));
		
		//Tenants
		if (ESresults.has("drBrand_json") && !ESresults.isNull("drBrand_json")) {
			int tenantCount = ESresults.getJSONArray("drBrand_json").length();
			for (int i = 0; i < tenantCount; i++) {
				JSONObject brand_json = ESresults.getJSONArray("drBrand_json").getJSONObject(i);
				if (ESresults.has("hotel_fg") && !ESresults.isNull("hotel_fg") && !ESresults.getBoolean("hotel_fg")) {
					String tenant = brand_json.getString("name_tx");
					if (i == 0) {
						EStenants = tenant;
					} else { 
						EStenants = EStenants + ", " + tenant;
					}
				} else { EStenants = ""; }
			
			}
		} else { EStenants = ""; }
		UItenants = propDetails.returnTenants();
		strs.add(new PropDetails(String.valueOf(caseID), "Tenants", EStenants, UItenants));
		
		//Leed Registered
		if (ESresults.has("leedRegister_fg")) {
			ESleedRegistered = ESresults.getBoolean("leedRegister_fg");
		} 
		UIleedRegistered = propDetails.returnLeedRegistered();
		bool.add(new PropDetails(String.valueOf(caseID), "Leed Registered", ESleedRegistered, UIleedRegistered));
		
		//Leed Registered Rating
		if (ESresults.has("leedRegisterRating_tx") && !ESresults.isNull("leedRegisterRating_tx")){
			ESleedRegRating = ESresults.getString("leedRegisterRating_tx");
		} else { ESleedRegRating = ""; }
		UIleedRegRating = propDetails.returnLeedRegRating();
		strs.add(new PropDetails(String.valueOf(caseID), "Leed Registered Rating", ESleedRegRating, UIleedRegRating));
	
		//Leed Intended
		if (ESresults.has("leedIntend_fg")) {
			ESleedIntended = ESresults.getBoolean("leedIntend_fg");
		} 
		UIleedIntended = propDetails.returnLeedIntended();
		bool.add(new PropDetails(String.valueOf(caseID), "Leed Intended", ESleedIntended, UIleedIntended));

		//Leed Intended Rating
		if (ESresults.has("leedIntendRating_tx") && !ESresults.isNull("leedIntendRating_tx")){
			ESleedIntendedRating = ESresults.getString("leedIntendRating_tx");
		} else { ESleedIntendedRating = ""; }
		UIleedIntendedRating = propDetails.returnLeedIntRating();
		strs.add(new PropDetails(String.valueOf(caseID), "Leed Intended Rating", ESleedIntendedRating, UIleedIntendedRating));
		
		CSV.appendToFileExcel("dodgeSearch" + RepoSmokeTest.currentDateEnv, strs);
		CSV.appendToFileExcelNums("dodgeSearch" + RepoSmokeTest.currentDateEnv, nums);
		CSV.appendToFileExcelBools("dodgeSearch" + RepoSmokeTest.currentDateEnv, bool);
	}
	
	public static void getDodgeCompanyDetails(JSONObject ESresults, DodgeAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> results = new ArrayList<PropDetails>(); 

		System.out.println("Validating Construction Company Details Data");
		
		//ES
		String ESownerName = "", ESownerAdd = "", ESownerCity = "", ESownerState = "", ESownerPostalCode = "", ESownerWebsite = "";  
		
		//UI
		String UIownerName = "", UIownerAdd = "", UIownerCity = "", UIownerState = "", UIownerPostalCode = "", UIownerWebsite = "";  
		
		if (ESresults.has("owner_json") && !ESresults.isNull("owner_json")) {
			int numOwners = ESresults.getJSONArray("owner_json").length();
			if (numOwners > 2) { numOwners = 2; }
			for (int i = 1; i <= numOwners; i++) {
				JSONObject owner_json = ESresults.getJSONArray("owner_json").getJSONObject(i-1);
				//if ES has 2 owners, validate Owner 2 label is displayed
				if (i == 2) {
					Assert.assertTrue(propDetails.isOwner2Displayed());
				}
				//Company Owner Name
				if (owner_json.has("name_tx") && !owner_json.isNull("name_tx")) {
					ESownerName = owner_json.getString("name_tx");
				}
				if (i == 1) {
					UIownerName = propDetails.returnOwner1Name();
				} else {
					UIownerName = propDetails.returnOwner2Name();
				}
				results.add(new PropDetails(String.valueOf(caseID), "Company Owner "+i+" Name", ESownerName, UIownerName));
				
				//Company Address
				if (owner_json.has("addr1_tx") && !owner_json.isNull("addr1_tx")) {
					ESownerAdd = owner_json.getString("addr1_tx"); 
				}
				if (i == 1) {
					UIownerAdd = propDetails.returnOwner1Add();
				} else {
					UIownerAdd = propDetails.returnOwner2Add();
				}
				results.add(new PropDetails(String.valueOf(caseID), "Company Owner "+i+" Address", ESownerAdd, UIownerAdd));
				
				//Company City
				if (owner_json.has("city_tx") && !owner_json.isNull("city_tx")) {
					ESownerCity = owner_json.getString("city_tx"); 
				} 
				if (i == 1) {
					UIownerCity = propDetails.returnOwner1City();
				} else {
					UIownerCity = propDetails.returnOwner2City();
				}
				results.add(new PropDetails(String.valueOf(caseID), "Company Owner "+i+" City", ESownerCity, UIownerCity));
				
				//Company State
				if (owner_json.has("state_tx") && !owner_json.isNull("state_tx")) {
					ESownerState = owner_json.getString("state_tx"); 
				} 
				if (i == 1) {
					UIownerState = propDetails.returnOwner1State();
				} else {
					UIownerState = propDetails.returnOwner2State();
				}
				results.add(new PropDetails(String.valueOf(caseID), "Company Owner "+i+" State", ESownerState, UIownerState));
				
				//Company Postal Code
				if (owner_json.has("zip_tx") && !owner_json.isNull("zip_tx")) {
					ESownerPostalCode = owner_json.getString("zip_tx"); 
				} 
				if (i == 1) {
					UIownerPostalCode = propDetails.returnOwner1PostalCode();
				} else {
					UIownerPostalCode = propDetails.returnOwner2PostalCode();
				}
				results.add(new PropDetails(String.valueOf(caseID), "Company Owner "+i+" Postal Code", ESownerPostalCode, UIownerPostalCode));
				
				//Company Website
				if (owner_json.has("web_tx") && !owner_json.isNull("web_tx")) {
					ESownerWebsite = owner_json.getString("web_tx").trim(); 
				} 
				if (i == 1 && propDetails.isOwner1WebsiteExist()) {
					UIownerWebsite = propDetails.returnOwner1Website();
				} else if (i == 2 && propDetails.isOwner2WebsiteExist()){
					UIownerWebsite = propDetails.returnOwner2Website().trim();
				} else {
					UIownerWebsite = "";
				}
				results.add(new PropDetails(String.valueOf(caseID), "Company Owner "+i+" Website", ESownerWebsite, UIownerWebsite));	
			}
		CSV.appendToFileExcel("dodgeSearch" + RepoSmokeTest.currentDateEnv, results);
		}
	}
	
	public static void getDodgeConstructionDetails(JSONObject ESresults, DodgeAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> nums = new ArrayList<PropDetails>(); 
		List<PropDetails> str = new ArrayList<PropDetails>();
		
		System.out.println("Validating Construction Details Data");
		
		//ES
		String ESreportDate, ESstage, ESestStartDate, ESestCompDate, ESstatus, ESconstDetails, ESfinalDate, ESfinalStatus, ESstartDate, ESstartStatus; 
		int ESversion, ESsourceExtractId;
		
		//UI
		String UIreportDate, UIstage, UIestStartDate, UIestCompDate, UIstatus, UIconstDetails, UIfinalDate, UIfinalStatus, UIstartDate, UIstartStatus; 
		int UIversion, UIsourceExtractId;
		
		//Version
		if (ESresults.has("version_nb") && !ESresults.isNull("version_nb")){
			ESversion = ESresults.getInt("version_nb");
		} else { ESversion = 0; }
		if (!propDetails.returnVersion().isEmpty()) {
			UIversion = Integer.valueOf(propDetails.returnVersion());
		} else { UIversion = 0;	}
		nums.add(new PropDetails(String.valueOf(caseID), "Version", ESversion, UIversion));
		
		//Source Extract ID
		if (ESresults.has("sourceExtract_id") && !ESresults.isNull("sourceExtract_id")){
			ESsourceExtractId = ESresults.getInt("sourceExtract_id");
		} else { ESsourceExtractId = 0; }
		UIsourceExtractId = Integer.valueOf(propDetails.returnSourceExtractId());
		nums.add(new PropDetails(String.valueOf(caseID), "Source Extract ID", ESsourceExtractId, UIsourceExtractId));
		
		//Report Date
		if (ESresults.has("issue_dt") && !ESresults.isNull("issue_dt")) {
			ESreportDate = ESresults.getString("issue_dt");
			String year = ESreportDate.substring(0, 4);
			String month = ESreportDate.substring(5, 7);
			String date = ESreportDate.substring(8, 10);
			ESreportDate = month + "/" + date + "/" + year;
		} else { ESreportDate = ""; }
		UIreportDate = propDetails.returnReportDate();
		str.add(new PropDetails(String.valueOf(caseID), "Report Date", ESreportDate, UIreportDate));
		
		//Stage
		if (ESresults.has("stage_tx") && !ESresults.isNull("stage_tx")) {
			ESstage = ESresults.getString("stage_tx");
		} else { ESstage = ""; }
		UIstage = propDetails.returnStage();
		str.add(new PropDetails(String.valueOf(caseID), "Stage", ESstage, UIstage));
		
		//Est Start Date
		if (ESresults.has("targetStart_dt") && !ESresults.isNull("targetStart_dt")) {
			ESestStartDate = ESresults.getString("targetStart_dt");
			String year = ESestStartDate.substring(0, 4);
			String month = ESestStartDate.substring(5, 7);
			String date = ESestStartDate.substring(8, 10);
			ESestStartDate = month + "/" + date + "/" + year;
		} else { ESestStartDate = ""; }
		UIestStartDate = propDetails.returnEstStartDate();
		str.add(new PropDetails(String.valueOf(caseID), "Est Start Date", ESestStartDate, UIestStartDate));
		
		//Est Completion Date
		if (ESresults.has("targetCompltn_dt") && !ESresults.isNull("targetCompltn_dt")) {
			ESestCompDate = ESresults.getString("targetCompltn_dt");
			String year = ESestCompDate.substring(0, 4);
			String month = ESestCompDate.substring(5, 7);
			String date = ESestCompDate.substring(8, 10);
			ESestCompDate = month + "/" + date + "/" + year;
		} else { ESestCompDate = ""; }
		UIestCompDate = propDetails.returnEstCompDate();
		str.add(new PropDetails(String.valueOf(caseID), "Est Comp Date", ESestCompDate, UIestCompDate));
		
		//Status
		if (ESresults.has("status_tx") && !ESresults.isNull("status_tx")) {
			ESstatus = ESresults.getString("status_tx");
		} else { ESstatus = ""; }
		UIstatus = propDetails.returnStatus();
		str.add(new PropDetails(String.valueOf(caseID), "Status", ESstatus, UIstatus));
		
		//Construction Details
		if (ESresults.has("constructionDetails_tx") && !ESresults.isNull("constructionDetails_tx")) {
			ESconstDetails = ESresults.getString("constructionDetails_tx");
		} else { ESconstDetails = ""; }
		UIconstDetails = propDetails.returnConstDetails();
		str.add(new PropDetails(String.valueOf(caseID), "Construction Details", ESconstDetails, UIconstDetails));
		
		//Final Date
		if (ESresults.has("final_dt") && !ESresults.isNull("final_dt")) {
			ESfinalDate = ESresults.getString("final_dt");
			String year = ESfinalDate.substring(0, 4);
			String month = ESfinalDate.substring(5, 7);
			String date = ESfinalDate.substring(8, 10);
			ESfinalDate = month + "/" + date + "/" + year;
		} else { ESfinalDate = ""; }
		UIfinalDate = propDetails.returnFinalDate();
		str.add(new PropDetails(String.valueOf(caseID), "Final Date", ESfinalDate, UIfinalDate));
		
		//Final Status
		if (ESresults.has("finalStatus_tx") && !ESresults.isNull("finalStatus_tx")) {
			ESfinalStatus = ESresults.getString("finalStatus_tx");
		} else { ESfinalStatus = ""; }
		UIfinalStatus = propDetails.returnFinalStatus();
		str.add(new PropDetails(String.valueOf(caseID), "Final Status", ESfinalStatus, UIfinalStatus));
		
		//Start Date
		if (ESresults.has("start_dt") && !ESresults.isNull("start_dt")) {
			ESstartDate = ESresults.getString("start_dt");
			String year = ESstartDate.substring(0, 4);
			String month = ESstartDate.substring(5, 7);
			String date = ESstartDate.substring(8, 10);
			ESstartDate = month + "/" + date + "/" + year;
		} else { ESstartDate = ""; }
		UIstartDate = propDetails.returnStartDate();
		str.add(new PropDetails(String.valueOf(caseID), "Start Date", ESstartDate, UIstartDate));
		
		//Start Status
		if (ESresults.has("startStatus_tx") && !ESresults.isNull("startStatus_tx")) {
			ESstartStatus = ESresults.getString("startStatus_tx");
		} else { ESstartStatus = ""; }
		UIstartStatus = propDetails.returnStartStatus();
		str.add(new PropDetails(String.valueOf(caseID), "Start Status", ESstartStatus, UIstartStatus));
	
		CSV.appendToFileExcelNums("dodgeSearch" + RepoSmokeTest.currentDateEnv, nums);
		CSV.appendToFileExcel("dodgeSearch" + RepoSmokeTest.currentDateEnv, str);
	
	}
	
}
