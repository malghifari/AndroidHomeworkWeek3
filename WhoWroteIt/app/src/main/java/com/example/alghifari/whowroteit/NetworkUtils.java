package com.example.alghifari.whowroteit;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String BOOK_BASE_URL =  "https://www.googleapis.com/books/v1/volumes?"; // Base URI for the Books API
    private static final String QUERY_PARAM = "q"; // Parameter for the search string
    private static final String MAX_RESULTS = "maxResults"; // Parameter that limits search results
    private static final String PRINT_TYPE = "printType";   // Parameter to filter by print type

    static String getBookInfo(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJsonString = null;
        try {
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                // Move to buffer with newline to make debugging easier
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Strean was empty
                return null;
            }
            bookJsonString = buffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            return bookJsonString;
        }
    }
}