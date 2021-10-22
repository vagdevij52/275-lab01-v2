package client;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.yrrhelp.grpc.Station;
import com.yrrhelp.grpc.stationGrpc;
import com.yrrhelp.grpc.Station.APIResponse;
import com.yrrhelp.grpc.Station.GetStationDataRequest;
import com.yrrhelp.grpc.Station.APIResponse.Builder;
import com.yrrhelp.grpc.stationGrpc.stationImplBase;

import io.grpc.stub.StreamObserver;
import parser.MesonetProcessor;

public class StationService {

	public String getStationData(String filename) {
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
				
			}else {
			}
				
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}	
}
