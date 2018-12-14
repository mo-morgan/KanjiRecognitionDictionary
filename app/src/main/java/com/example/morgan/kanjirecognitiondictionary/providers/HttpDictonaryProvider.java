package com.example.morgan.kanjirecognitiondictionary.providers;

import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpDictonaryProvider extends AbstractHttpDictionaryProvider {
    private static String word;

    @Override
    protected URL getUrl() throws MalformedURLException {
        String webaddress = "https://jisho.org/api/v1/search/words?keyword=" + word;
        Log.d("website is: ", webaddress);
        return new URL(webaddress);
    }

    @Override
    public byte[] dataSourceToBytes() throws IOException {
        return new byte[0];
    }

    public void setWord(String word) {
        this.word = word;
    }

    public static URL getURL() throws MalformedURLException {
        String webaddress = "https://jisho.org/api/v1/search/words?keyword=" + word;
        Log.d("website is: ", webaddress);
        return new URL(webaddress);
    }
}
