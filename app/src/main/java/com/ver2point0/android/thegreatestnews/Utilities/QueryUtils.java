package com.ver2point0.android.thegreatestnews.Utilities;

import android.net.Uri;
import android.util.Log;

import com.ver2point0.android.thegreatestnews.Models.GreatestNews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public static URL createUrl() {
        String url = createStringUrl();
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error creating URL: ", e);
            return null;
        }
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("MainActivity", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error making HTTP request: ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    public static List<GreatestNews> parseJson(String response) {
        ArrayList<GreatestNews> newsArrayList = new ArrayList<>();
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONObject jsonResults = jsonResponse.getJSONObject("response");
            JSONArray resultsArray = jsonResults.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject result = resultsArray.getJSONObject(i);
                String webTitle = result.getString("webTitle");
                String webUrl = result.getString("webUrl");
                String webPublicationDate = result.getString("webPublicationDate");
                webPublicationDate = formatDate(webPublicationDate);
                String sectionName = result.getString("sectionName");
                JSONArray tags = result.getJSONArray("tags");
                String webAuthor = "";

                if (tags.length() == 0) {
                    webAuthor = null;
                } else {
                    for (int j = 0; j < tags.length(); j++) {
                        JSONObject object = tags.getJSONObject(j);
                        webAuthor += object.getString("webTitle");
                    }
                }
                newsArrayList.add(new GreatestNews(webTitle, webAuthor, webUrl, webPublicationDate, sectionName));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing JSON Response", e);
        }
        return newsArrayList;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    private static String formatDate(String rawDate) {
        String jsonDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat jsonFormatter = new SimpleDateFormat(jsonDatePattern, Locale.US);
        try {
            Date parsedJsonDate = jsonFormatter.parse(rawDate);
            String finalDatePattern = "MMM d, yyy";
            SimpleDateFormat finalDateFormatter = new SimpleDateFormat(finalDatePattern, Locale.US);
            return finalDateFormatter.format(parsedJsonDate);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Error parsing JSON Date: ", e);
            return "";
        }
    }

    private static String createStringUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .encodedAuthority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter("order-by", "newest")
                .appendQueryParameter("show-references", "author")
                .appendQueryParameter("show-tags", "contributor")
                .appendQueryParameter("q", "Android")
                .appendQueryParameter("api-key", "test");
        return builder.build().toString();
    }
}
