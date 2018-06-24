package com.finalTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PageLoadService {

    public Document loadPage(SearchForm searchForm){
        Document document = null;
        try {
            document = Jsoup.connect(searchForm.getUrl()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}