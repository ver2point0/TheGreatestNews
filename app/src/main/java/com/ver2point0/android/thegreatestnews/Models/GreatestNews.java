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

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTopic() {
        return mTopic;
    }

    public void setTopic(String topic) {
        mTopic = topic;
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
