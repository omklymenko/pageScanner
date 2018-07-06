package com.finalTest;

public class SearchForm {

    private String url;
    private String textToSearch;
    private Integer numOfThreads;
    private Integer maxNumberOfUrl;

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
        return numOfThreads;
    }

    public void setNumOfThreads(Integer numOfThreads) {
        this.numOfThreads = numOfThreads;
    }

    public Integer getMaxNumberOfUrl() {
        return maxNumberOfUrl;
    }

    public void setMaxNumberOfUrl(Integer maxNumberOfUrl) {
        this.maxNumberOfUrl = maxNumberOfUrl;
    }
}
