package com.finalTest;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class PageParseService {

    @Autowired
    private PageLoadService pageLoadService;
    @Autowired
    private LinkSearchService linkSearchService;
    @Autowired
    private TextSearchService textSearchService;

    private Queue<String> documentQueue = new PriorityQueue<>();
    int count = 0;
    private List<SearchResultItem> searchResultItemList = new ArrayList<>();

    public void processPage(SearchForm searchForm){
        documentQueue.add(searchForm.getUrl());
        while(!documentQueue.isEmpty()) {
            SearchResultItem searchResultItem = new SearchResultItem();
            searchResultItemList.add(searchResultItem);
            searchResultItem.setLink(documentQueue.peek());
            searchResultItem.setStatus(Status.DOWNLOAD);
            Future<Document> future = pageLoadService.loadPage(documentQueue.poll());
            Document document = null;
            try {
                document = future.get();
            } catch (InterruptedException | ExecutionException e) {
                searchResultItem.setStatus(Status.ERROR);
                e.printStackTrace();
            }
            List<String> links = linkSearchService.searchLinks(document);
            for (String link : links) {
                if(count < 5) {
                    documentQueue.add(link);
                    count++;
                }
            }
            boolean searchResult = textSearchService.searchText(document, searchForm.getTextToSearch());
            if(searchResult) {
                searchResultItem.setStatus(Status.FOUND);
            } else {
                searchResultItem.setStatus(Status.NOT_FOUND);
            }
        }
    }
}