package com.pragya.coronavirustracker.service;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.pragya.coronavirustracker.models.locationStats;
@Service
public class coronaVirusDataService {
	
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv"; 
    private List<locationStats> allStats = new ArrayList<>(); 
	
 @PostConstruct     //to construct after the instance of class is created
 @Scheduled(cron = "* * 1 * * *") // to run on daily basis and cron value of star says it run every second in short specified frequency     	
 public void fetchVirusData() throws IOException, InterruptedException {
	     List<locationStats> newStats = new ArrayList<>();
	     
	     
	     
 	    //way of parsing the url to uri and fetch the data
	 
		HttpClient client = HttpClient.newHttpClient();
	    HttpRequest request =  HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build() ;
	    HttpResponse<String> httpResponse= client.send(request,HttpResponse.BodyHandlers.ofString());
	   
	    // parsing the csv data into readable string
	    
	    StringReader csvBodyReader = new StringReader(httpResponse.body());
	    Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
	    for (CSVRecord record : records) {
	    	locationStats locationstats = new locationStats();
	        locationstats.setStates(record.get("Province/State"));
	        locationstats.setCountry(record.get("Country/Region"));
	        int latestCase = Integer.parseInt(record.get(record.size()-1));
	        int previousDayCase = Integer.parseInt(record.get(record.size()-2));
	        locationstats.setDifferenceFromPrevDay(latestCase-previousDayCase);
	        locationstats.setLatestTotalCase(latestCase);
	        System.out.println(locationstats);
	        newStats.add(locationstats);
	    }
	    this.allStats = newStats;
	}

public List<locationStats> getAllStats() {
	return allStats;
}

public void setAllStats(List<locationStats> allStats) {
	this.allStats = allStats;
}


}
