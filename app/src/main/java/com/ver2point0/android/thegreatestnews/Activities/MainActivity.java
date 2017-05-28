package com.ver2point0.android.thegreatestnews.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ver2point0.android.thegreatestnews.Adapters.GreatestNewsAdapter;
import com.ver2point0.android.thegreatestnews.Loaders.GreatestNewsLoader;
import com.ver2point0.android.thegreatestnews.Models.GreatestNews;
import com.ver2point0.android.thegreatestnews.R;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<GreatestNews>>, SwipeRefreshLayout.OnRefreshListener {

    private GreatestNewsAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static int LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        ListView listView = (ListView) findViewById(R.id.activity_main_list_view);
        mAdapter = new GreatestNewsAdapter(this);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GreatestNews greatestNews = mAdapter.getItem(position);
                String url = greatestNews.getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            }
        });
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<GreatestNews>> onCreateLoader(int i, Bundle bundle) {
        return new GreatestNewsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<GreatestNews>> loader, List<GreatestNews> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (data != null) {
            mAdapter.setNotifyOnChange(false);
            mAdapter.clear();
            mAdapter.setNotifyOnChange(true);
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<GreatestNews>> loader) {
        mAdapter.clear();
    }

    @Override
    public void onRefresh() {
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }

}
