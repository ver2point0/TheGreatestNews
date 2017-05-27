package com.ver2point0.android.thegreatestnews.Models;

public class GreatestNews {

    private String mTitle;
    private String mAuthor;
    private String mUrl;
    private String mDate;
    private String mTopic;

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

    @Override
    public String toString() {
        return "Greatest News{" +
                "Title='" + mTitle + '\'' +
                ", Author='" + mAuthor + '\'' +
                ", Url='" + mUrl + '\'' +
                ", Date='" + mDate + '\'' +
                ", Topic='" + mTopic + '\'' +
                '}';
    }
}
