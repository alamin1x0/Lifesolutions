//package com.stardigiinternational.starnotee.Adapters;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupMenu;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;
//import com.stardigiinternational.starnotee.Activities.AddPostActivity;
//import com.stardigiinternational.starnotee.Activities.FullPostActivity;
//import com.stardigiinternational.starnotee.Api.RetrofitClient;
//import com.stardigiinternational.starnotee.Api.UrlType;
//import com.stardigiinternational.starnotee.Helpers.TimeAgo;
//import com.stardigiinternational.starnotee.Models.PostItem;
//import com.stardigiinternational.starnotee.R;
//import com.stardigiinternational.starnotee.Test.TestActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MyPostListAdapter extends RecyclerView.Adapter<MyPostListAdapter.MyPostViewHolder>{
//    private Context mContext;
//    private ArrayList<PostItem> postItems;
//    private String name,imageLink;
//    private DatabaseReference databaseReference;
//    private String uID;
//    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
//
//    private UrlType responseBody;
//
//
//
//    public MyPostListAdapter(Context mContext) {
//        this.postItems = new ArrayList<>();
//        this.mContext = mContext;
//    }
//
//    public void addAllPosts(ArrayList<PostItem> newPosts){
//        int initSize = postItems.size();
//        postItems.addAll(newPosts);
//        notifyItemRangeChanged(initSize,newPosts.size());
//    }
//
//    public void removeAllItem(){
//        postItems.clear();
//    }
//
//    @NonNull
//    @Override
//    public MyPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new MyPostViewHolder(LayoutInflater.from(mContext).inflate(R.layout.post_item_layout,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final MyPostViewHolder holder, final int position) {
//
//        uID = mAuth.getUid();
//
//        List<String> links = hasUrl(postItems.get(position).getPostDescription());
//
//        if (links.size() > 0) {
//            getUrlResponse(
//                    postItems.get(position).getPostDescription(),
//                    links.get(0),
//                    holder
//            );
//        }
//
//        if (postItems.get(position).getPostDescription().equals("  ")){
//            holder.title.setVisibility(View.GONE);
//            holder.backgroundLayout.setVisibility(View.GONE);
//        } else {
//            if (postItems.get(position).getPostDescription().length() < 150 && postItems.get(position).getPostThumbnail().equals("none")){
//                holder.title.setVisibility(View.GONE);
//                holder.post_image.setVisibility(View.GONE);
//                holder.backgroundLayout.setVisibility(View.VISIBLE);
//                holder.textBackground.setText(postItems.get(position).getPostDescription());
//            } else {
//                holder.backgroundLayout.setVisibility(View.GONE);
//                holder.title.setVisibility(View.VISIBLE);
//                holder.title.setText(postItems.get(position).getPostDescription());
//            }
//        }
//        holder.date.setText(getDateTime(postItems.get(position).getDate()));
//        getAuthorInfo(postItems.get(position).getAuthorID(),holder.userName,holder.user_image);
//        getLikesCount(postItems.get(position).getPostID(),holder.like);
//        isLikes(holder.tvLikeStatus,postItems.get(position).getPostID(),holder.likeStatus,uID);
//        getCommentsCount(postItems.get(position).getPostID(),holder.comment);
//        if (postItems.get(position).getPostThumbnail().equals("none")){
//            holder.post_image.setVisibility(View.GONE);
//        } else {
//            holder.post_image.setVisibility(View.VISIBLE);
//            Picasso.get().load(postItems.get(position).getPostThumbnail()).into(holder.post_image);
//        }
//
//        holder.item.setOnClickListener(view -> mContext.startActivity(new Intent(mContext, FullPostActivity.class).putExtra("Id",postItems.get(position).getPostID())));
//
//        holder.btnLike.setOnClickListener(view -> {
//            if (holder.tvLikeStatus.getText().equals("Star")){
//                FirebaseDatabase.getInstance().getReference().child("Likes").child(postItems.get(position).getPostID()).child(uID).setValue(true);
//            }
//            else {
//                FirebaseDatabase.getInstance().getReference().child("Likes").child(postItems.get(position).getPostID()).child(uID).removeValue();
//            }
//        });
//
//        holder.item.setClickable(true);
//        holder.item.setOnLongClickListener(view -> {
//            PopupMenu popupMenu = new PopupMenu(mContext,holder.userName);
//            popupMenu.inflate(R.menu.menu_notice);
//            popupMenu.setOnMenuItemClickListener(item -> {
//                switch (item.getItemId())
//                {
//                    case R.id.menu_edit:
//                        mContext.startActivity(new Intent(mContext, AddPostActivity.class)
//                        .putExtra("postId",postItems.get(position).getPostID())
//                        .putExtra("code",1));
//                        break;
//                    case R.id.menu_delete:
//                        mContext.startActivity(new Intent(mContext, AddPostActivity.class)
//                                .putExtra("postId",postItems.get(position).getPostID())
//                                .putExtra("code",2));
//                        break;
//                }
//
//                return false;
//            });
//
//            popupMenu.show();
//            return true;
//        });
//
//        holder.btnShare.setOnClickListener(view -> {
//            try {
//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("text/plain");
//                i.putExtra(Intent.EXTRA_SUBJECT, mContext.getResources().getString(R.string.app_name));
//                String shareHint = postItems.get(position).getPostDescription()+"\n for more info download :  "+mContext.getResources().getString(R.string.app_name)+" Application\n\n"+"https://play.google.com/store/apps/details?id=" + mContext.getPackageName();
//                i.putExtra(Intent.EXTRA_TEXT, shareHint);
//                mContext.startActivity(Intent.createChooser(i, "Choose one"));
//            } catch(Exception e) {
//                //e.toString();
//                Toast.makeText(mContext, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return postItems.size();
//    }
//
//    public class MyPostViewHolder extends RecyclerView.ViewHolder{
//
//        TextView userName,date,title,like,comment,tvLikeStatus,textBackground, linkTitle;
//        CircleImageView user_image;
//        ImageView post_image,likeStatus;
//        RelativeLayout btnLike,btnComment,btnShare;
//        LinearLayout item,backgroundLayout, linkPreview;
//
//        public MyPostViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            user_image = itemView.findViewById(R.id.user_image_post_item);
//            date = itemView.findViewById(R.id.date_post_item);
//            title = itemView.findViewById(R.id.post_title_post_item);
//            userName = itemView.findViewById(R.id.user_name_post_item);
//            post_image = itemView.findViewById(R.id.post_image_post_item);
//            like = itemView.findViewById(R.id.tv_like_count_post_item);
//            comment = itemView.findViewById(R.id.tv_comment_count_post_item);
//            btnLike = itemView.findViewById(R.id.like_button_post_item);
//            btnComment = itemView.findViewById(R.id.comment_button_post_item);
//            btnShare = itemView.findViewById(R.id.share_button_post_item);
//            item = itemView.findViewById(R.id.post_item);
//            likeStatus = itemView.findViewById(R.id.like_pic_post_item);
//            tvLikeStatus = itemView.findViewById(R.id.like_text_post_item);
//            backgroundLayout = itemView.findViewById(R.id.background_layout_post_item);
//            textBackground = itemView.findViewById(R.id.text_background_post_item);
//
//
//        }
//    }
//
//    private void getAuthorInfo(final String uID, final TextView userName, final CircleImageView user_image){
//
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                imageLink = "none";
//                if (dataSnapshot.child(uID).child("imageThumbnail").exists()){
//                    imageLink = dataSnapshot.child(uID).child("imageThumbnail").getValue(String.class);
//                }
//                String firstName,lastName;
//                firstName = dataSnapshot.child(uID).child("firstName").getValue(String.class);
//                lastName = dataSnapshot.child(uID).child("lastName").getValue(String.class);
//                userName.setText(firstName+" "+lastName);
//                if (!imageLink.equals("none")){
//                    Picasso.get().load(imageLink).into(user_image);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void getLikesCount(String postId,final TextView likes){
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Likes").child(postId);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                likes.setText(""+dataSnapshot.getChildrenCount());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }
//
//    private void getCommentsCount(String postID, final TextView comments){
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Comments").child(postID);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                comments.setText(dataSnapshot.getChildrenCount()+" Comments");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private String getDateTime(long timeInMilli){
//        long cDate = System.currentTimeMillis();
//        String date = TimeAgo.toDuration(cDate-timeInMilli);
//        return date;
//    }
//
//    private void isLikes(final TextView textView, String postId, final ImageView imageView, final String userID){
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Likes").child(postId);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.child(userID).exists()){
//                    imageView.setImageResource(R.drawable.ic_star_black_24dp);
//                    textView.setText("Star ");
//                    textView.setTextColor(mContext.getResources().getColor(R.color.colorMain));
//                } else {
//                    imageView.setImageResource(R.drawable.ic_star_border_black_24dp);
//                    textView.setText("Star");
//                    textView.setTextColor(mContext.getResources().getColor(R.color.black));
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//    public void getUrlResponse(String postDescription, String url, @NonNull final MyPostViewHolder holder) {
//        UrlType urlType = new UrlType(url);
//
//
//        // Retrofit API Call..
//        Call<UrlType> call = RetrofitClient
//                .getInstance()
//                .getServerApi()
//                .urlEncode(urlType);
//
//        call.enqueue(new Callback<UrlType>() {
//            @Override
//            public void onResponse(Call<UrlType> call, Response<UrlType> response) {
//                UrlType serverResponse = response.body();
//                responseBody = serverResponse;
//                holder.linkPreview.setVisibility(View.VISIBLE);
//                holder.linkTitle.setText(responseBody.getTitle());
//
//            }
//
//            @Override
//            public void onFailure(Call<UrlType> call, Throwable t) {
//                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    public List<String> hasUrl(String postDescription) {
//        List<String> links = new ArrayList<String>();
//
//
//        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
//
//        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
//        Matcher urlMatcher = pattern.matcher(postDescription);
//
//        while (urlMatcher.find()) {
//            links.add(postDescription.substring(urlMatcher.start(0),
//                    urlMatcher.end(0)));
//        }
//        return links;
//
//    }
//}
