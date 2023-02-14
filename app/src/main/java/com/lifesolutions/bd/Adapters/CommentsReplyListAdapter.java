package com.lifesolutions.bd.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Helpers.TimeAgo;
import com.lifesolutions.bd.Models.Comments;
import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class CommentsReplyListAdapter extends RecyclerView.Adapter<CommentsReplyListAdapter.MyViewHolder>{
    private Context mContext;
    private ArrayList<Comments> comments;
    private String uID;

    public CommentsReplyListAdapter(Context mContext, ArrayList<Comments> comments) {
        this.mContext = mContext;
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentsReplyListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CommentsReplyListAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.comment_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentsReplyListAdapter.MyViewHolder myViewHolder, int i) {

        final int position;
        position = i;

        getUserInfo(myViewHolder.image,myViewHolder.name,comments.get(i).getCommenterId());
        myViewHolder.comment.setText(comments.get(position).getComment());
        myViewHolder.dateTime.setText(TimeAgo.toDuration(System.currentTimeMillis() - comments.get(position).getDateTime()));

        myViewHolder.item.setLongClickable(true);
        myViewHolder.item.setOnLongClickListener(v -> {
            SharedPreferences sharedPreferences = mContext.getSharedPreferences("UserInfo",MODE_PRIVATE);
            uID = sharedPreferences.getString("uID",null);

            if (uID.equals(comments.get(position).getCommenterId())) {
                PopupMenu popupMenu = new PopupMenu(mContext,myViewHolder.name);
                popupMenu.inflate(R.menu.menu_notice);
                popupMenu.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId())
                    {
                        case R.id.menu_edit:
                            final Dialog dialog = new Dialog(mContext);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialogue_with_edittext);
                            dialog.setCancelable(true);
                            RelativeLayout ok_btn = dialog.findViewById(R.id.dialogue_ok);
                            final EditText c_comment = dialog.findViewById(R.id.dialogue_editText);
                            c_comment.setText(comments.get(position).getComment());
                            ok_btn.setOnClickListener(v1 -> {
                                String cmnt = c_comment.getText().toString();
                                if(cmnt.isEmpty())
                                {
                                    c_comment.setError("This field cant,t be empty");
                                    c_comment.requestFocus();
                                }
                                else
                                {
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("CommentsReply");
                                    databaseReference.child(comments.get(position).getPostId()).child(comments.get(position).getCommentId())
                                            .child("comment").setValue(cmnt).addOnSuccessListener(aVoid -> {
                                                Toast.makeText(mContext, "Comment edited successfully", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(mContext, "Failed to edit comment", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });
                                }
                            });
                            dialog.show();
                            break;
                        case R.id.menu_delete:
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setTitle("Delete");
                            builder.setCancelable(false);
                            builder.setMessage("Are you sure to delete your comment ?");
                            builder.setPositiveButton("Yes", (dialog1, which) -> {

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("CommentsReply");
                                databaseReference.child(comments.get(position).getPostId()).child(comments.get(position).getCommentId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(mContext, "Comment deleted successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(mContext, "Failed to delete comment", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                            break;
                        default:
                            break;
                    }

                    return false;
                });

                popupMenu.show();
                return true;
            }
            return true;
        });

        myViewHolder.reply.setVisibility(View.GONE);
        myViewHolder.replyCount.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView name,comment,dateTime,reply,replyCount;
        private CircleImageView image;
        private LinearLayout item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.commenter_name);
            comment  = itemView.findViewById(R.id.comment_comment);
            image  = itemView.findViewById(R.id.commenter_image);
            dateTime = itemView.findViewById(R.id.comment_date_time);
            item = itemView.findViewById(R.id.comment_item);
            reply = itemView.findViewById(R.id.replay_comment);
            replyCount = itemView.findViewById(R.id.replay_count_comment);

        }
    }

    public static void getUserInfo(final ImageView imageView, final TextView userName, String userId) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                userName.setText(user.getFirstName()+" "+user.getLastName());
                Picasso.get().load(user.getImageThumbnail()).into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
