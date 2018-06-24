package com.finalTest;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageParseService {

    @Autowired
    PageLoadService pageLoadService;
    @Autowired
    LinkSearchService linkSearchService;
    @Autowired
    TextSearchService textSearchService;

    public void processPage(SearchForm searchForm){
        Document document = pageLoadService.loadPage(searchForm);
        linkSearchService.searchLinks(document);
        textSearchService.searchText(document, searchForm.getTextToSearch());
    }
}
