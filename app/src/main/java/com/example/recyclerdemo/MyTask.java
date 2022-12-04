package com.example.recyclerdemo;

import android.os.AsyncTask;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyTask extends AsyncTask<String, Void, String> {
    MainActivity activity;

    public MyTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            if (conn.getResponseCode() != 200) {
                throw new IOException(conn.getResponseMessage());
            }

            BufferedReader buffrd = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = buffrd.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
            buffrd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    protected void onPostExecute(String s) {
        if (s != null) {
            Gson gson = new Gson();
            MoviesModel response = gson.fromJson(s, MoviesModel.class);
            Toast.makeText(activity,response.getTotal_pages(),Toast.LENGTH_SHORT).show();
            activity.updateData(response.getMovies());
        }
    }

}
