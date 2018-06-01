package Utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import Apps.*;
import test.java.RepoSearchTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSV { 
	
	public static void createFileExcel(String fileName){
		try{
			FileWriter writer = new FileWriter(fileName+".csv");
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("Field");
			writer.append(",");
			writer.append("ES Value");
			writer.append(",");
			writer.append("UI Value");
			writer.append(",");
			writer.append("Difference/Pass/Fail");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void createFileExcelFIPSCounts(String fileName){
		try{
			FileWriter writer = new FileWriter(fileName+".csv");
			writer.append("FIPS Code");
			writer.append(",");
			writer.append("Excel Value");
			writer.append(",");
			writer.append("ES Value");
			writer.append(",");
			writer.append("Difference/Pass/Fail");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void createFileExcelTransCounts(String fileName){
		try{
			FileWriter writer = new FileWriter(fileName+".csv");
			writer.append("Master Taxroll ID");
			writer.append(",");
			writer.append("Excel Value");
			writer.append(",");
			writer.append("ES Value");
			writer.append(",");
			writer.append("Difference/Pass/Fail");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileExcelFIPSCounts(String fileName,List<FIPSCountResults> values){
		try{
			FileWriter writer = new FileWriter(fileName+".csv", true);
			for(int i = 0; i < values.size();i++){
				writer.append(((String) values.get(i).FIPSCode));
				writer.append(",");
				writer.append("\""+ values.get(i).exCount +"\"");
				writer.append(",");
				writer.append("\""+ values.get(i).esCount +"\"");
				writer.append(",");
				writer.append(isDifferent(values.get(i).exCount,values.get(i).esCount));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileExcelTransCounts(String fileName,List<TransCountResults> values){
		try{
			FileWriter writer = new FileWriter(fileName+".csv", true);
			for(int i = 0; i < values.size();i++){
				writer.append(((String) values.get(i).MasterTaxRollId));
				writer.append(",");
				writer.append("\""+ values.get(i).exCount +"\"");
				writer.append(",");
				writer.append("\""+ values.get(i).esCount +"\"");
				writer.append(",");
				writer.append(isDifferent(values.get(i).exCount,values.get(i).esCount));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileExcel(String fileName,List<PropDetails> values){
		try{
			FileWriter writer = new FileWriter(fileName+".csv", true);
			for(int i = 0; i < values.size();i++){
				writer.append(((String) values.get(i).tcID));
				writer.append(",");
				writer.append("\""+ values.get(i).field +"\"");
				writer.append(",");
				writer.append("\""+ values.get(i).esValue +"\"");
				writer.append(",");
				writer.append("\""+ values.get(i).uiValue +"\"");
				writer.append(",");
				writer.append(isDifferent(values.get(i).esValue,values.get(i).uiValue));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileExcelNums(String fileName,List<PropDetails> values){
		try{
			FileWriter writer = new FileWriter(fileName+".csv", true);
			for(int i = 0; i < values.size();i++){
				writer.append(((String) values.get(i).tcID));
				writer.append(",");
				writer.append("\""+ values.get(i).field +"\"");
				writer.append(",");
				writer.append("\""+ values.get(i).esDblValue +"\"");
				writer.append(",");
				writer.append("\""+ values.get(i).uiDblValue +"\"");
				writer.append(",");
				writer.append(String.valueOf(percentDifference(values.get(i).esDblValue,values.get(i).uiDblValue)));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileExcelBools(String fileName,List<PropDetails> values){
		try{
			FileWriter writer = new FileWriter(fileName+".csv", true);
			for(int i = 0; i < values.size();i++){
				writer.append(((String) values.get(i).tcID));
				writer.append(",");
				writer.append("\""+ values.get(i).field +"\"");
				writer.append(",");
				writer.append("\""+ values.get(i).esBoolValue +"\"");
				writer.append(",");
				writer.append("\""+ values.get(i).uiBoolValue +"\"");
				writer.append(",");
				writer.append(isDifferent(String.valueOf(values.get(i).esBoolValue),String.valueOf(values.get(i).uiBoolValue)));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	/*
	public static void createFileExcelIU(String fileName){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv");
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("Type");
			writer.append(",");
			writer.append("Row");
			writer.append(",");
			writer.append("Field");
			writer.append(",");
			writer.append("ES Value");
			writer.append(",");
			writer.append("Excel Value");
			writer.append(",");
			writer.append("Difference/Pass/Fail");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileExcelIUNums(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append("\""+ cValues.get(i).modeValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).rowValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).fieldValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).actualValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).calculatedValue +"\"");
				writer.append(",");
				writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileExcelIUStrings(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append("\""+ cValues.get(i).modeValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).rowValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).fieldValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).actualNameValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).calculatedNameValue +"\"");
				writer.append(",");
				writer.append(isDifferent(cValues.get(i).actualNameValue,cValues.get(i).calculatedNameValue));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileExcelStrings(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append("\""+ cValues.get(i).modeValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).fieldValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).actualNameValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).calculatedNameValue +"\"");
				writer.append(",");
				writer.append(isDifferent(cValues.get(i).actualNameValue,cValues.get(i).calculatedNameValue));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileExcelNums(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append("\""+ cValues.get(i).modeValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).fieldValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).actualValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).calculatedValue +"\"");
				writer.append(",");
				writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileSales(String fileName,List<CountryValues> cValues,String nodeType, double absThresh, double perThresh){
		try{
			FileWriter writer = new FileWriter(fileName+".csv", true);

			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append(nodeType);
				writer.append(",");
				writer.append(((String) cValues.get(i).quarterDate).split("T")[0]);
				writer.append(",");
				writer.append("\""+cValues.get(i).actualValue+"\"");
				writer.append(",");
				writer.append("\""+cValues.get(i).calculatedValue+"\"");
				writer.append(",");
				writer.append(String.valueOf(absoluteDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)));
				writer.append(",");
				writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
				writer.append(",");
				writer.append(String.valueOf(isPassing(cValues.get(i).actualValue,cValues.get(i).calculatedValue,absThresh,perThresh)));
				writer.append("\n");
				
			}
			
			writer.flush();
			writer.close();
		}catch(IOException  e){
			e.printStackTrace();
		}
	}
	
	public static void createFileSales(String fileName, String non){
		try{
			FileWriter writer = new FileWriter(fileName+".csv");
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("Data Type");
			writer.append(",");
			writer.append("Quarter");
			writer.append(",");
			writer.append("Actual Value");
			writer.append(",");
			writer.append("Calculated Value");
			writer.append(",");
			writer.append("Absolute Difference");
			writer.append(",");
			writer.append("Percent Difference");
			writer.append(",");
			writer.append("Pass / Fail");
			writer.append("\n");

			writer.flush();
			writer.close();
		}catch(IOException  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileSales(String fileName,List<CountryValues> cValues,boolean numberFormat, double absThresh, double perThresh){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			if(numberFormat){
				for(int i = 0; i < cValues.size();i++){
					writer.append(((String) cValues.get(i).testCaseId));
					writer.append(",");
					writer.append(((String) cValues.get(i).quarterDate).split("T")[0]);
					writer.append(",");
					writer.append("\""+nf.format(cValues.get(i).actualValue)+"\"");
					writer.append(",");
					writer.append("\""+nf.format(cValues.get(i).calculatedValue)+"\"");
					writer.append(",");
					writer.append(String.valueOf(absoluteDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)));
					writer.append(",");
					writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
					writer.append(",");
					writer.append(String.valueOf(isPassing(cValues.get(i).actualValue,cValues.get(i).calculatedValue,absThresh,perThresh)));
					writer.append("\n");
				}
			}else{
				
				for(int i = 0; i < cValues.size();i++){
					writer.append(((String) cValues.get(i).testCaseId));
					writer.append(",");
					writer.append(((String) cValues.get(i).quarterDate).split("T")[0]);
					writer.append(",");
					writer.append("\""+cValues.get(i).actualValue+"\"");
					writer.append(",");
					writer.append("\""+cValues.get(i).calculatedValue+"\"");
					writer.append(",");
					writer.append(String.valueOf(absoluteDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)));
					writer.append(",");
					writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
					writer.append(",");
					writer.append(String.valueOf(isPassing(cValues.get(i).actualValue,cValues.get(i).calculatedValue,absThresh,perThresh)));
					writer.append("\n");
				}
				
			}
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void createFileSales(String fileName){
		try{
			FileWriter writer = new FileWriter(fileName+".csv");
			
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("Quarter");
			writer.append(",");
			writer.append("Actual Value");
			writer.append(",");
			writer.append("Calculated Value");
			writer.append(",");
			writer.append("Absolute Difference");
			writer.append(",");
			writer.append("Percent Difference");
			writer.append(",");
			writer.append("Pass / Fail");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void createFilePlayers(String fileName){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv");
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("Type");
			writer.append(",");
			writer.append("Field");
			writer.append(",");
			writer.append("ES Value");
			writer.append(",");
			writer.append("UI Value");
			writer.append(",");
			writer.append("Pass/Fail");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFilePlayersNums(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append("\""+ cValues.get(i).modeValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).fieldValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).actualValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).calculatedValue +"\"");
				writer.append(",");
				writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	public static void appendToFilePlayers(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append("\""+ cValues.get(i).modeValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).fieldValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).actualNameValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).calculatedNameValue +"\"");
				writer.append(",");
				if (cValues.get(i).actualNameValue == null && cValues.get(i).calculatedNameValue == null){
					writer.append(isDifferent("0","0"));
				} else {
					writer.append(isDifferent(cValues.get(i).actualNameValue,cValues.get(i).calculatedNameValue));
				}
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void createFileTopDealsStr(String fileName){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv");
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("Step");
			writer.append(",");
			writer.append("ES Value");
			writer.append(",");
			writer.append("UI Value");
			writer.append(",");
			writer.append("Pass/Fail");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileTopDealsStr(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append("\""+ cValues.get(i).modeValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).actualNameValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).calculatedNameValue +"\"");
				writer.append(",");
				writer.append(isDifferent(cValues.get(i).actualNameValue,cValues.get(i).calculatedNameValue));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void createFileIU(String fileName){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv");
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("Step");
			writer.append(",");
			writer.append("ES Value");
			writer.append(",");
			writer.append("UI Value");
			writer.append(",");
			writer.append("Pass/Fail");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileIU(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append("\""+ cValues.get(i).modeValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).actualValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).calculatedValue +"\"");
				writer.append(",");
				writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void createFileMAM(String fileName){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv");
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("ES Value");
			writer.append(",");
			writer.append("UI Value");
			writer.append(",");
			writer.append("Pass/Fail");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileMAM(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append("\""+ cValues.get(i).actualNameValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).calculatedNameValue +"\"");
				writer.append(",");
				writer.append(isDifferent(cValues.get(i).actualNameValue,cValues.get(i).calculatedNameValue));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	public static void appendToFileMAMNums(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append("\""+ cValues.get(i).actualNameValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).calculatedNameValue +"\"");
				writer.append(",");
				writer.append(String.valueOf(percentDifference(Double.valueOf(cValues.get(i).actualNameValue),Double.valueOf(cValues.get(i).calculatedNameValue))+"%"));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	public static void createFilePTS(String fileName){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv");
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("ES Value");
			writer.append(",");
			writer.append("UI Value");
			writer.append(",");
			writer.append("Percent Difference");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFilePTS(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append("\""+ cValues.get(i).actualValue +"\"");
				writer.append(",");
				writer.append("\""+ cValues.get(i).calculatedValue +"\"");
				writer.append(",");
				writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void createFile(String fileName){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv");
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("Quarter");
			writer.append(",");
			writer.append("ES Value");
			writer.append(",");
			writer.append("UI Value");
			writer.append(",");
			writer.append("Percent Difference");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFile(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append("\""+ cValues.get(i).modeValue +"\"");
				writer.append(",");
				writer.append("\""+nf.format(cValues.get(i).actualValue)+"\"");
				writer.append(",");
				writer.append("\""+nf.format(cValues.get(i).calculatedValue)+"\"");
				writer.append(",");
				writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void createFileIA(String fileName){
		try{
			FileWriter writer = new FileWriter(fileName+".csv");
			
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("Year");
			writer.append(",");
			writer.append("Graph Type");
			writer.append(",");
			writer.append("Bar Type");
			writer.append(",");
			writer.append("Actual Value");
			writer.append(",");
			writer.append("Calculated Value");
			writer.append(",");
			writer.append("Absolute Difference");
			writer.append(",");
			writer.append("Percent Difference");
			writer.append(",");
			writer.append("Pass / Fail");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(IOException  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileIA(String fileName,List<CountryValues> cValues,String graphType,String barType, double absThresh, double perThresh){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv", true);
			for(int i = 0; i < cValues.size();i++){
				//System.out.println("Real Value: "+cValues.get(i).actualValue);
				//System.out.println("Calculated Value: "+cValues.get(i).calculatedValue);
				String tmp = "";
				writer.append((String) cValues.get(i).testCaseId);
				writer.append(",");
				writer.append((String) cValues.get(i).quarterDate);
				writer.append(",");
				writer.append(graphType);
				writer.append(",");
				writer.append(barType);
				writer.append(",");
				
				if(cValues.get(i).actualValue < 0){
					tmp = "-"+nf.format(Math.abs(cValues.get(i).actualValue));
					writer.append("\""+tmp+"\"");
				}
				else
					writer.append("\""+nf.format(cValues.get(i).actualValue)+"\"");
				writer.append(",");
				if(cValues.get(i).actualValue < 0){
					tmp = "-"+nf.format(Math.abs(cValues.get(i).calculatedValue));
					writer.append("\""+tmp+"\"");
				}
				else
					writer.append("\""+nf.format(cValues.get(i).calculatedValue)+"\"");
				writer.append(",");
				writer.append(String.valueOf(absoluteDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)));
				writer.append(",");
				writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
				writer.append(",");
				writer.append(String.valueOf(isPassing(cValues.get(i).actualValue,cValues.get(i).calculatedValue,absThresh,perThresh)));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(IOException  e){
			e.printStackTrace();
		}
	}
	
	public static void createFileCumulative(String fileName){
		try{
			FileWriter writer = new FileWriter(fileName+".csv");
			
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("Year");
			writer.append(",");
			writer.append("Month");
			writer.append(",");
			writer.append("Actual Value");
			writer.append(",");
			writer.append("Calculated Value");
			writer.append(",");
			writer.append("Absolute Difference");
			writer.append(",");
			writer.append("Percent Difference");
			writer.append(",");
			writer.append("Pass / Fail");
			writer.append("\n");

			writer.flush();
			writer.close();
		}catch(IOException  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFileCumulative(String fileName,List<CountryValues> cValues, double absThresh, double perThresh){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv",true);
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append(cValues.get(i).cumYear);
				writer.append(",");
				writer.append(cValues.get(i).quarterDate.toString());
				writer.append(",");
				writer.append('"'+nf.format(cValues.get(i).actualValue)+'"');
				writer.append(",");
				writer.append('"'+nf.format(cValues.get(i).calculatedValue)+'"');
				writer.append(",");
				writer.append(String.valueOf(absoluteDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)));
				writer.append(",");
				writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
				writer.append(",");
				writer.append(String.valueOf(isPassing(cValues.get(i).actualValue,cValues.get(i).calculatedValue,absThresh,perThresh)));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}catch(IOException  e){
			e.printStackTrace();
		}
	}
	
	public static void createFile(String fileName,String non){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			FileWriter writer = new FileWriter(fileName+".csv", true);
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			writer.append("Test Case ID");
			writer.append(",");
			writer.append("Quarter");
			writer.append(",");
			writer.append("ES Value");
			writer.append(",");
			writer.append("UI Value");
			writer.append(",");
			writer.append("Percent Difference");
			writer.append("\n");

			writer.flush();
			writer.close();
		}catch(IOException  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToFile(String fileName,List<CountryValues> cValues,String non){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			FileWriter writer = new FileWriter(fileName+".csv", true);
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");

			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).testCaseId));
				writer.append(",");
				writer.append(((String) cValues.get(i).quarterDate).split("T")[0]);
				writer.append(",");
				writer.append("\""+cValues.get(i).actualValue+"\"");
				writer.append(",");
				writer.append("\""+cValues.get(i).calculatedValue+"\"");
				writer.append(",");
				writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
				writer.append("\n");
				
			}
			
			writer.flush();
			writer.close();
		}catch(IOException  e){
			e.printStackTrace();
		}
	}
	
	public static void sortESValuesForTopDeals(String fileName){
		
		
		
		try {
			InputStream in = new FileInputStream(fileName+".csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			
			line = reader.readLine();
			
			
			for (int i = 0; i < countLinesMinusHeader(fileName); i++ ){
				for(int j = 0 ; j < countLinesMinusHeader(fileName);j++){
					line = reader.readLine();
					String tc = line.substring(0, line.indexOf(","));
					String line2 = line.substring(line.indexOf(",")+1);
					String ste = line2.substring(0, line.indexOf(","));
					
				}
			}
			reader.close();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static int countLinesMinusHeader(String fileName){

		int counter = 0;
		
		try {
			InputStream in = new FileInputStream(fileName+".csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			
			line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				counter++;				
			} 
			reader.close();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;

}
	
	public CSV(String fileName,List<CountryValues> cValues){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			FileWriter writer = new FileWriter(fileName+".csv");
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			writer.append("Quarter");
			writer.append(",");
			writer.append("Actual Value");
			writer.append(",");
			writer.append("Calculated Value");
			writer.append(",");
			writer.append("Percent Difference");
			writer.append("\n");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).quarterDate).split("T")[0]);
				writer.append(",");
				writer.append('"'+nf.format(cValues.get(i).actualValue)+'"');
				writer.append(",");
				writer.append('"'+nf.format(cValues.get(i).calculatedValue)+'"');
				writer.append(",");
				writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
				writer.append("\n");
				
			}
			
			writer.flush();
			writer.close();
		}catch(IOException  e){
			e.printStackTrace();
		}
	}
	
	public CSV(String fileName,List<CountryValues> cValues,String non){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			FileWriter writer = new FileWriter(fileName+".csv");
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			writer.append("Quarter");
			writer.append(",");
			writer.append("Actual Value");
			writer.append(",");
			writer.append("Calculated Value");
			writer.append(",");
			writer.append("Percent Difference");
			writer.append("\n");
			
			for(int i = 0; i < cValues.size();i++){
				writer.append(((String) cValues.get(i).quarterDate).split("T")[0]);
				writer.append(",");
				writer.append("\""+cValues.get(i).actualValue+"\"");
				writer.append(",");
				writer.append("\""+cValues.get(i).calculatedValue+"\"");
						
				writer.append(",");
				writer.append(percentDifference(cValues.get(i).actualValue,cValues.get(i).calculatedValue)+"%");
				writer.append("\n");
				
			}
			
			writer.flush();
			writer.close();
		}catch(IOException  e){
			e.printStackTrace();
		}
	}*/
	
	public static long percentDifference(double x,double y){
		if(x==0 && y==0)
			return 0;
		return Math.round((Math.abs(x-y))/((x+y)/2) * 100);
	}
	
	public static String isDifferent(String x,String y){
		String result = "";
		if (x.equalsIgnoreCase(y)){
			result = "Pass";
		} else {
			result = "Fail";
		}
		return result;
	}
	
	public static String isLessThanEqualTo(double x, double y){
		String result = "";
		if (y <= x) {
			result = "Pass";
		} else {
			result = "Fail";
		}
		return result;
	}
	
	public static double absoluteDifference(double actual,double calc){
		return Math.abs(actual-calc);
	}
	
	public static boolean isPassing(double actual,double calc, double absThresh,double percentThresh){
		
		if(actual == 0 && Double.isNaN(calc))
			return true;
		
		boolean flagAbs = false;
		if(calc >= actual - absThresh  && calc <= actual + absThresh)
			flagAbs = true;
		
		//Percent Threshold Check
		boolean flagPer = false;
		double perValue = actual * percentThresh;
		if(actual >= 0){
			if(calc >= actual - perValue && calc <= actual + perValue)
				flagPer = true;
		}else{
			if(calc >= actual + perValue &&  calc <= actual - perValue)
				flagPer = true;
		}
		return flagAbs || flagPer;
	}
	
	public static void createResultsFile(String fileName){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();

			FileWriter writer = new FileWriter(fileName+".csv");
			
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			writer.append("Test Case Name");
			writer.append(",");
			writer.append("Result");
			writer.append(",");
			writer.append("Failed Rows");
			writer.append("\n");
			
			writer.flush();
			writer.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public static void appendToResultsFile(String fileName,String result, String failedRows){
		try{
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			FileWriter writer = new FileWriter("Results" + RepoSearchTest.currentDateEnv + ".csv", true);
			DateFormat date = new SimpleDateFormat("MM-dd-yyyy");


				writer.append(fileName);
				writer.append(",");
				writer.append(result);
				writer.append(",");
				writer.append(failedRows);
				writer.append("\n");

			
			writer.flush();
			writer.close();
		}catch(IOException  e){
			e.printStackTrace();
		}
	}
	
}