package com.service.retrofitdemo.controller.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.service.retrofitdemo.R;
import com.service.retrofitdemo.controller.network.RestAppController;
import com.service.retrofitdemo.model.adapters.GalleryAdapter;
import com.service.retrofitdemo.model.pojo.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ajaya on 20/9/17.
 */

public class GalleryDemoActivity extends AppCompatActivity {
    private ArrayList<Image> images;
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        pDialog = new ProgressDialog(this);
        images = new ArrayList<>();
        mAdapter = new GalleryAdapter(GalleryDemoActivity.this, images);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //request for images
        Call<JSONObject> call = RestAppController.getWsController().fetchImageData("6em7h");
        call.enqueue(new Callback<JSONObject>() {

            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                JSONArray arrayData = response.body().optJSONArray("data");//.optJSONArray("data");
                if(arrayData!= null && arrayData.length()>0){
                    for (int index=0;index<arrayData.length();index++){
                        try {
                            JSONObject object = arrayData.optJSONObject(index);
                            Image image = new Image();
                            image.setName(object.getString("name"));

                            JSONObject url = object.getJSONObject("url");
                            image.setSmall(url.getString("small"));
                            image.setMedium(url.getString("medium"));
                            image.setLarge(url.getString("large"));
                            image.setTimestamp(object.getString("timestamp"));

                            images.add(image);

                        } catch (JSONException e) {
                            Log.e("TAG", "Json parsing error: " + e.getMessage());
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        });
        //Glide.with(GalleryDemoActivity.this)
        }

    }