package com.taxiforsure.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
//import org.json.simple.parser.ParseException;

public class ParsePriceAPI {


	String[][] names ;

	public StringBuilder readingJSON(String URL)  {
		String APIURL = URL;
		StringBuilder Jsoncontent = new StringBuilder();
		BufferedReader br;
		HttpURLConnection con = null;
		URL url;
		String output;

		try {
			url = new URL(APIURL);
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");

		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		try {
			br = new BufferedReader(new InputStreamReader((con.getInputStream())));

			 while ((output = br.readLine()) != null) 
			    {
				 Jsoncontent.append(output);
				}
			
			con.disconnect();
		} 
		catch (IOException e) 
		{
		  e.printStackTrace();
		}
		
		return Jsoncontent;

		/*Map <String, List <String[]> > FinalList = new LinkedHashMap<String, List<String[]>>();
		List<String> TravelType = new ArrayList<String>(Arrays.asList("p2p","4h40km","8h80km","from_at_km_fares","at-km"));

		String jResponse = Jsoncontent.toString();
		JSONObject DayNightO = null;

		
			try {
				
				for(String TType:TravelType)
				{

				JSONObject jobjects = new JSONObject(jResponse);
				JSONObject response_data = (JSONObject) jobjects.get("response_data");
				DayNightO = (JSONObject) response_data.get("day");
			
		    	//Defining Parsing Object
				org.json.simple.parser.JSONParser ParsingO = new JSONParser();
				
				//Parsing Day/Night Object
                org.json.simple.JSONObject object = (org.json.simple.JSONObject) ParsingO.parse(DayNightO.toString());

				@SuppressWarnings("unchecked")

				//Iterating Day/Night Object
				Iterator<Object> FairType = object.keySet().iterator();
				while (FairType.hasNext()) 
				    {
					String FType = FairType.next().toString();
					if (FType.contains(TType)) {
						String obj1 = object.get(FType).toString();
						Object o = ParsingO.parse(obj1);
						JSONArray array = (JSONArray) o;

						names = new String[array.size()+2][10];
						int j;

						if(TType=="p2p"){
							for (int i = 0; i < array.size(); i++) 
							{

								org.json.simple.JSONObject object1 = (org.json.simple.JSONObject) ParsingO.parse(array.get(i).toString());
								j = 0;
								names[i][j] = object1.get("car_name").toString();
								j = 1;
								names[i][j]="Rs. "+Float.parseFloat(object1.get("base_fare").toString())+" for first "+object1.get("base_km").toString()+" km &amp; Rs."+Float.parseFloat(object1.get("extra_km_fare").toString())+"/km after";
							}
						}

						else if(TType=="4h40km" || TType=="8h80km")  
						{
							for (int i = 0; i < array.size(); i++) 
							{

								org.json.simple.JSONObject object1 = (org.json.simple.JSONObject) ParsingO.parse(array.get(i).toString());
								j = 0;
								names[i][j] = object1.get("car_name").toString();
								j = 1;
								names[i][j] = "Rs. "+Float.parseFloat(object1.get("base_fare").toString())+ ", extra hour Rs."+ Float.parseFloat(object1.get("extra_hour_fare").toString())+ ",&#10; extra km Rs."+ Float.parseFloat(object1.get("extra_km_fare").toString());
							
							}

						}
						
						
						else if(TType=="at-km" || TType=="from_at_km_fares")
						{
							for (int i = 0; i < array.size(); i++) 
							{

								org.json.simple.JSONObject object1 = (org.json.simple.JSONObject) ParsingO.parse(array.get(i).toString());
								j = 0;
								names[i][j] = object1.get("car_name").toString();
								j = 1;
								names[i][j] = "Rs. "+Float.parseFloat(object1.get("base_fare").toString())+ " for first "+ object1.get("base_km").toString()+ " km &amp; Rs."+Float.parseFloat(object1.get("extra_km_fare").toString())+ "/km after";
							}
						}

                        List<String[]> ValueList = Arrays.asList(names);
                        FinalList.put(TType,ValueList);
					}

				}
			}
				
			}
		
			catch (Exception ef)  {
				ef.printStackTrace();
			}
			
			

			return (FinalList);
		}*/

	}
  }