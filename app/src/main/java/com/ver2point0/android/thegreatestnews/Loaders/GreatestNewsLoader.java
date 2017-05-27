package com.ver2point0.android.thegreatestnews.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.ver2point0.android.thegreatestnews.Models.GreatestNews;
import com.ver2point0.android.thegreatestnews.Utilities.QueryUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GreatestNewsLoader extends AsyncTaskLoader<List<GreatestNews>> {

    private static final String LOG_TAG = GreatestNewsLoader.class.getName();

    public GreatestNewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<GreatestNews> loadInBackground() {
        List<GreatestNews> greatestNewsList = null;
        try {
            URL url = QueryUtils.createUrl();
            String jsonResponse = QueryUtils.makeHttpRequest(url);
            greatestNewsList = QueryUtils.parseJson(jsonResponse);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error Loader LoadInBackground: ", e);
        }
        return greatestNewsList;
    }

}
