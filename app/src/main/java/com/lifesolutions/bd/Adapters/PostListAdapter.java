package com.lifesolutions.bd.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Activities.ProfileActivity;
import com.lifesolutions.bd.Activities.UserProfileActivity;
import com.lifesolutions.bd.Api.RetrofitClient;
import com.lifesolutions.bd.Api.UrlType;
import com.lifesolutions.bd.Helpers.TimeAgo;
import com.lifesolutions.bd.Models.NotificationInApp;
import com.lifesolutions.bd.Models.PostItem;
import com.lifesolutions.bd.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder>{

    private Context mContext;
    private ArrayList<PostItem> postItems;
    private String imageLink;
    private DatabaseReference databaseReference;
    private String uID;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private UrlType serverResponse;
    private PostItemClickListener postItemClickListener;

    public PostListAdapter(Context mContext, PostItemClickListener postItemClickListener) {
        this.postItems = new ArrayList<>();
        this.mContext = mContext;
        this.postItemClickListener = postItemClickListener;
    }

    public void addAllPosts(ArrayList<PostItem> newPosts){
        int initSize = postItems.size();
        postItems.addAll(newPosts);
        notifyItemRangeChanged(initSize,newPosts.size());
    }

    public String getLastPostId(){
        return postItems.get(postItems.size()-1).getPostID();
    }

    public void removeAllItem(){
        postItems.clear();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(mContext).inflate(R.layout.post_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, final int position) {

        uID = mAuth.getUid();

        // SAZIB....

        if (!postItems.get(position).getPostDescription().equals(" ")) {
            List<String> links = hasUrl(postItems.get(position).getPostDescription());

            if (links.size() > 0) {
                holder.post_image.setVisibility(View.VISIBLE);
                urlEncoder(postItems.get(position).getPostDescription(), links.get(0), holder);
            } else {
                holder.linkViewTitle.setVisibility(View.GONE);
                holder.title.setLinksClickable(false);
                holder.linkViewTitle.setLinksClickable(false);

                if (postItems.get(position).getPostDescription().equals("  ")){
                    holder.title.setVisibility(View.GONE);
                    holder.backgroundLayout.setVisibility(View.GONE);
                } else {
                    if (postItems.get(position).getPostDescription().length() < 150 && postItems.get(position).getPostThumbnail().equals("none")){
                        holder.title.setVisibility(View.GONE);
                        holder.post_image.setVisibility(View.GONE);
                        holder.backgroundLayout.setVisibility(View.VISIBLE);
                        holder.textBackground.setText(postItems.get(position).getPostDescription());
                    } else {
                        holder.backgroundLayout.setVisibility(View.GONE);
                        holder.title.setVisibility(View.VISIBLE);
                        holder.title.setText(postItems.get(position).getPostDescription());
                    }
                }
            }
        }


        holder.date.setText(getDateTime(postItems.get(position).getDate()));
        getAuthorInfo(postItems.get(position).getAuthorID(),holder.userName,holder.user_image);
        getLikesCount(postItems.get(position).getPostID(),holder.like,holder.likeLayout);
        getCommentsCount(postItems.get(position).getPostID(),holder.comment);
        isLikes(holder.tvLikeStatus,postItems.get(position).getPostID(),holder.likeStatus,uID);

        if (!postItems.get(position).getPostThumbnail().equals("none")){
//            holder.linkViewLayout.setVisibility(View.GONE);
            holder.post_image.setVisibility(View.VISIBLE);
            Picasso.get().load(postItems.get(position).getPostThumbnail()).into(holder.post_image);
        } else {
            holder.post_image.setVisibility(View.GONE);
        }

        //holder.item.setOnClickListener(view -> mContext.startActivity(new Intent(mContext, FullPostActivity.class).putExtra("Id",postItems.get(position).getPostID())));

        holder.item.setOnClickListener(v -> postItemClickListener.onItemClick(postItems.get(position),holder.post_image,holder.userName,holder.user_image));

        holder.btnLike.setOnClickListener(view -> {
            if (holder.tvLikeStatus.getText().equals("Star")){
                FirebaseDatabase.getInstance().getReference().child("Likes").child(postItems.get(position).getPostID()).child(uID).setValue(true);
                sendNotificationToAuthor(postItems.get(position).getPostID(),postItems.get(position).getAuthorID(),uID,"like","Liked your post");
            }
            else {
                FirebaseDatabase.getInstance().getReference().child("Likes").child(postItems.get(position).getPostID()).child(uID).removeValue();
            }
        });

        //holder.btnComment.setOnClickListener(view -> mContext.startActivity(new Intent(mContext, FullPostActivity.class).putExtra("Id",postItems.get(position).getPostID())));

        holder.btnComment.setOnClickListener(v -> postItemClickListener.onItemClick(postItems.get(position),holder.post_image,holder.userName,holder.user_image));

        holder.userName.setOnClickListener(view -> {
            if (postItems.get(position).getAuthorID().equals(uID)){
                mContext.startActivity(new Intent(mContext, ProfileActivity.class));
            }else {
                mContext.startActivity(new Intent(mContext, UserProfileActivity.class).putExtra("uID",postItems.get(position).getAuthorID()));
            }
        });

        holder.user_image.setOnClickListener(view -> {
            if (postItems.get(position).getAuthorID().equals(uID)){
                mContext.startActivity(new Intent(mContext, ProfileActivity.class));
            }else {
                mContext.startActivity(new Intent(mContext, UserProfileActivity.class).putExtra("uID",postItems.get(position).getAuthorID()));
            }
        });

        holder.btnShare.setOnClickListener(view -> {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, mContext.getResources().getString(R.string.app_name));
                String shareHint = "https://starnotesocial.com/shared_post/" + postItems.get(position).getPostID();
                i.putExtra(Intent.EXTRA_TEXT, shareHint);
                mContext.startActivity(Intent.createChooser(i, "Choose one"));
            } catch(Exception e) {
                //e.toString();
                Toast.makeText(mContext, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder{

        TextView userName,date,title,like,comment,tvLikeStatus,textBackground, linkViewTitle;
        CircleImageView user_image;
        ImageView post_image,likeStatus;
        RelativeLayout btnLike,btnComment,btnShare;
        LinearLayout item,likeLayout,backgroundLayout;

        PostViewHolder(@NonNull View itemView) {
            super(itemView);

            user_image = itemView.findViewById(R.id.user_image_post_item);
            date = itemView.findViewById(R.id.date_post_item);
            title = itemView.findViewById(R.id.post_title_post_item);
            userName = itemView.findViewById(R.id.user_name_post_item);
            post_image = itemView.findViewById(R.id.post_image_post_item);
            like = itemView.findViewById(R.id.tv_like_count_post_item);
            comment = itemView.findViewById(R.id.tv_comment_count_post_item);
            btnLike = itemView.findViewById(R.id.like_button_post_item);
            btnComment = itemView.findViewById(R.id.comment_button_post_item);
            btnShare = itemView.findViewById(R.id.share_button_post_item);
            item = itemView.findViewById(R.id.post_item);
            likeStatus = itemView.findViewById(R.id.like_pic_post_item);
            tvLikeStatus = itemView.findViewById(R.id.like_text_post_item);
            likeLayout = itemView.findViewById(R.id.like_count_layout_post_item);
            backgroundLayout = itemView.findViewById(R.id.background_layout_post_item);
            textBackground = itemView.findViewById(R.id.text_background_post_item);

            // SAZIB..
            linkViewTitle = itemView.findViewById(R.id.link_view_title);
        }
    }


    private void getAuthorInfo(final String uID, final TextView userName, final CircleImageView user_image){

        user_image.setImageResource(R.drawable.user_low);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users").child(uID).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                imageLink = "none";
                if (dataSnapshot.child("imageThumbnail").exists()){
                    imageLink = dataSnapshot.child("imageThumbnail").getValue(String.class);
                }
                String firstName,lastName;
                firstName = dataSnapshot.child("firstName").getValue(String.class);
                lastName = dataSnapshot.child("lastName").getValue(String.class);
                userName.setText(firstName+" "+lastName);
                if (!imageLink.equals("none")){
                    Picasso.get().load(imageLink).into(user_image);
                } else {
                    user_image.setImageResource(R.drawable.user_low);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getLikesCount(String postId,final TextView likes,LinearLayout likeLayout){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Likes").child(postId);
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount()<1){
                    likeLayout.setVisibility(View.GONE);
                } else {
                    likeLayout.setVisibility(View.VISIBLE);
                    likes.setText(""+dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getCommentsCount(String postID, final TextView comments){
        DatabaseReference commentReference = FirebaseDatabase.getInstance().getReference().child("Comments").child(postID);
        commentReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount()<1){
                    comments.setVisibility(View.GONE);
                } else {
                    comments.setVisibility(View.VISIBLE);
                    comments.setText(dataSnapshot.getChildrenCount()+" Comments");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private String getDateTime(long timeInMilli){
        long cDate = System.currentTimeMillis();
        String date = TimeAgo.toDuration(cDate-timeInMilli);
        return date;
    }

    private void isLikes(final TextView textView, String postId, final ImageView imageView, final String userID){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Likes").child(postId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.child(userID).exists()){
//                    imageView.setImageResource(R.drawable.ic_star_full_color);
//                    textView.setText("Star ");
//                    textView.setTextColor(mContext.getResources().getColor(R.color.colorMain));
//                } else {
//                    imageView.setImageResource(R.drawable.ic_star_outline);
//                    textView.setText("Star");
//                    textView.setTextColor(mContext.getResources().getColor(R.color.black));
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void sendNotificationToAuthor(String postId, String authorId,String userId,String type,String message){

        DatabaseReference notificationReference = FirebaseDatabase.getInstance().getReference("Notifications");
        DatabaseReference pushRef = notificationReference.child(authorId).push();
        pushRef.push();
        String notificationId = pushRef.getKey();
        long time = System.currentTimeMillis();

        NotificationInApp notification = new NotificationInApp(postId,type,userId,authorId,message,notificationId,time,false);
        pushRef.setValue(notification);
    }

    // SAZIB..
    public List<String> hasUrl(String postDescription) {
        List<String> links = new ArrayList<String>();


        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";

        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(postDescription);

        while (urlMatcher.find()) {
            links.add(postDescription.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }
        return links;

    }

    public void urlEncoder(String desc, String link, @NonNull final PostViewHolder holder) {
        UrlType urlType = new UrlType(link);
        // Retrofit API Call..
        Call<UrlType> call = RetrofitClient
                .getInstance()
                .getServerApi()
                .urlEncode(urlType);

        call.enqueue(new Callback<UrlType>() {
            @Override
            public void onResponse(Call<UrlType> call, Response<UrlType> response) {
                serverResponse = response.body();
                holder.post_image.setVisibility(View.VISIBLE);
                holder.linkViewTitle.setVisibility(View.VISIBLE);
                Picasso.get().load(serverResponse.getImage()).into(holder.post_image);
                holder.backgroundLayout.setVisibility(View.GONE);
                holder.title.setVisibility(View.VISIBLE);
                holder.title.setText(desc);
                holder.title.setTextColor(Color.parseColor("#F15A22"));
                holder.linkViewTitle.setText(serverResponse.getTitle());
                holder.title.setLinksClickable(true);
                holder.post_image.setOnClickListener(v -> {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(serverResponse.getLinkUrl())));
                });
                holder.title.setOnClickListener(v -> {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(serverResponse.getLinkUrl())));
                });
                holder.linkViewTitle.setOnClickListener(v -> {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(serverResponse.getLinkUrl())));
                });
                // holder.linkViewLayout.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<UrlType> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface PostItemClickListener {
        void onItemClick(PostItem postItem, ImageView postImage, TextView authorName, CircleImageView authorImage);
    }

}
