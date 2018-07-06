package com.finalTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class PageLoadService implements LoadService {

    private static final Integer DEFAULT_NUM_OF_THREADS = 5;

    private ExecutorService threadPool;

    public void init(Integer numOfThreads){
        Integer numOfThreadsToStart = Optional.ofNullable(numOfThreads).orElse(DEFAULT_NUM_OF_THREADS);
        threadPool = Executors.newFixedThreadPool(numOfThreadsToStart);
    }

    public Future<Document> loadPage(String url){

        return threadPool.submit(new Callable<Document>() {
            public Document call() {
                System.out.println(Thread.currentThread().getName());
                Document document = null;
                try {
                    document = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return document;
            }
        });
    }
}