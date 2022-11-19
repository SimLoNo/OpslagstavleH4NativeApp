package com.example.opslagstavleh4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    OkHttpClient client;
    String url = "http://10.108.137.14:45893/WeatherForecast";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new OkHttpClient();

        Button getImagesButton = (Button)findViewById(R.id.getImagesButton);
        getImagesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getAllImages();
            }
        });

        ImageView imageView1= (ImageView)findViewById(R.id.image1);
        imageView1.setOnLongClickListener(new View.OnLongClickListener() {

            float x;
            float y;
            float dx;
            float dy;
            @Override
            public boolean onLongClick(View view) {
                imageView1.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                            x = motionEvent.getX();
                            y = motionEvent.getY();
                        }
                        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                            dx = motionEvent.getX() - x;
                            dy = motionEvent.getY() - y;

                            view.setX(view.getX() +dx);
                            view.setY(view.getY() +dy);

                            x = motionEvent.getX();
                            y = motionEvent.getY();
                        }
                        return true;
                    }
                });
                return false;
            }
        });

        ImageView imageView2= (ImageView)findViewById(R.id.image2);
        imageView2.setOnLongClickListener(new View.OnLongClickListener() {

            float x;
            float y;
            float dx;
            float dy;
            @Override
            public boolean onLongClick(View view) {
                imageView2.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                            x = motionEvent.getX();
                            y = motionEvent.getY();
                        }
                        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                            dx = motionEvent.getX() - x;
                            dy = motionEvent.getY() - y;

                            view.setX(view.getX() +dx);
                            view.setY(view.getY() +dy);

                            x = motionEvent.getX();
                            y = motionEvent.getY();
                        }
                        return true;
                    }
                });
                return false;
            }
        });

    }

    public void getAllImages(){
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                JSONArray myResponse;
                Object[] imageStrings;
                if (response.isSuccessful()){
                    try {
                        myResponse = new JSONArray(response.body().string());
                        imageStrings = new String[myResponse.length()];
                        for(Integer i = 0; i < myResponse.length(); i++){
                            imageStrings[i] = myResponse.get(i);
                        }MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                FrameLayout frame = (FrameLayout)findViewById(R.id.dragBoard);
                                for (Object imageString: imageStrings
                                ) {
                                    imageString.getClass()
                                }
                                TextView myTextView =  (TextView)findViewById(R.id.titleText);
                                myTextView.setText(imageStrings[0]);
                            }
                        });

                    }
                    catch (JSONException exception){

                    }

                    };



                }
            });
        };
    }

//    float x;
//    float y;
//    float dx;
//    float dy;
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN){
//            x = event.getX();
//            y = event.getY();
//        }
//        if (imageView != null){
//
//            if (event.getAction() == MotionEvent.ACTION_MOVE){
//                dx = event.getX() - x;
//                dy = event.getY() - y;
//
//                imageView.setX(imageView.getX() +dx);
//                imageView.setY(imageView.getY() +dy);
//
//                x = event.getX();
//                y = event.getY();
//            }
//        }
//        return super.onTouchEvent(event);
//    }
//
//    public void onImageClicked(View v){
//        imageView = (ImageView)v;
//    }
