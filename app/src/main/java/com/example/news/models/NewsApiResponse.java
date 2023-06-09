package com.example.news.models;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {
    String status;
    int totalResults;
    List<news_Headlines> articles ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<news_Headlines> getArticles() {
        return articles;
    }

    public void setArticles(List<news_Headlines> articles) {
        this.articles = articles;
    }
}
