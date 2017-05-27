package com.ver2point0.android.thegreatestnews.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ver2point0.android.thegreatestnews.Models.GreatestNews;
import com.ver2point0.android.thegreatestnews.R;

import java.util.ArrayList;

public class GreatestNewsAdapter extends ArrayAdapter<GreatestNews> {

    public GreatestNewsAdapter(Context context) {
        super(context, -1, new ArrayList<GreatestNews>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.text_view_title);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.text_view_author);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.text_view_date);
        TextView topicTextView = (TextView) convertView.findViewById(R.id.text_view_topic);

        GreatestNews greatestNews = getItem(position);
        titleTextView.setText(greatestNews.getTitle());
        authorTextView.setText(greatestNews.getAuthor());
        dateTextView.setText(greatestNews.getDate());
        topicTextView.setText(greatestNews.getTopic());

        return convertView;
    }

}
