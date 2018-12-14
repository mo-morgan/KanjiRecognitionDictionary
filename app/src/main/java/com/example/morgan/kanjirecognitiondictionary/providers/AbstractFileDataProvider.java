package com.example.morgan.kanjirecognitiondictionary.providers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Common functionality for file data providers
 */
abstract class AbstractFileDataProvider implements DataProvider {

    /**
     * Read source data from input stream as string
     *
     * @param is  input stream connected to source data
     * @return  source data as string
     * @throws IOException  when error occurs reading data from file
     */
    String readSource(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        br.close();

        return sb.toString();
    }

    public static byte[] readSourceRaw(InputStream is) {
        try {
            final int maxlen = 4 * 1024 * 1024;

            byte[] bytes = new byte[maxlen];
            int nread = is.read(bytes, 0, maxlen);
            byte[] xbytes = new byte[1];
            int xnread = is.read(xbytes, 0, 1);
            byte[] ans = new byte[nread];
            System.arraycopy(bytes, 0, ans, 0, nread);
            return ans;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}