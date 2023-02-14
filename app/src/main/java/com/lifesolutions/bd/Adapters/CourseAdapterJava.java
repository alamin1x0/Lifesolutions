package com.lifesolutions.bd.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.lifesolutions.bd.Activities.CourseWebView;
import com.lifesolutions.bd.Api.Data;
import com.lifesolutions.bd.Api.GetCourses;
import com.lifesolutions.bd.Api.RetrofitClientForCourse;
import com.lifesolutions.bd.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseAdapterJava extends RecyclerView.Adapter<CourseAdapterJava.CourseHolder> {

    Context context;
    List<Data> courseList;
    String id;

    public CourseAdapterJava(Context context, List<Data> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @NotNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.course_card_view_hz, parent, false);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, @SuppressLint("RecyclerView") int position) {

        // Retrofit API Call..
        Call<GetCourses> call = RetrofitClientForCourse
                .getInstance()
                .getServerApi()
                .getAllCourses();


        call.enqueue(new Callback<GetCourses>() {
            @Override
            public void onResponse(Call<GetCourses> call, Response<GetCourses> response) {

             //   adapter.notifyDataSetChanged();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(context, CourseWebView.class);
                        intent.putExtra("CourseID", response.body().getDataList().get(position).getCourseID());
                        context.startActivity(intent);

                    }
                });

            }

            @Override
            public void onFailure(Call<GetCourses> call, Throwable t) {

            }
        });
        


        Glide.with(context).load(courseList.get((position)).getImage()).into(holder.imageView);
        holder.priceText.setText(courseList.get(position).getPrice());
        holder.teacherName.setText(courseList.get(position).getAuthor().getAuthorName());
        holder.CoursenameText.setText(courseList.get(position).getCourseName());



    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class CourseHolder extends RecyclerView.ViewHolder {
        RoundedImageView imageView;
        TextView priceText, CoursenameText, teacherName;

        public CourseHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_profile_img);
            priceText = itemView.findViewById(R.id.tv_price_text);
            teacherName = itemView.findViewById(R.id.tv_teacher_name);
            CoursenameText = itemView.findViewById(R.id.tv_course_name);
        }
    }

}
