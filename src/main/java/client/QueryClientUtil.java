package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.yrrhelp.grpc.Station.APIResponse;

import parser.MesonetProcessor;
import server.GRPCServer;

public class QueryClientUtil{
	
	public QueryClientUtil() {
		// TODO Auto-generated constructor stub
	}
	GRPCServer server;
	int servernum;
	public QueryClientUtil(GRPCServer server,int servernum) {
		// TODO Auto-generated constructor stub
		this.server = server;
		this.servernum = servernum;
	}

	public String sendQuery(String filename) {
		System.out.println("inside send message");
		MesonetProcessor mp = new MesonetProcessor();
		APIResponse response = null;
		JSONArray result = new JSONArray();
		
		try {
			File f = mp.processFile(filename);
			Scanner sc = new Scanner(f);
			if(f.exists()) {
				while (sc.hasNext()) { //returns a boolean value
						JSONObject res = new JSONObject();
						String values[] = sc.nextLine().split(",");
						  res.put("id", values[0]);
						  res.put("name", values[1]);
						  res.put("mesonet", values[2]);
						  res.put("lat", values[3]);
						  res.put("lon", values[4]);
						  res.put("elevation", values[5]);
						  res.put("agl", values[6]);
						  res.put("cit", values[7]);
						  res.put("state", values[8]);
						  res.put("country", values[9]);
						  res.put("active", values[10]);
						result.add(res);					
				}   
				sc.close();
				return result.toJSONString();
				//response = APIResponse.newBuilder().setMessage(result.toJSONString()).build();				
				//response.setResponseMessage("Success").setResponseCode(200).setM(response.toJSONString());
				
			}else {
				sc.close();
				return "File not found";
			}
				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
