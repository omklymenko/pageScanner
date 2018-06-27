package com.finalTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import sun.management.counter.Counter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class PageLoadService implements LoadService {

    private ExecutorService threadPool;

    public void init(Integer numOfThreads){
        threadPool = Executors.newFixedThreadPool(numOfThreads);
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