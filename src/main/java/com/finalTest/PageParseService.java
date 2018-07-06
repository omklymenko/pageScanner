package com.finalTest;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class PageParseService {

    private static final Integer DEFAULT_MAX_NUMBER_OF_URL = 20;

    @Autowired
    private LoadService pageLoadService;
    @Autowired
    private LinkSearchService linkSearchService;
    @Autowired
    private TextSearchService textSearchService;

    private Queue<Future<Document>> documentQueue = new ArrayDeque<>();
    private Queue<String> linksQueue = new ArrayDeque<>();
    int count = 0;
    boolean stopSearch = false;
    boolean searchFinished = false;
    private List<SearchResultItem> searchResultItemList = new ArrayList<>();

    public void processPage(SearchForm searchForm){
        Integer maxNumberOfUrlsToLoad = Optional.ofNullable(searchForm.getMaxNumberOfUrl()).orElse(DEFAULT_MAX_NUMBER_OF_URL);
        while(!documentQueue.isEmpty() && !stopSearch) {
            String currentLink = linksQueue.poll();
            Future<Document> future = documentQueue.poll();
            SearchResultItem searchResultItem = new SearchResultItem();
            searchResultItem.setLink(currentLink);
            searchResultItemList.add(searchResultItem);
            searchResultItem.setStatus(Status.DOWNLOAD);
            Document document = null;
            try {
                document = future.get();
            } catch (InterruptedException | ExecutionException e) {
                searchResultItem.setStatus(Status.ERROR);
                e.printStackTrace();
                searchResultItem.setErrorMessage(e.getMessage());
                continue;
            }
            List<String> links = linkSearchService.searchLinks(document);
            for (String link : links) {
                if(count < maxNumberOfUrlsToLoad) {
                    documentQueue.add(pageLoadService.loadPage(link));
                    linksQueue.add(link);
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
        searchFinished = true;
    }

    public SearchProgress getCurrentProgress() {
        return new SearchProgress(searchFinished, searchResultItemList);
    }

    public void startSearch(SearchForm searchForm) {
        documentQueue.clear();
        linksQueue.clear();
        stopSearch = false;
        searchFinished = false;
        pageLoadService.init(searchForm.getNumOfThreads());
        linksQueue.add(searchForm.getUrl());
        documentQueue.add(pageLoadService.loadPage(searchForm.getUrl()));
        new Thread(() -> {
            processPage(searchForm);
        }).start();
    }

    public void stopSearch() {
        stopSearch = true;
    }
}