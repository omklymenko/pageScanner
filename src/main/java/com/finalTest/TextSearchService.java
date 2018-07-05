package com.finalTest;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TextSearchService {

    public boolean searchText(Document document, String text){
        Pattern pattern = Pattern.compile("^.*\\b(" + text + ")\\b.*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(document.body().text());
        return matcher.find();
    }
}
