package com.finalTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class SearchController {

    @Autowired
    PageParseService pageParseService;

    @RequestMapping(method = RequestMethod.GET)
    public String openForm(ModelMap modelMap) {
        modelMap.addAttribute("searchForm", new SearchForm());
        return "index";
    }

    @RequestMapping(value = "/searchResult", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("searchForm")SearchForm searchForm, ModelMap model) {
        pageParseService.processPage(searchForm);
        return "searchResult";
    }
}
