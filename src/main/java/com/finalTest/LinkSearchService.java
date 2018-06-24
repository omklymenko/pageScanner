package com.finalTest;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkSearchService {

    public List<String> searchLinks(Document document){
        return document.select("a").stream().map(element -> element.attr("abs:href")).collect(Collectors.toList());
    }
}
