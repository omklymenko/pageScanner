package com.finalTest;

import org.springframework.util.StringUtils;

public class SearchForm {

    private String url;
    private String textToSearch;
    private Integer numOfThreads;
    private Integer maxNumberOfUrl;

    private final Integer defaultNumOfThreads = 5;
    private final Integer defaultMaxNumberOfUrl = 20;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTextToSearch() {
        return textToSearch;
    }

    public void setTextToSearch(String textToSearch) {
        this.textToSearch = textToSearch;
    }

    public Integer getNumOfThreads() {
        if(!StringUtils.isEmpty(numOfThreads)) {
            return numOfThreads;
        } else {
            setNumOfThreads(defaultNumOfThreads);
            return defaultNumOfThreads;
        }
    }

    public void setNumOfThreads(Integer numOfThreads) {
        this.numOfThreads = numOfThreads;
    }

    public Integer getMaxNumberOfUrl() {
        if(!StringUtils.isEmpty(maxNumberOfUrl)){
            return maxNumberOfUrl;
        } else {
            setMaxNumberOfUrl(defaultMaxNumberOfUrl);
            return defaultMaxNumberOfUrl;
        }
    }

    public void setMaxNumberOfUrl(Integer maxNumberOfUrl) {
        this.maxNumberOfUrl = maxNumberOfUrl;
    }
}
