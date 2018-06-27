package com.finalTest;

import org.jsoup.nodes.Document;

import java.util.concurrent.Future;

public interface LoadService {

    void init(Integer numOfThreads);
    Future<Document> loadPage(String url);
}
