import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JFileChooser;

public class ip_to_country {
		
	public static void main(String[] args) {
		double[] ip_min = new double[104679];
		double[] ip_max = new double[104679];
		double[] ip = new double[104679];

		String[] country = new String[104679];

		for(int i = 0; i < 104679; i++){
			ip_min[i] = 0;
			ip_max[i] = 0;
			ip[i] = 0;
		}
		
		
		//thanks:  http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
		//JFileChooser fc = new JFileChooser();
		//In response to a button click:
		//int returnVal = fc.showOpenDialog(fc);
		
		String csvFile = "GeoIPCountryWhois.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			 
			br = new BufferedReader(new FileReader(csvFile));
			int row = 0;
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] csv_line = line.split(cvsSplitBy);
				ip_min[row] = Double.parseDouble(csv_line[0].replace(".", ""));
				ip_max[row] = Double.parseDouble(csv_line[1].replace(".", ""));
				country[row] = (csv_line[2]);

				row++;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		 csvFile = "ip.csv";
		 br = null;
		 line = "";
		 cvsSplitBy = ",";
		try {
			 
			br = new BufferedReader(new FileReader(csvFile));
			int row = 0;
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] csv_line = line.split(cvsSplitBy);
				try{
				ip[row] = Double.parseDouble(csv_line[0].replace(".", ""));
				}catch(Exception e){
					continue;
				}
				row++;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		HashMap<String, Integer> map = new HashMap<String,Integer>();
		for(int i= 0; i < ip.length; i++){
			for(int j = 0; j < ip_max.length; j++){
				if(ip[i] >= ip_min[j] && ip[i] <= ip_max[j]){
					try{
					map.put(country[j],1);
					}catch(Exception e){
						
					}
				}
			}
		}
		System.out.println(map.toString());
		System.out.println(map.size());
	}

	
	
	
}
