package it.furtmeier.sms2url;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by nemiah on 13.05.17.
 */

public class TaskGET extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... data) {
        HttpURLConnection urlConnection = null;
        try {

            URL url = new URL(data[0]+"?device="+URLEncoder.encode(data[1], "utf-8")+"&phone="+URLEncoder.encode(data[2], "utf-8")+"&text="+URLEncoder.encode(data[3], "utf-8"));

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.getInputStream();

            //InputStreamReader isw = new InputStreamReader(in);

            /*int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                System.out.print(current);
            }*/
            //TV.append("Done Testing...\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }
}
