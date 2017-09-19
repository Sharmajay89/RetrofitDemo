package com.service.retrofitdemo.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.service.retrofitdemo.R;
import com.service.retrofitdemo.model.pojo.UserInfo;
import com.service.retrofitdemo.controller.network.RestAppController;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        ImageView imageView = (ImageView)findViewById(R.id.thumbnail);

        Glide.with(MainActivity.this).load("https://api.android/hive.info/images/glide/medium/deadpool.jpg")
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GalleryDemoActivity.class));
                Map<String ,RequestBody> postData = new LinkedHashMap<>();
                //postData.put("file",createMultiPartRequestBody(getAssets().open("")));

            }
        });
    }

    public static RequestBody createMultiPartRequestBody(@NonNull String fileUrl){
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),new File(fileUrl));
        return requestBody;
    }

    public static void postFormDataRequest(Map<String, RequestBody> sendData){
       /* Call<String> clickCall = RestAppController.getWsController().postCapturedData("",sendData);
        clickCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {

                    Log.e("Response:SUCESS", response.toString());
                } else {
                    Log.e("Response:FAIULRE", response.toString());
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Response:FAIULRE", t.getLocalizedMessage());
            }
        });*/
    }
public static void getUseInfoRequest(){
        Call<UserInfo> clickCall = RestAppController.getWsController().getUsers("14e0n9");
        clickCall.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()) {

                    Log.e("Response:SUCESS", response.body().getName());
                } else {
                    Log.e("Response:FAIULRE", response.toString());
                }

            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.e("Response:FAIULRE", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
