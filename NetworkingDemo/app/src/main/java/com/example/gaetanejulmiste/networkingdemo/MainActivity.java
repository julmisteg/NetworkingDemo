package com.example.gaetanejulmiste.networkingdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {


    private static final String IMAGE_URL ="http://cdn2-www.dogtime.com/assets/uploads/gallery/30-impossibly-cute-puppies/impossibly-cute-puppy-8.jpg";
    private ImageView ivBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


       /* Version 1
       super.onCreate(savedInstanceState);
         disableStrictPolicy();
        setContentView(R.layout.activity_main);
        ivBitmap = (ImageView) findViewById(R.id.ivBitmap);
        Bitmap b = downloadImage(IMAGE_URL);
        ivBitmap.setImageBitmap(b);
        */

       /*  VERSION 2
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivBitmap = (ImageView) findViewById(R.id.ivBitmap);
        new ImageDownloadTask().execute(IMAGE_URL);

        */

       // Version 3
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivBitmap = (ImageView) findViewById(R.id.ivBitmap);
        downloadImageAsync(IMAGE_URL);


    }


    // Version 3
    private void downloadImageAsync(String imageUrl) {
        AsyncHttpClient aClient = new AsyncHttpClient();
        String[] imageTypes = new String[]{"image/jpeg"};
        aClient.get(imageUrl, new BinaryHttpResponseHandler(imageTypes) {



            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] imageTypes) {
                InputStream in = new ByteArrayInputStream(imageTypes);
                Bitmap bmp = BitmapFactory.decodeStream(in);
                ivBitmap.setImageBitmap(bmp);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                Toast.makeText(getBaseContext(),"Image could not be found !!!", Toast.LENGTH_SHORT).show();
            }


        });
    }



    /*
      Version 1

    private Bitmap downloadImage(String imageUrl) {
        URL url = getUrlFromString(imageUrl);
        InputStream in = getInputStream(url);
        Bitmap bmp =decodeBitmap(in);
        return bmp;
    }

  //step 1
    private URL getUrlFromString(String imageUrl) {
        URL url ;
        try{
            url = new URL(imageUrl);

        }catch (MalformedURLException m){
            url =null;
        }
        return url;
    }

    //step2
    private InputStream getInputStream(URL url) {
        InputStream in;
        URLConnection conn;
        try{
            conn = url.openConnection();
            conn.connect();
            in = conn.getInputStream();

        }catch(IOException e){
            in =null;

        }
        return in;
    }

    //step3
    private Bitmap decodeBitmap(InputStream in) {
        Bitmap btmap ;
        try {
            btmap = BitmapFactory.decodeStream(in);
            in.close();
        }catch (IOException e){
            in =null;
            btmap = null;
        }
        return btmap;
    }
       private void disableStrictPolicy(){
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder().permitNetwork().build()

        );
    }*/



    /* VERSION 2


    private class ImageDownloadTask extends AsyncTask<String,Void,Bitmap>{
        @Override
        protected Bitmap doInBackground(String... adresses) {
            URL url = getUrlFromString(adresses[0]);
            InputStream in = getInputStream(url);
            Bitmap bmp =decodeBitmap(in);
            return bmp;
        }



        private URL getUrlFromString(String imageUrl) {
            URL url ;
            try{
                url = new URL(imageUrl);

            }catch (MalformedURLException m){
                url =null;
            }
            return url;
        }


        private InputStream getInputStream(URL url) {
            InputStream in;
            URLConnection conn;
            try{
                conn = url.openConnection();
                conn.connect();
                in = conn.getInputStream();

            }catch(IOException e){
                in =null;

            }
            return in;
        }


        private Bitmap decodeBitmap(InputStream in) {
            Bitmap btmap ;
            try {
                btmap = BitmapFactory.decodeStream(in);
                in.close();
            }catch (IOException e){
                in =null;
                btmap = null;
            }
            return btmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            ivBitmap.setImageBitmap(result);
        }
    }


*/




}





