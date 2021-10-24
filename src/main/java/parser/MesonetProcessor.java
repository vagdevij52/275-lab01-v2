package parser;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class MesonetProcessor {

	/** file name prefix for general data */
	public static final String sGeneralTag = "madis-mesonet";
	public MesonetProcessor() {
		// TODO Auto-generated constructor stub
	}

	public MesonetProcessor(File catalogF, File outputDir) {
		// TODO read catalog
		// TODO read data

		// TODO if needed, save catalog w/ new stations
	}

	/**
	 * assumption: data is received once every 15 minutes or so.
	 * 
	 * This will perform batch processing.
	 * 
	 * @param args
	 * @throws ParseException 
	 */
	public File processFile(String filename) throws ParseException {
//		if (args.length != 3) {
//			System.out.println("Usage: dataSource catalogFile outputDir");
//			System.exit(2);
//		}

		//File dataSource = new File("/Users/vagdevijunnuri/Desktop/SJSU/secondsem/275/projects/mesonet/9-days/20120201/20120201_1600.gz");//20140201/20140201_0100.gz  20120201/20120201_1000.gz   /201202/20120201_0203.gz
		//201202012000
		File dataSource = new File("/Users/vagdevijunnuri/Desktop/mesonetdata/"+filename+".gz");
		File catF = new File("catalog.csv");
		File outdir = new File("/Users/vagdevijunnuri/Desktop/mesonetdata/output");
		
//		Long startDateValue = 201202011100L;
//		Long endDateValue =   201202012300L;
		
		Integer startDateValue = null;
		Integer endDateValue = null;
		
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
		//SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMddHHmm");
		//Date date = originalFormat.parse(value.toString());

		// filters
		//Date startDate = originalFormat.parse(startDateValue.toString());
		//Date endDate = originalFormat.parse(endDateValue.toString());
		
		Date startDate = null;
		Date endDate = null;
		Rectangle region = null;
		Set<String> stationIDs = null;

//		System.out.println("\n\nSource: " + dataSource + "\nCatalog: " + catF.getAbsolutePath() + "\nOutput: "
//				+ outdir.getAbsolutePath());
		System.out.println("file: "+filename);

		long startTime = System.currentTimeMillis();
		try {
			Catalog cat = new Catalog();
			if (!cat.load(catF)) {
				System.out.println("-- new catalog file created");
			}

			MesonetReader rawReader = new MesonetReader();

			/**
			 * note use readFile() to perform the same steps
			 */

			if (!dataSource.exists())
				return null;
			if (dataSource.isFile()) {
				List<Station> stations = rawReader.extractCatalog(dataSource);
				if (stations != null) {
					for (Station s : stations)
						cat.addStation(s);
				}

				List<MesonetData> data = rawReader.read(dataSource, startDate, endDate, region, stationIDs);
//				for (int i = 0; i < data.size(); i++) {
//				    System.out.println("i:: "+data.get(i).getStationID());
//				}
				System.out.println("processed " + data.size() + " entries");

				// now do something with the data
				// 1. send to the cluster or cloud

//				for (MesonetData d : data) {
//					// TODO do something other than print!
//					System.out.println("Obs: " + d.getStationID() + " T = " + d.getTemperature() + ", WS = "
//							+ d.getWindSpeed() + ", WD = " + d.getWindDir() + ", RH = " + d.getRelHumidity());
//				}
			} else {
				FileFilter filter = new FileFilter() {
					public boolean accept(File pathname) {
						return (pathname.isFile() && !pathname.getName().startsWith(".")
								&& !pathname.getName().endsWith(".gz"));
					}
				};

				// TODO walk through accepted files and process
				System.out.println("TODO: process files");

			}

			// save catalog
			cat.save(catF);

			long stopTime = System.currentTimeMillis();
//			System.out.println(
//					"MADIS Mesonet - total processing time is " + ((stopTime - startTime) / 1000.0) + " seconds");
		} catch (Throwable t) {
			System.out.println(
					"Unable to process mesowest data in " + dataSource.getAbsolutePath() + ": " + t.getMessage()+":"+t.getStackTrace());
		}
		return catF;
	}
}
