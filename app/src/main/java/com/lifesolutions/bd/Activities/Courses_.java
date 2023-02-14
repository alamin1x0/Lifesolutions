package com.lifesolutions.bd.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.lifesolutions.bd.Adapters.CourseAdapterJava;
import com.lifesolutions.bd.Api.Data;
import com.lifesolutions.bd.Api.GetCourses;
import com.lifesolutions.bd.Api.RetrofitClientForCourse;
import com.lifesolutions.bd.Api.ServerApi;
import com.lifesolutions.bd.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Courses_ extends AppCompatActivity {

    RecyclerView recyclerView;
    ServerApi serverApi;

   // ArrayList<Course> cousesList=new ArrayList<>();
    CourseAdapterJava adapter;
    ArrayList<Data> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        recyclerView=findViewById(R.id.recyclerViewCourses);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllCourses();



    }


    private void setAdapter(List<Data> courseList){

        adapter=new CourseAdapterJava(this,courseList);
        recyclerView.setAdapter(adapter);

    }
    private void getAllCourses(){


        // Retrofit API Call..
        Call<GetCourses> call = RetrofitClientForCourse
                .getInstance()
                .getServerApi()
                .getAllCourses();


        call.enqueue(new Callback<GetCourses>() {
            @Override
            public void onResponse(Call<GetCourses> call, Response<GetCourses> response) {

                Log.d("FDDK", "onResponse: "+response.body().getDataList().get(0).getCourseName());
                setAdapter(response.body().getDataList());
                adapter.notifyDataSetChanged();

                String id=response.body().getDataList().get(0).getCourseID();
                getCourseId(id);

            }

            @Override
            public void onFailure(Call<GetCourses> call, Throwable t) {

            }
        });


    }

    private void getCourseId(String courseId){

    }
}