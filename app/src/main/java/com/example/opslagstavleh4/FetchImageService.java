package com.example.opslagstavleh4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;
import android.view.View;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchImageService extends Service {
    public FetchImageService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://reqres.in/api/users?/page=2";

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
                if (response.isSuccessful()){
                    String myResponse = response.body().string();

//                    MainActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            TextView myTextView =  (TextView)findViewById(R.id.image1);
//                        }
//                    });
                }
            }
        });
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}