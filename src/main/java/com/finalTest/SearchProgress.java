package com.finalTest;

import java.util.List;

public class SearchProgress {

    private boolean searchFinished;
    private List<SearchResultItem> pageList;

    public SearchProgress(boolean searchFinished, List<SearchResultItem> pageList) {
        this.searchFinished = searchFinished;
        this.pageList = pageList;
    }

    public boolean isSearchFinished() {
        return searchFinished;
    }

    public List<SearchResultItem> getPageList() {
        return pageList;
    }
}