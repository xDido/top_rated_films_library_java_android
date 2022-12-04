package com.example.recyclerdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.InputStream;
import java.net.URL;

public class DownLoadImageActivity extends Activity {
    EditText et;
    ImageView img;
    Button b;
    ProgressBar progressBar;
    String url = "https://images.alphacoders.com/651/thumb-1920-651875.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_image);
        et = (EditText) findViewById(R.id.editText1);
        img = (ImageView) findViewById(R.id.imageView1);
        et.setText(url.toString());
        progressBar=(ProgressBar) findViewById(R.id.progress);
        b.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    DownloadTask downloadTask = new DownloadTask();
                    downloadTask.execute(et.getText().toString());
                }else
                    Toast.makeText(getApplicationContext(),
                            "No Internet connection", Toast.LENGTH_SHORT)
                            .show();
            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectMgr.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }
    public class DownloadTask extends AsyncTask<String, Void, Bitmap> {
        Bitmap bitmap = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected Bitmap doInBackground(String[] params) {
            String url = params[0];
            bitmap = downloadImage(url);
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            if (result != null) {
                img.setImageBitmap(result);
            }
        }
    }
    public Bitmap downloadImage(String url) {
        Bitmap bitmap = null;

        try {
            InputStream is = (InputStream) new URL(url).getContent();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            return null;
        }
        return bitmap;
    }

}
