package org.taxiforsure.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ParseAPIAndGetValue
{

   private synchronized HttpURLConnection getConnectionObject()
   {
	  HttpURLConnection con = null;
	  return con;
   }
   
   private synchronized URL getURLForPostRequest()
   {
	  URL url = null;
	  return url;
   }
   
   
   private synchronized String postRequest(String URL, String apikey, String input)
   {
	  HttpURLConnection con = getConnectionObject();
	  StringBuilder sb = new StringBuilder();
	  URL url = getURLForPostRequest();
	  String output;

	  try 
	  {
		 url = new URL(URL);
		 con = (HttpURLConnection) url.openConnection();
		 con.setDoOutput(true);
	  } catch (MalformedURLException e)
	  {
		 e.printStackTrace();
	  } catch (IOException e) 
	  {
		 e.printStackTrace();
	  }

	  try 
	  {
		 con.setRequestMethod("POST");
		 con.setRequestProperty("Content-Type", "application/json");
		 con.setRequestProperty("key", apikey);
		 
	  } catch (ProtocolException e) 
	  {
		 e.printStackTrace();
	  }


	  try {
		 OutputStream outputStream = con.getOutputStream();
		 outputStream.write(input.getBytes());
		 outputStream.flush();
	  } catch (IOException e) 
	  {
		 e.printStackTrace();
	  }


	  try {
		 if (con.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
		 }
	  } catch (IOException e) 
	  {
		 e.printStackTrace();
	  }

	  BufferedReader responseBuffer = null;
	  try {
		 responseBuffer = new BufferedReader(new InputStreamReader(
			   (con.getInputStream())));
	  } catch (IOException e) 
	  {
		 e.printStackTrace();
	  }


	  try {
		 while ((output = responseBuffer.readLine()) != null) {
			sb.append(output);
			sb.append("\n");
		 }
	  } catch (IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
	  }

	  con.disconnect();
	  return sb.toString();
   }

   private synchronized String getJsonResponse()
   {
	  String jsonResponse = null;
	  return jsonResponse;
   }
   
   private synchronized JSONObject getJsonObject()
   {
	  JSONObject jsonobject = null;
	  return jsonobject;
   }
   
   private synchronized JSONArray getJsonArray()
   {
	  JSONArray ja = null;
	  return ja;
   }
   
   public synchronized List<Object> getPostJSONValue(String URL, String apikey,String input, String jsonkey)
   {
	  List<Object> list = null;
	  list = new ArrayList<Object>();
	  String jsonResponse =  getJsonResponse();
	  jsonResponse= postRequest(URL, apikey,input);
	  
		 String s = jsonResponse.toString().replace("\"{", "{");
		 String s2 = s.toString().replace("}\"", "}");
		 String s3 = s2.toString().replace("\\", "");


		 JSONArray ja = getJsonArray();
		 try 
		 {
			ja = new JSONArray(s3.toString());
		 } catch (JSONException e) 
		 {
			e.printStackTrace();
		 }

		 for(int i=0;i<ja.length();i++)
		 {

			JSONArray jaTemp = null;
			try {
			   jaTemp = ja.getJSONArray(i);
			} catch (JSONException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			}

			JSONObject jsonobject = getJsonObject();
			try
			{
			   jsonobject = jaTemp.getJSONObject(0);
			   list.add(jsonobject.getString(jsonkey));
			} catch (JSONException e) {
			   e.printStackTrace();
			}

		 }
	  
	  return list;
   }

   public synchronized String getBookingDetailsRequest(String URL)
   {
	  StringBuilder sb = new StringBuilder();
	  BufferedReader br;
	  HttpURLConnection con = null;
	  URL url;
	  String output;

	  try 
	  {
		 url = new URL(URL);
		 con = (HttpURLConnection) url.openConnection();
		 con.setDoOutput(true);
	  } catch (MalformedURLException e)
	  {
		 e.printStackTrace();
	  } catch (IOException e) 
	  {
		 e.printStackTrace();
	  }

	  try 
	  {
		 con.setRequestMethod("GET");
		 con.setRequestProperty("Content-Type", "application/json");

	  } catch (ProtocolException e) 
	  {
		 e.printStackTrace();
	  }


	  try {
		 br = new BufferedReader(new InputStreamReader((con.getInputStream())));


		 while ((output = br.readLine()) != null)
		 {
			sb.append(output);
		 }
		 con.disconnect();
	  } catch (IOException e) 
	  {
		 e.printStackTrace();
	  }

	  return sb.toString();
   }

   public synchronized Object getBookingDetailsFromJSON(String jasonResponse, String jsonkey)
   {
	  

	  Object obj = null;

	  try
	  {

		 JSONObject jo = new JSONObject(jasonResponse);
		 JSONObject response_data = (JSONObject) jo.get("response_data");
		 obj = response_data.get(jsonkey);

	  } catch (JSONException e)
	  {
		 e.printStackTrace();
	  }
	  return obj;
   }

   @SuppressWarnings("unused")
public synchronized List<String> getAirportName(String url)
   {
	  String airportName = null;
	  String jsonResponse = getBookingDetailsRequest(url);
	  List<String> airportNames = new ArrayList<String>();
	  try
	  {
		 String str = jsonResponse.replace("[", "").replace("]", "");
		 String splitJsonResponse[] = str.split("\\{");
		 
		 for(int i=0;i<splitJsonResponse.length;i++)
		 {
			if(!(splitJsonResponse[i].equals(""))){
			   String string = splitJsonResponse[i].replace("\"name\"", "{\"name\"");
			   JSONObject jobject = new JSONObject(string);
			   
			   JSONParser jparser = new JSONParser();
			   org.json.simple.JSONObject ob = null;
               try
               {
	              ob = (org.json.simple.JSONObject) jparser.parse(jobject.toString());
               } catch (ParseException e)
               {
	              // TODO Auto-generated catch block
	              e.printStackTrace();
               }
			  
			   Iterator<?> it = ob.keySet().iterator();
			   
			   while(it.hasNext())
			   {
				  if(it.next().toString().equals("name"))
				  {
					 airportNames.add(ob.get("name").toString());
				  }
			   }
			}
		 }
	  } catch (JSONException e)
	  {
		 e.printStackTrace();
	  }
   
	  return airportNames;
   }
}
