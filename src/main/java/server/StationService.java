package server;
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

public class StationService extends stationImplBase{
	
	GRPCServer server;
	int servernum;
	public StationService(GRPCServer server,int servernum) {
		// TODO Auto-generated constructor stub
		this.server = server;
		this.servernum = servernum;
	}

	@Override
	public void getStationData(GetStationDataRequest request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("Server "+servernum+" is processing the request");
		MesonetProcessor mp = new MesonetProcessor();
		APIResponse response = null;
		JSONArray result = new JSONArray();
		//APIResponse response = null;
//		APIResponse.Builder response = APIResponse.newBuilder();
//		response.setResponseCode(200).setResponseMessage("Success");
//		responseObserver.onNext(response.build());
//	    responseObserver.onCompleted();
		
		try {
			File f = mp.processFile(request.getFilename());
			Scanner sc = new Scanner(f);
			if(f.exists()) {
				//System.out.println("file received::"+f);
				
//				  try (CSVReader reader = new CSVReader(new FileReader(f))) {
//				      String[] lineInArray;
//				      
//				      while ((lineInArray = reader.readNext()) != null) {
//				    	  response.setId(lineInArray[0]);
//				    	  response.setName(lineInArray[1]);
//				    	  response.setMesonet(lineInArray[2]);
//				    	  response.setLat(lineInArray[3]);
//				    	  response.setLon(lineInArray[4]);
//				    	  response.setElevation(lineInArray[5]);
//				    	  response.setAgl(lineInArray[6]);
//				    	  response.setCit(lineInArray[7]);
//				    	  response.setState(lineInArray[8]);
//				    	  response.setCountry(lineInArray[9]);
//				    	  response.setActive(lineInArray[10]);
//				          //System.out.println("Response: "+response);
//				    	  response.setResponseMessage("Success").setResponseCode(200).setId(response.getId()).setName(response.getName()).setMesonet(response.getMesonet()).setLat(response.getLat()).setLon(response.getLon()).setElevation(response.getElevation()).setAgl(response.getAgl()).setCit(response.getCit()).setState(response.getState()).setCountry(response.getCountry()).setActive(response.getActive());
//						  responseObserver.onNext(response.build());
//						  responseObserver.onCompleted();
//				      }
//				  } catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (CsvValidationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
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
//				    	  response.setName(lineInArray[1]);
//				    	  response.setMesonet(lineInArray[2]);
//				    	  response.setLat(lineInArray[3]);
//				    	  response.setLon(lineInArray[4]);
//				    	  response.setElevation(lineInArray[5]);
//				    	  response.setAgl(lineInArray[6]);
//				    	  response.setCit(lineInArray[7]);
//				    	  response.setState(lineInArray[8]);
//				    	  response.setCountry(lineInArray[9]);
//				    	  response.setActive(lineInArray[10]);
						result.add(res);					
				}   
				sc.close();
				response = APIResponse.newBuilder().setMessage(result.toJSONString()).build();				
				//response.setResponseMessage("Success").setResponseCode(200).setM(response.toJSONString());
				
			}else {
				//response.setResponseCode(404).setResponseMessage("File not found");
			}
				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	//implement the APIs


	
}
