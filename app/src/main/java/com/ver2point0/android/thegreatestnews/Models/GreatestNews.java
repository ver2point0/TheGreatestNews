package com.ver2point0.android.thegreatestnews.Models;

public class GreatestNews {

    private final String mTitle;
    private final String mAuthor;
    private final String mUrl;
    private final String mDate;
    private final String mTopic;

    public GreatestNews(String title, String author, String url, String date, String topic) {
        mTitle = title;
        mAuthor = author;
        mUrl = url;
        mDate = date;
        mTopic = topic;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getDate() {
        return mDate;
    }

    public String getTopic() {
        return mTopic;
    }
}
