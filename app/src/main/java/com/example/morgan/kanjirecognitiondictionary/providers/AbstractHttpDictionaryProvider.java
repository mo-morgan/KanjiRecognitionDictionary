package com.example.morgan.kanjirecognitiondictionary.providers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Abstract Http data provider
 */
public abstract class AbstractHttpDictionaryProvider implements DataProvider {
    protected HttpURLConnection conn;

    public AbstractHttpDictionaryProvider() {
        conn = null;
    }

    @Override
    public String dataSourceToString() throws IOException {
        URL url = getUrl();
        StringBuilder jsonResults = readData(url);
        return jsonResults.toString();
    }

    private StringBuilder readData(URL url) {
        StringBuilder jsonResults = new StringBuilder();
        try {
            configureConnection(url);
            jsonResults = loadResponse();
        } catch (Exception e) {
            jsonResults.append("Error");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return jsonResults;
    }

    private void configureConnection(URL url) throws IOException {
        final int HTTP_OK = 200;           // HTTP success code
        final int CONNECT_TIMEOUT = 2000;  // timeout in ms
        final int READ_TIMEOUT = 3000;     // timeout in ms

        conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(CONNECT_TIMEOUT);
        conn.setRequestProperty("Accept", "application/JSON");
        conn.setReadTimeout(READ_TIMEOUT);
        conn.connect();
        int response = conn.getResponseCode();
        if (response != HTTP_OK) {
            throw new IOException("HTTP response code:" + response + "- failed to obtain data");
        }
    }

    private StringBuilder loadResponse() throws IOException {
        StringBuilder jsonResults = new StringBuilder();
        InputStreamReader in = new InputStreamReader(conn.getInputStream());

        // Load the results into a StringBuilder
        int read;
        char[] buff = new char[1024];
        while ((read = in.read(buff)) != -1) {
            jsonResults.append(buff, 0, read);
        }

        return jsonResults;
    }

    /**
     * Get URL of Http data provider
     *
     * @return URL of http data provider
     * @throws MalformedURLException when URL is malformed
     */
    protected abstract URL getUrl() throws MalformedURLException;
}