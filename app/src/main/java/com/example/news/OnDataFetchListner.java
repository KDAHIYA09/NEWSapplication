package com.example.news;

import com.example.news.models.news_Headlines;

import java.util.List;

public interface OnDataFetchListner<NewsApiResponse> {
    void onFetchData(List<news_Headlines> list, String message);
    void onError(String message);
}
