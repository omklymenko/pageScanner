package com.finalTest;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class TextSearchService {

    public boolean searchText(Document document, String text){
        return document.body().text().contains(text);
    }
}
