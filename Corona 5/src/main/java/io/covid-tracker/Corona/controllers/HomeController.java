package io.javabrains.Corona.controllers;
import io.javabrains.Corona.models.LocationStats;
import io.javabrains.Corona.services.coronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    coronaVirusDataService coronaVirusDataService;
    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allstats  = coronaVirusDataService.getAllstat();
        int totalReportedCases = allstats.stream().mapToInt(stat ->stat.getLatestTotalCases()).sum();
        int totalNewCases = allstats.stream().mapToInt(stat -> stat.getDiffFromPreDay()).sum() ;
        model.addAttribute("LocationStats",allstats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);


        return "home";
    }

}
