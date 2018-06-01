package Apps;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Scanner;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.Object.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

//import org.apache.http.Header;
//import org.apache.http.NameValuePair;
//import org.apache.http.ProtocolVersion;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
//import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.*;
//import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.gson.Gson;
import PageObjects.*;
import Utility.Constant;
import test.java.RepoSearchTest;
import test.java.RepoSmokeTest;

public class Elastic_Action { 
	
	public static JSONObject getSearchResultsByRepnum(String repnum, String env, String token) {
		String jsonRequest;
		String response = "";
		jsonRequest = "{\"title_tx\":\"\",\"addr1_tx\":\"\",\"addr2_tx\":\"\",\"city_tx\":\"\",\"state_tx\":\"\",\"primaryProperty_tx\":\"\",\"owner_json.name_tx\":\"\",\"sourceKey_tx\":\""+repnum+"\",\"sort\":{\"title_tx\":\"asc\",\"raw\":true}}";
		//jsonRequest = "{\"sourceKey_tx\":\""+repnum+"\"}";
		//System.out.println(jsonRequest);
		response = getDodgeSearchResultsQuery(jsonRequest, env, token);
		JSONObject jsonResponse = new JSONObject(response); 
		jsonResponse = jsonResponse.getJSONObject("records");
		return jsonResponse;
	}

	public static String getDodgeSearchResultsQuery(String filters, String env, String token){
		String data = "";
		String response ="";
		URL url;
		
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] {new TrustAllX509TrustManager()}, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
				public boolean verify(String str, SSLSession ssls){
					return true;
				}
			});

			url = new URL("http://repositorysearch" + env + ".rcanalytics.io/api/constructiondetails");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.addRequestProperty("Accept", "application/json");
			conn.addRequestProperty("Content-Type", "application/json");
			conn.addRequestProperty("Authorization", token);
			OutputStream os = conn.getOutputStream();
			os.write(filters.getBytes());
			os.flush();
			os.close();
			int statusCode= conn.getResponseCode();
			String statusMsg = conn.getResponseMessage();
			if (statusCode == 200){
				InputStream stream = conn.getInputStream();
				java.io.BufferedInputStream sr = new java.io.BufferedInputStream(stream);
				response = convertStreamToString(sr);
				data = response;
			} else {
				System.out.println("Status:" + statusCode + ". Message: " + statusMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static JSONObject getSearchResultsByProjID(String projectid, String env, String token) {
		String jsonRequest;
		String response = "";
		String id = "";
		jsonRequest = "{\"query\": {\"project_id\":\""+projectid+"\"},\"columns\": []}";
		response = getOfferingsIDQuery(jsonRequest, env, token);
		JSONObject jsonResponse = new JSONObject(response);
		JSONArray responseArr = jsonResponse.getJSONArray("records");
		JSONObject idObj = (JSONObject) responseArr.get(0);
		id = idObj.getString("id");
		
		String results = getOfferingsSearchResults(id, env, token);
		JSONObject jsonResults = new JSONObject(results);
		jsonResults = jsonResults.getJSONObject("inResult");
		return jsonResults;
	}
	
	public static String getOfferingsIDQuery(String filters, String env, String token){
		String data = "";
		String response ="";
		URL url;
		
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] {new TrustAllX509TrustManager()}, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
				public boolean verify(String str, SSLSession ssls){
					return true;
				}
			});
			//
			
			url = new URL("http://repositorysearch" + env + ".rcanalytics.io/api/offerings");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.addRequestProperty("Accept", "application/json");
			conn.addRequestProperty("Content-Type", "application/json"); //update for content type
			conn.addRequestProperty("Authorization", token);
			OutputStream os = conn.getOutputStream();
			os.write(filters.getBytes());
			os.flush();
			os.close();
			int statusCode= conn.getResponseCode();
			String statusMsg = conn.getResponseMessage();
			if (statusCode == 200){
				InputStream stream = conn.getInputStream();
				java.io.BufferedInputStream sr = new java.io.BufferedInputStream(stream);
				response = convertStreamToString(sr);
				data = response;
			} else {
				System.out.println("Status:" + statusCode + ". Message: " + statusMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static String getOfferingsSearchResults(String id, String env, String token){
		String data = "";
		String response ="";
		URL url;
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] {new TrustAllX509TrustManager()}, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
				public boolean verify(String str, SSLSession ssls){
					return true;
				}
			});
			url = new URL("http://repositorysearch" + env + ".rcanalytics.io/api/offerings/"+id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.addRequestProperty("Accept", "application/json");
			conn.addRequestProperty("Content-Type", "application/json"); //update for content type
			conn.addRequestProperty("Authorization", token);
			//OutputStream os = conn.getOutputStream();
			//os.write(id.getBytes());
			//os.flush();
			//os.close();
			int statusCode= conn.getResponseCode();
			String statusMsg = conn.getResponseMessage();
			if (statusCode == 200){
				InputStream stream = conn.getInputStream();
				java.io.BufferedInputStream sr = new java.io.BufferedInputStream(stream);
				response = convertStreamToString(sr);
				data = response;
			} else {
				System.out.println("Status:" + statusCode + ". Message: " + statusMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static int getTransSCountByMasterTaxrollID(String mastertaxrollid, String env, String token) {
		String jsonRequest;
		String response = "";
		String id = "";
		jsonRequest = "{\"query\": {\"transactions_json.masterTaxRoll_id\":\""+mastertaxrollid+"\"},\"columns\": []}";
		response = getTitleIDQuery(jsonRequest, env, token);
		JSONObject jsonResponse = new JSONObject(response); 
		try {
			id = jsonResponse.getJSONArray("records").getJSONObject(0).getString("id");
		} catch (Exception e){
			System.out.println(mastertaxrollid + " missing");
			e.printStackTrace();
		}
		
		String results = getTitleSearchResults(id, env, token);
		JSONObject jsonResults = new JSONObject(results);
		try {
			
			JSONArray transactionsJson = jsonResults.getJSONObject("inResult").getJSONArray("transactions_json");
			int transcount = transactionsJson.length();
			return transcount;
		} catch (Exception e) {
			System.out.println(mastertaxrollid + " missing");
			e.printStackTrace();
		}
		
		return -1;

	}
	
	public static JSONObject getSearchResultsByMasterTaxID(String mastertaxrollid, String env, String token) {
		String jsonRequest;
		String response = "";
		String id = "";
		jsonRequest = "{\"query\": {\"vendorRecord_id\":\""+mastertaxrollid+"\"},\"columns\": []}";
		response = getTitleIDQuery(jsonRequest, env, token);
		JSONObject jsonResponse = new JSONObject(response);
		JSONArray responseArr = jsonResponse.getJSONArray("records");
		JSONObject idObj = (JSONObject) responseArr.get(0);
		id = idObj.getString("id");
		String results = getTitleSearchResults(id, env, token);
		JSONObject jsonResults = new JSONObject(results);
		jsonResults = jsonResults.getJSONObject("inResult");
		return jsonResults;
	}
	
	public static String getTitleIDQuery(String filters, String env, String token){
		String data = "";
		String response ="";
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] {new TrustAllX509TrustManager()}, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
				public boolean verify(String str, SSLSession ssls){
					return true;
				}
			});
			URL url = new URL("http://repositorysearch" + env + ".rcanalytics.io/api/title");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.addRequestProperty("Accept", "application/json");
			conn.addRequestProperty("Content-Type", "application/json"); //update for content type
			conn.addRequestProperty("Authorization", token);
			OutputStream os = conn.getOutputStream();
			os.write(filters.getBytes());
			os.flush();
			os.close();
			int statusCode= conn.getResponseCode();
			String statusMsg = conn.getResponseMessage();
			if (statusCode == 200){
				InputStream stream = conn.getInputStream();
				java.io.BufferedInputStream sr = new java.io.BufferedInputStream(stream);
				response = convertStreamToString(sr);
				data = response;
			} else {
				System.out.println("Status:" + statusCode + ". Message: " + statusMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static String getTitleSearchResults(String id, String env, String token){
		String data = "";
		String response ="";
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] {new TrustAllX509TrustManager()}, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
				public boolean verify(String str, SSLSession ssls){
					return true;
				}
			});
			//URL url = new URL("http://rancherlb-test.rcanalytics.com:3030/api/constructiondetails");
			URL url = new URL("http://repositorysearch" + env + ".rcanalytics.io/api/title/"+id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.addRequestProperty("Accept", "application/json");
			conn.addRequestProperty("Content-Type", "application/json"); //update for content type
			conn.addRequestProperty("Authorization", token);
			//OutputStream os = conn.getOutputStream();
			//os.write(id.getBytes());
			//os.flush();
			//os.close();
			int statusCode= conn.getResponseCode();
			String statusMsg = conn.getResponseMessage();
			if (statusCode == 200){
				InputStream stream = conn.getInputStream();
				java.io.BufferedInputStream sr = new java.io.BufferedInputStream(stream);
				response = convertStreamToString(sr);
				data = response;
			} else {
				System.out.println("Status:" + statusCode + ". Message: " + statusMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static int getFIPSCount(String fips_tx) {
		String jsonRequest;
		String response = "";
		jsonRequest = "{\"query\": {\"fips_tx\":\""+fips_tx+"\"},\"columns\": []}";
		response = getFIPSCountQuery(jsonRequest);
		JSONObject jsonResponse = new JSONObject(response); 
		int FIPSCount = jsonResponse.getInt("total");
		return FIPSCount;
	}
	
	//Old
	public static String getFIPSCountQuery(String filters){
		String data = "";
		String response ="";
		//JSONObject objFilters = new JSONObject(filters);
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] {new TrustAllX509TrustManager()}, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
				public boolean verify(String str, SSLSession ssls){
					return true;
				}
			});
			URL url = new URL("http://repositorysearch" + RepoSearchTest.env + ".rcanalytics.com:3030/api/title");
			//URL url = new URL("http://internal-rancherpoc-test-1065858592.us-east-1.elb.amazonaws.com:3030/api/constructiondetails");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.addRequestProperty("Accept", "application/json");
			conn.addRequestProperty("Content-Type", "application/json"); //update for content type
			//conn.addRequestProperty("Authorization", token);
			OutputStream os = conn.getOutputStream();
			os.write(filters.getBytes());
			os.flush();
			os.close();
			int statusCode= conn.getResponseCode();
			String statusMsg = conn.getResponseMessage();
			if (statusCode == 200){
				InputStream stream = conn.getInputStream();
				java.io.BufferedInputStream sr = new java.io.BufferedInputStream(stream);
				response = convertStreamToString(sr);
				data = response;
			} else {
				System.out.println("Status:" + statusCode + ". Message: " + statusMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	public static String convertStreamToString(java.io.InputStream is){
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
	
	public static String getToken(String environment){
		String strToken= "";
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("username", Constant.username);
			params.put("password", Constant.password);
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] {new TrustAllX509TrustManager()}, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
				public boolean verify(String str, SSLSession ssls){
					return true;
				}
			});
			URL url = new URL("https://home" + environment + ".rcanalytics.io/api/v1/authentication/login");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.addRequestProperty("Accept", "application/json");
			conn.addRequestProperty("Content-Type", "application/json");
			String input = new Gson().toJson(params);
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			os.close();
			int statusCode= conn.getResponseCode();
			String statusMsg = conn.getResponseMessage();
			if (statusCode == 200){
				InputStream stream = conn.getInputStream();
				java.io.BufferedInputStream sr = new java.io.BufferedInputStream(stream);
				String response = convertStreamToString(sr);
				String resp[] = response.split("\"");
				strToken = resp[3];
				//System.out.println(strToken);
			} else {
				System.out.println("Status:" + statusCode + ". Message: " + statusMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Bearer " + strToken;
	}
	
}