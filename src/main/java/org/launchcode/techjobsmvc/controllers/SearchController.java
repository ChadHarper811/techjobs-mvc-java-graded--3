package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.im.spi.InputMethod;
import java.util.ArrayList;
import java.util.List;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    private static List<String> jobs = new ArrayList<>();

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping(value="")
    public String displaySearchResults(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {
        String searchJobs;
        List<String> jobs = new ArrayList<>();
        if (searchTerm.equals("") || searchTerm.toLowerCase().equals("all")) {
            searchJobs = String.valueOf(JobData.findAll());
        } else {
            searchJobs = String.valueOf(JobData.findByColumnAndValue(searchType, searchTerm));
        }
        jobs.add(searchJobs);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("jobs", jobs);
        return "redirect:/search";
    }

}

