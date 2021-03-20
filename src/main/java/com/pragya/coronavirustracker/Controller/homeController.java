package com.pragya.coronavirustracker.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pragya.coronavirustracker.service.coronaVirusDataService;
import com.pragya.coronavirustracker.models.locationStats;

@Controller
public class homeController {
	
	@Autowired
	coronaVirusDataService service;
	@GetMapping("/")
 public String home(Model model) {
		List<locationStats> allStats = service.getAllStats();
		
		// adding the total value of reported case
		int totalReportedCases = allStats.stream().mapToInt(stat->stat.getLatestTotalCase()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat->stat.getDifferenceFromPrevDay()).sum();
		
		// forwarding the parameter to our html page 
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);

	 return "home";
 }
}
