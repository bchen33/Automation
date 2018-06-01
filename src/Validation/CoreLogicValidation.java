package Validation;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import PageObjects.CoreLogicAdditionalDetailsPage;
import PageObjects.DodgeAdditionalDetailsPage;
import Utility.CSV;
import Utility.PropDetails;
import test.java.RepoSmokeTest;

public class CoreLogicValidation {
	
	public static void getCoreLogicPropDetailResults(JSONObject ESresults, CoreLogicAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> header = new ArrayList<PropDetails>();
		//check for header
		String ESpropNameHeader, UIpropNameHeader = "";
		if (ESresults.has("propertyAddressStd_tx") && !ESresults.isNull("propertyAddressStd_tx")) {
			ESpropNameHeader = ESresults.getString("propertyAddressStd_tx");
			UIpropNameHeader = propDetails.returnPropNameHeader();
			header.add(new PropDetails(String.valueOf(caseID), "Property Address", ESpropNameHeader, UIpropNameHeader));
		}
		CSV.appendToFileExcel("titleSearch" + RepoSmokeTest.currentDateEnv, header);
		try {
			getCLLocationDetails(ESresults, propDetails, caseID);
			getCLPropDetails(ESresults, propDetails, caseID);
			getCLTaxDetails(ESresults, propDetails, caseID);
			getCLOwnerBuyerDetails(ESresults, propDetails, caseID);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void getCLLocationDetails(JSONObject ESresults, CoreLogicAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> results = new ArrayList<PropDetails>(); 
		
		//ES
		String ESadd1, EScity, ESstate, ESpostalCode, EScounty, ESFIPS, ESAPN, ESoriginalAPN; 
		String ESlat, ESlong, ESlatLong, ESzoning, ESsubdivisionName, EStownship;
		
		//UI
		String UIadd1, UIcity, UIstate, UIpostalCode, UIcounty, UIFIPS, UIAPN, UIoriginalAPN; 
		String UIlatLong, UIzoning, UIsubdivisionName, UItownship;
		
		System.out.println("Validating CoreLogic Location Data");
		
		//Address 1
		if (ESresults.has("propertyAddressStd_tx") && !ESresults.isNull("propertyAddressStd_tx")){
			ESadd1 = ESresults.getString("propertyAddressStd_tx");
		} else { ESadd1 = ""; }
		UIadd1 = propDetails.returnAdd1();
		results.add(new PropDetails(String.valueOf(caseID), "Address 1", ESadd1, UIadd1));

		//City
		if (ESresults.has("propertyCity_tx") && !ESresults.isNull("propertyCity_tx")){
			EScity = ESresults.getString("propertyCity_tx");
		} else { EScity = ""; }
		UIcity = propDetails.returnCity();
		results.add(new PropDetails(String.valueOf(caseID), "City", EScity, UIcity));
		
		//State
		if (ESresults.has("propertyState_cd") && !ESresults.isNull("propertyState_cd")){
			ESstate = ESresults.getString("propertyState_cd");
		} else { ESstate = ""; }
		UIstate = propDetails.returnState();
		results.add(new PropDetails(String.valueOf(caseID), "State", ESstate, UIstate));
		
		//PostalCode
		if (ESresults.has("propertyZipCode_tx") && !ESresults.isNull("propertyZipCode_tx")){
			ESpostalCode = ESresults.getString("propertyZipCode_tx");
			String firstfive = ESpostalCode.substring(0, 5);
			String lastfour = ESpostalCode.substring(5);
			if (!lastfour.isEmpty()) {
				ESpostalCode = firstfive + "-" + lastfour;
			} else { ESpostalCode = firstfive; }
		} else { ESpostalCode = ""; }
		UIpostalCode = propDetails.returnPostalCode();
		results.add(new PropDetails(String.valueOf(caseID), "Postal Code", ESpostalCode, UIpostalCode));
		
		//County
		if (ESresults.has("municipalityName_tx") && !ESresults.isNull("municipalityName_tx")){
			EScounty = ESresults.getString("municipalityName_tx");
		} else { EScounty = "";}
		UIcounty = propDetails.returnCounty();
		results.add(new PropDetails(String.valueOf(caseID), "County", EScounty, UIcounty));
		
		//FIPS Code
		if (ESresults.has("fips_tx") && !ESresults.isNull("fips_tx")){
			ESFIPS = ESresults.getString("fips_tx");
		} else { ESFIPS = "";}
		UIFIPS = propDetails.returnFIPSCode();
		results.add(new PropDetails(String.valueOf(caseID), "FIPS Code", ESFIPS, UIFIPS));
		
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

		//APN
		if (ESresults.has("apn_tx") && !ESresults.isNull("apn_tx")){
			ESAPN = ESresults.getString("apn_tx");
		} else { ESAPN = "";}
		UIAPN = propDetails.returnAPN();
		results.add(new PropDetails(String.valueOf(caseID), "APN", ESAPN, UIAPN));
		
		//Original APN
		if (ESresults.has("apnPrevious_tx") && !ESresults.isNull("apnPrevious_tx")){
			ESoriginalAPN = ESresults.getString("apnPrevious_tx");
		} else { ESoriginalAPN = "";}
		UIoriginalAPN = propDetails.returnOriginalAPN();
		results.add(new PropDetails(String.valueOf(caseID), "Original APN", ESoriginalAPN, UIoriginalAPN));
		
		//Zoning
		if (ESresults.has("zoning_tx") && !ESresults.isNull("zoning_tx")){
			ESzoning = ESresults.getString("zoning_tx");
		} else { ESzoning = "";}
		UIzoning = propDetails.returnZoning();
		results.add(new PropDetails(String.valueOf(caseID), "Zoning", ESzoning, UIzoning));
		
		//Subdivision Name
		if (ESresults.has("subdivisionName_tx") && !ESresults.isNull("subdivisionName_tx")){
			ESsubdivisionName = ESresults.getString("subdivisionName_tx");
		} else { ESsubdivisionName = "";}
		UIsubdivisionName = propDetails.returnSubdivisionName();
		results.add(new PropDetails(String.valueOf(caseID), "Subdivision Name", ESsubdivisionName, UIsubdivisionName));
		
		//Township
		if (ESresults.has("township_tx") && !ESresults.isNull("township_tx")){
			EStownship = ESresults.getString("township_tx");
		} else { EStownship = "";}
		UItownship = propDetails.returnTownship();
		results.add(new PropDetails(String.valueOf(caseID), "Township", EStownship, UItownship));
		
		CSV.appendToFileExcel("titleSearch" + RepoSmokeTest.currentDateEnv, results);
	}
	
	public static void getCLPropDetails(JSONObject ESresults, CoreLogicAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> strs = new ArrayList<PropDetails>(); 
		List<PropDetails> nums = new ArrayList<PropDetails>();
		
		System.out.println("Validating CoreLogic Property Data");
		
		//ES
		String ESpropType, ESmasterTaxID; 
		int ESvalue, ESunits, ESfloors, ESbuildings, ESsqFt, EStotalSqFt, ESlandSqFt, EStotalRooms, ESyearBuilt, ESyearRenov;
		
		//UI
		String UIpropType, UImasterTaxID; 
		int UIvalue, UIunits, UIfloors, UIbuildings, UIsqFt, UItotalSqFt, UIlandSqFt, UItotalRooms, UIyearBuilt, UIyearRenov;
		
		//Prop Type
		if (ESresults.has("propertyType_tx") && !ESresults.isNull("propertyType_tx")){
			ESpropType = ESresults.getString("propertyType_tx");
		} else { ESpropType = ""; }
		UIpropType = propDetails.returnPropType();
		strs.add(new PropDetails(String.valueOf(caseID), "Prop Type", ESpropType, UIpropType));
				
		//Value
		if (ESresults.has("assdTotalValueAmount_nb") && !ESresults.isNull("assdTotalValueAmount_nb")){
			ESvalue = ESresults.getInt("assdTotalValueAmount_nb");
		} else { ESvalue = 0; }
		if (!propDetails.returnValue().isEmpty()){
			UIvalue = Integer.valueOf(propDetails.returnValue().replaceAll("\\$", "").replaceAll(",", ""));
		} else { UIvalue = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Value", ESvalue, UIvalue));
		
		//Units
		if (ESresults.has("units_nb") && !ESresults.isNull("units_nb")){
			ESunits = ESresults.getInt("units_nb");
		} else { ESunits = 0; }
		if (!propDetails.returnUnits().equals("N/A")) {
			UIunits = Integer.valueOf(propDetails.returnUnits().replaceAll(",", ""));
		} else { UIunits = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Units", ESunits, UIunits));
		
		//Floors
		if (ESresults.has("stories_dbl") && !ESresults.isNull("stories_dbl")){
			ESfloors = ESresults.getInt("stories_dbl");
		} else { ESfloors = 0; }
		if (!propDetails.returnFloors().equals("N/A")) {
			UIfloors = Integer.valueOf(propDetails.returnFloors().replaceAll(",", ""));
		} else { UIfloors = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Floors", ESfloors, UIfloors));
		
		//Buildings
		if (ESresults.has("totalBldgeUnitsOnParcel_nb") && !ESresults.isNull("totalBldgeUnitsOnParcel_nb")){
			ESbuildings = ESresults.getInt("totalBldgeUnitsOnParcel_nb");
		} else { ESbuildings = 0; }
		if (!propDetails.returnBuildings().equals("N/A")) {
			UIbuildings = Integer.valueOf(propDetails.returnBuildings().replaceAll(",", ""));
		} else { UIbuildings = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Buildings", ESbuildings, UIbuildings));
		
		//SqFt
		if (ESresults.has("buildingSquareFeet_nb") && !ESresults.isNull("buildingSquareFeet_nb")){
			ESsqFt = ESresults.getInt("buildingSquareFeet_nb");
		} else { ESsqFt = 0; }
		if (!propDetails.returnSqFt().equals("N/A")) {
			UIsqFt = Integer.valueOf(propDetails.returnSqFt().replaceAll(",", ""));
		} else { UIsqFt = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "SqFt", ESsqFt, UIsqFt));
		
		//Total SqFt
		if (ESresults.has("universalBuildingSquareFeet_nb") && !ESresults.isNull("universalBuildingSquareFeet_nb")){
			EStotalSqFt = ESresults.getInt("universalBuildingSquareFeet_nb");
		} else { EStotalSqFt = 0; }
		if (!propDetails.returnTotalSqFt().equals("N/A")) {
			UItotalSqFt = Integer.valueOf(propDetails.returnTotalSqFt().replaceAll(",", ""));
		} else { UItotalSqFt = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Total SqFt", EStotalSqFt, UItotalSqFt));
		
		//Land SqFt
		if (ESresults.has("landSquareFootage_nb") && !ESresults.isNull("landSquareFootage_nb")){
			ESlandSqFt = ESresults.getInt("landSquareFootage_nb");
		} else { ESlandSqFt = 0; }
		if (!propDetails.returnLandSqFt().equals("N/A")) {
			UIlandSqFt = Integer.valueOf(propDetails.returnLandSqFt().replaceAll(",", ""));
		} else { UIlandSqFt = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Land SqFt", ESlandSqFt, UIlandSqFt));
		
		//Total Rooms
		if (ESresults.has("totalRooms_nb") && !ESresults.isNull("totalRooms_nb")){
			EStotalRooms = ESresults.getInt("totalRooms_nb");
		} else { EStotalRooms = 0; }
		if (!propDetails.returnTotalRooms().equals("N/A")) {
			UItotalRooms = Integer.valueOf(propDetails.returnTotalRooms().replaceAll(",", ""));
		} else { UItotalRooms = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Total Rooms", EStotalRooms, UItotalRooms));
		
		//Year Built
		if (ESresults.has("yearBuilt_nb") && !ESresults.isNull("yearBuilt_nb")){
			ESyearBuilt = ESresults.getInt("yearBuilt_nb");
		} else { ESyearBuilt = 0; }
		if (!propDetails.returnYearBuilt().equals("N/A")) {
			UIyearBuilt = Integer.valueOf(propDetails.returnYearBuilt());
		} else { UIyearBuilt = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Year Built", ESyearBuilt, UIyearBuilt));
		
		//Year Renovated
		if (ESresults.has("yearRenovated_nb") && !ESresults.isNull("yearRenovated_nb")){
			ESyearRenov = ESresults.getInt("yearRenovated_nb");
		} else { ESyearRenov = 0; }
		if (!propDetails.returnYearRenov().equals("N/A")) {
			UIyearRenov = Integer.valueOf(propDetails.returnYearRenov());
		} else { UIyearRenov = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Year Renov", ESyearRenov, UIyearRenov));
		
		//Master Tax ID
		if (ESresults.has("vendorRecord_id") && !ESresults.isNull("vendorRecord_id")){
			ESmasterTaxID = ESresults.get("vendorRecord_id").toString();
		} else { ESmasterTaxID = ""; }
		UImasterTaxID = propDetails.returnMasterTaxID();
		strs.add(new PropDetails(String.valueOf(caseID), "Master Tax ID", ESmasterTaxID, UImasterTaxID));
		
		CSV.appendToFileExcel("titleSearch" + RepoSmokeTest.currentDateEnv, strs);
		CSV.appendToFileExcelNums("titleSearch" + RepoSmokeTest.currentDateEnv, nums);
	}
	
	public static void getCLTaxDetails(JSONObject ESresults, CoreLogicAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> strs = new ArrayList<PropDetails>(); 
		List<PropDetails> nums = new ArrayList<PropDetails>();

		System.out.println("Validating CoreLogic Tax Data");
		
		//ES
		double EStaxAmount;
		int EStaxYear, ESassdTotalValue, ESassdLandValue, ESassdImprovValue, ESassdYear, ESlotArea;  
		String EScensusTrack, ESblockNumber, ESlotNumber, ESlegalDescription;
		
		//UI
		double UItaxAmount;
		int UItaxYear, UIassdTotalValue, UIassdLandValue, UIassdImprovValue, UIassdYear, UIlotArea;  
		String UIcensusTrack, UIblockNumber, UIlotNumber, UIlegalDescription; 
		
		//Tax Amount
		if (ESresults.has("taxAmount_dbl") && !ESresults.isNull("taxAmount_dbl")){
			EStaxAmount = ESresults.getDouble("taxAmount_dbl");
		} else { EStaxAmount = 0; }
		if (!propDetails.returnTaxAmount().isEmpty()){
			UItaxAmount = Integer.valueOf(propDetails.returnTaxAmount().replaceAll("\\$", "").replaceAll(",", ""));
		} else { UItaxAmount = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Tax Amount", EStaxAmount, UItaxAmount));
		
		//Tax Year
		if (ESresults.has("taxYear_nb") && !ESresults.isNull("taxYear_nb")){
			EStaxYear = ESresults.getInt("taxYear_nb");
		} else { EStaxYear = 0; }
		if (!propDetails.returnTaxYear().equals("N/A")) {
			UItaxYear = Integer.valueOf(propDetails.returnTaxYear());
		} else { UItaxYear = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Tax Year", EStaxYear, UItaxYear));
		
		//Assd Total Value
		if (ESresults.has("assdTotalValueAmount_nb") && !ESresults.isNull("assdTotalValueAmount_nb")){
			ESassdTotalValue = ESresults.getInt("assdTotalValueAmount_nb");
		} else { ESassdTotalValue = 0; }
		if (!propDetails.returnAssdTotalValue().isEmpty()){
			UIassdTotalValue = Integer.valueOf(propDetails.returnAssdTotalValue().replaceAll("\\$", "").replaceAll(",", ""));
		} else { UIassdTotalValue = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Assd Total Value", ESassdTotalValue, UIassdTotalValue));
		
		//Assd Land Value
		if (ESresults.has("assdLandValueAmount_nb") && !ESresults.isNull("assdLandValueAmount_nb")){
			ESassdLandValue = ESresults.getInt("assdLandValueAmount_nb");
		} else { ESassdLandValue = 0; }
		if (!propDetails.returnAssdLandValue().isEmpty()){
			UIassdLandValue = Integer.valueOf(propDetails.returnAssdLandValue().replaceAll("\\$", "").replaceAll(",", ""));
		} else { UIassdLandValue = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Assd Land Value", ESassdLandValue, UIassdLandValue));
		
		//Assd Improv Value
		if (ESresults.has("assdImprovementValueAmount_nb") && !ESresults.isNull("assdImprovementValueAmount_nb")){
			ESassdImprovValue = ESresults.getInt("assdImprovementValueAmount_nb");
		} else { ESassdImprovValue = 0; }
		if (!propDetails.returnAssdImprovValue().isEmpty()){
			UIassdImprovValue = Integer.valueOf(propDetails.returnAssdImprovValue().replaceAll("\\$", "").replaceAll(",", ""));
		} else { UIassdImprovValue = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Assd Improv Value", ESassdImprovValue, UIassdImprovValue));
		
		//Assd Year
		if (ESresults.has("assessedYear_nb") && !ESresults.isNull("assessedYear_nb")){
			ESassdYear = ESresults.getInt("assessedYear_nb");
		} else { ESassdYear = 0; }
		if (!propDetails.returnAssdYear().equals("N/A")) {
			UIassdYear = Integer.valueOf(propDetails.returnAssdYear());
		} else { UIassdYear = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Assd Year", ESassdYear, UIassdYear));
		
		//Census Track
		if (ESresults.has("censusTract_tx") && !ESresults.isNull("censusTract_tx")){
			EScensusTrack = ESresults.getString("censusTract_tx");
		} else { EScensusTrack = ""; }
		if (!propDetails.returnCensusTrack().isEmpty()){
			UIcensusTrack = propDetails.returnCensusTrack();
		} else { UIcensusTrack = ""; }
		strs.add(new PropDetails(String.valueOf(caseID), "Census Track", EScensusTrack, UIcensusTrack));
		
		//Block Number
		if (ESresults.has("blockNumber_tx") && !ESresults.isNull("blockNumber_tx")){
			ESblockNumber = ESresults.getString("blockNumber_tx");
		} else { ESblockNumber = ""; }
		if (!propDetails.returnBlockNumber().isEmpty()){
			UIblockNumber = propDetails.returnBlockNumber();
		} else { UIblockNumber = ""; }
		strs.add(new PropDetails(String.valueOf(caseID), "Block Number", ESblockNumber, UIblockNumber));
		
		//Lot Number
		if (ESresults.has("lotNumber_tx") && !ESresults.isNull("lotNumber_tx")){
			ESlotNumber = ESresults.getString("lotNumber_tx");
		} else { ESlotNumber = ""; }
		if (!propDetails.returnLotNumber().isEmpty()){
			UIlotNumber = propDetails.returnLotNumber();
		} else { UIlotNumber = ""; }
		strs.add(new PropDetails(String.valueOf(caseID), "Lot Number", ESlotNumber, UIlotNumber));
		
		//Lot Area
		if (ESresults.has("landSquareFootage_nb") && !ESresults.isNull("landSquareFootage_nb")){
			ESlotArea = ESresults.getInt("landSquareFootage_nb");
		} else { ESlotArea = 0; }
		if (!propDetails.returnLotArea().equals("N/A")) {
			UIlotArea = Integer.valueOf(propDetails.returnLotArea().replaceAll(",", ""));
		} else { UIlotArea = 0; }
		nums.add(new PropDetails(String.valueOf(caseID), "Lot Area", ESlotArea, UIlotArea));
		
		//Legal Description
		if (ESresults.has("legalDescription_tx") && !ESresults.isNull("legalDescription_tx")){
			ESlegalDescription = ESresults.getString("legalDescription_tx");
		} else { ESlegalDescription = ""; }
		if (!propDetails.returnLegalDescription().isEmpty()){
			UIlegalDescription = propDetails.returnLegalDescription();
		} else { UIlegalDescription = ""; }
		strs.add(new PropDetails(String.valueOf(caseID), "Legal Description", ESlegalDescription, UIlegalDescription));
		
		CSV.appendToFileExcel("titleSearch" + RepoSmokeTest.currentDateEnv, strs);
		CSV.appendToFileExcelNums("titleSearch" + RepoSmokeTest.currentDateEnv, nums);
	}
	
	public static void getCLOwnerBuyerDetails(JSONObject ESresults, CoreLogicAdditionalDetailsPage propDetails, int caseID) throws InterruptedException {
		List<PropDetails> results = new ArrayList<PropDetails>(); 
		
		System.out.println("Validating CoreLogic Owner/Buyer Data");
		//ES
		String ESowner1Name, ESowner1HouseNumber, ESowner1StreetName, ESowner1Mode, ESowner1UnitNumber, ESowner1Address, ESowner1City, ESowner1State, ESowner1PostalCode, ESowner2Name; 
		
		//UI
		String UIowner1Name, UIowner1Address, UIowner1City, UIowner1State, UIowner1PostalCode, UIowner2Name; 
		
		//Owner 1 Name
		if (ESresults.has("ownerBuyerName1_tx") && !ESresults.isNull("ownerBuyerName1_tx")){
			ESowner1Name = ESresults.getString("ownerBuyerName1_tx");
		} else { ESowner1Name = ""; }
		if (!propDetails.returnOwner1Name().isEmpty()) {
			UIowner1Name = propDetails.returnOwner1Name();
		} else { UIowner1Name = "";	}
		results.add(new PropDetails(String.valueOf(caseID), "Owner 1 Name", ESowner1Name, UIowner1Name));
		
		//Owner 1 Address
		if (ESresults.has("mailHouseNumber_tx") && !ESresults.isNull("mailHouseNumber_tx")) {
			ESowner1HouseNumber = ESresults.getString("mailHouseNumber_tx");
			if (!ESowner1HouseNumber.isEmpty()) {
				ESowner1HouseNumber = ESowner1HouseNumber + " ";
			}
		} else { ESowner1HouseNumber = ""; }
		
		if (ESresults.has("mailStreetName_tx") && !ESresults.isNull("mailStreetName_tx")) {
			ESowner1StreetName = ESresults.getString("mailStreetName_tx");
			if (!ESowner1StreetName.isEmpty()) {
				ESowner1StreetName = ESowner1StreetName + " "; 
			}
		} else { ESowner1StreetName = ""; }
		
		if (ESresults.has("mailMode_tx") && !ESresults.isNull("mailMode_tx")) {
			ESowner1Mode = ESresults.getString("mailMode_tx");
		} else { ESowner1Mode = ""; }
		
		if (ESresults.has("mailUnitNumber_tx") && !ESresults.isNull("mailUnitNumber_tx")) {
			ESowner1UnitNumber = ESresults.getString("mailUnitNumber_tx");
			if (!ESowner1UnitNumber.isEmpty()) {
				ESowner1UnitNumber = " #" + ESowner1UnitNumber;
			}
		} else { ESowner1UnitNumber = ""; }
		
		ESowner1Address = (ESowner1HouseNumber + ESowner1StreetName + ESowner1Mode + ESowner1UnitNumber).replaceAll("  ", " "); 
		
		if (!propDetails.returnOwner1Address().isEmpty()) {
			UIowner1Address = propDetails.returnOwner1Address();
		} else { UIowner1Address = ""; }
		results.add(new PropDetails(String.valueOf(caseID), "Owner 1 Address", ESowner1Address, UIowner1Address));
				
		//Owner 1 City
		if (ESresults.has("mailCity_tx") && !ESresults.isNull("mailCity_tx")) {
			ESowner1City = ESresults.getString("mailCity_tx");
		} else { ESowner1City = ""; }
		if (!propDetails.returnOwner1City().isEmpty()) {
			UIowner1City = propDetails.returnOwner1City();
		} else { UIowner1City = ""; }
		results.add(new PropDetails(String.valueOf(caseID), "Owner 1 City", ESowner1City, UIowner1City));
		
		//Owner 1 State
		if (ESresults.has("mailState_cd") && !ESresults.isNull("mailState_cd")) {
			ESowner1State = ESresults.getString("mailState_cd");
		} else { ESowner1State = ""; }
		if (!propDetails.returnOwner1State().isEmpty()) {
			UIowner1State = propDetails.returnOwner1State();
		} else { UIowner1State = ""; }
		results.add(new PropDetails(String.valueOf(caseID), "Owner 1 State", ESowner1State, UIowner1State));
	
		//Owner 1 Postal Code
		if (ESresults.has("mailZipCode_tx") && !ESresults.isNull("mailZipCode_tx")) {
			ESowner1PostalCode = ESresults.getString("mailZipCode_tx");
			String firstfive = ESowner1PostalCode.substring(0, 5);
			String lastfour = ESowner1PostalCode.substring(5);
			if (!lastfour.isEmpty()) {
				ESowner1PostalCode = firstfive + "-" + lastfour;
			} else { ESowner1PostalCode = firstfive; }
		} else { ESowner1PostalCode = ""; }
		if (!propDetails.returnOwner1PostalCode().isEmpty()) {
			UIowner1PostalCode = propDetails.returnOwner1PostalCode();
		} else { UIowner1PostalCode = ""; }
		results.add(new PropDetails(String.valueOf(caseID), "Owner 1 Postal Code", ESowner1PostalCode, UIowner1PostalCode));
		
		//Owner 2 Name
		if (ESresults.has("ownerBuyerName2_tx") && !ESresults.isNull("ownerBuyerName2_tx")){
			ESowner2Name = ESresults.getString("ownerBuyerName2_tx");
		} else { ESowner2Name = ""; }
		if (propDetails.isOwner2Exist()) {
			if (!propDetails.returnOwner2Name().isEmpty()) {
				UIowner2Name = propDetails.returnOwner2Name();
			} else { UIowner2Name = ""; }
		} else { UIowner2Name = "";	}
		results.add(new PropDetails(String.valueOf(caseID), "Owner 2 Name", ESowner2Name, UIowner2Name));
		
		CSV.appendToFileExcel("titleSearch" + RepoSmokeTest.currentDateEnv, results);
	}
}
