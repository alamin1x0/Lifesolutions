package com.lifesolutions.bd.Adapters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Activities.ViewImageActivity;
import com.lifesolutions.bd.Models.Message;
import com.lifesolutions.bd.R;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private static final int MESSAGE_LEFT = 0;
    private static final int MESSAGE_RIGHT = 1;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Handler handler = new Handler();
    private Context mContext;
    private String uID;
    private ArrayList<Message> messages;
    private SeekBar seekBar;
    private TextView timer;
    private String imageUrl;


    public MessageAdapter(Context mContext, ArrayList<Message> messages) {
        this.mContext = mContext;
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MESSAGE_RIGHT){
            return new MessageAdapter.MessageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.chat_item_right,parent,false));
        } else {
            return new MessageAdapter.MessageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.chat_item_left,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageAdapter.MessageViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        if(messages.get(position).getType().equals("text")){
            holder.showImage.setVisibility(View.GONE);
            holder.file.setVisibility(View.GONE);
            holder.voiceItem.setVisibility(View.GONE);
            holder.showMessage.setVisibility(View.VISIBLE);
            holder.showMessage.setText(messages.get(position).getMessage());
            holder.showMessage.setOnLongClickListener(view -> {
                if (uID.equals(messages.get(position).getSenderId())) {
                    PopupMenu popupMenu = new PopupMenu(mContext,holder.showMessage);
                    popupMenu.inflate(R.menu.menu_chat);
                    popupMenu.setOnMenuItemClickListener(item -> {
                        switch (item.getItemId())
                        {
                            case R.id.menu_delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setTitle("Delete");
                                builder.setCancelable(false);
                                builder.setMessage("Are you sure to delete your message ?");
                                builder.setPositiveButton("Yes", (dialog, which) -> {

                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
                                    databaseReference.child(messages.get(position).getMessageId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(mContext, "Message deleted successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(e -> Toast.makeText(mContext, "Failed to delete message", Toast.LENGTH_SHORT).show());
                                }).setNegativeButton("No", (dialog, which) -> dialog.dismiss());
                                builder.show();
                                break;
                            default:
                                break;
                        }

                        return true;
                    });

                    popupMenu.show();
                    return true;
                }
                return true;
            });
        } else if (messages.get(position).getType().equals("image")){
            holder.showMessage.setVisibility(View.GONE);
            holder.file.setVisibility(View.GONE);
            holder.voiceItem.setVisibility(View.GONE);
            holder.showImage.setVisibility(View.VISIBLE);
            Picasso.get().load(messages.get(position).getMessage()).into(holder.showImage);
            holder.showImage.setOnClickListener(view -> {
                mContext.startActivity(new Intent(mContext, ViewImageActivity.class).putExtra("imageUrl",messages.get(position).getImageUrlHD()));
            });

            holder.showImage.setOnLongClickListener(view -> {
                if (uID.equals(messages.get(position).getSenderId()))
                {
                    PopupMenu popupMenu = new PopupMenu(mContext,holder.showImage);
                    popupMenu.inflate(R.menu.menu_chat);
                    popupMenu.setOnMenuItemClickListener(item -> {
                        switch (item.getItemId()) {
                            case R.id.menu_delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setTitle("Delete");
                                builder.setCancelable(false);
                                builder.setMessage("Are you sure to delete your message ?");
                                builder.setPositiveButton("Yes", (dialog, which) -> {

                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
                                    databaseReference.child(messages.get(position).getMessageId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(mContext, "Message deleted successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(e -> Toast.makeText(mContext, "Failed to delete message", Toast.LENGTH_SHORT).show());
                                }).setNegativeButton("No", (dialog, which) -> dialog.dismiss());
                                builder.show();
                                break;
                            default:
                                break;
                        }

                        return true;
                    });

                    popupMenu.show();
                    return true;
                }
                return true;
            });

        } else if (messages.get(position).getType().equals("pdf")){

            holder.showMessage.setVisibility(View.GONE);
            holder.showImage.setVisibility(View.GONE);
            holder.voiceItem.setVisibility(View.GONE);
            holder.file.setText("PDF File");
            holder.file.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pdf_file, 0, 0, 0);
            holder.file.setVisibility(View.VISIBLE);
            holder.file.setOnClickListener(view -> {
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext, Manifest.permission.READ_PHONE_STATE)) {
                        Toast.makeText(mContext,"Please go to app info and accept for required permission",Toast.LENGTH_SHORT).show();
                    } else {
                        ActivityCompat.requestPermissions((Activity) mContext,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
                    }

                } else {
                    new AlertDialog.Builder(Objects.requireNonNull(mContext))
                            .setTitle("Are you sure to download ?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startDownloading(messages.get(position).getMessage(),"pdf");
                                    dialogInterface.dismiss();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                }





            });

            holder.file.setOnLongClickListener(view -> {
                if (uID.equals(messages.get(position).getSenderId()))
                {
                    PopupMenu popupMenu = new PopupMenu(mContext,holder.file);
                    popupMenu.inflate(R.menu.menu_chat);
                    popupMenu.setOnMenuItemClickListener(item -> {
                        switch (item.getItemId())
                        {
                            case R.id.menu_delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setTitle("Delete");
                                builder.setCancelable(false);
                                builder.setMessage("Are you sure to delete your message ?");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
                                        databaseReference.child(messages.get(position).getMessageId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(mContext, "Message deleted successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(mContext, "Failed to delete message", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
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

                        return true;
                    });

                    popupMenu.show();
                    return true;
                }
                return true;
            });

        }else if (messages.get(position).getType().equals("docx")){

            holder.showMessage.setVisibility(View.GONE);
            holder.showImage.setVisibility(View.GONE);
            holder.voiceItem.setVisibility(View.GONE);
            holder.file.setVisibility(View.VISIBLE);
            holder.file.setText("DOCX File");
            holder.file.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_docs_file, 0, 0, 0);

            holder.file.setOnClickListener(view -> {
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext, Manifest.permission.READ_PHONE_STATE)) {
                        Toast.makeText(mContext,"Please go to app info and accept for required permission",Toast.LENGTH_SHORT).show();
                    } else {

                        ActivityCompat.requestPermissions((Activity) mContext,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
                    }

                } else {
                    new AlertDialog.Builder(Objects.requireNonNull(mContext))
                            .setTitle("Are you sure to download ?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", (dialogInterface, i) -> {
                                startDownloading(messages.get(position).getMessage(),"docx");
                                dialogInterface.dismiss();
                            })
                            .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss()).show();
                }
            });

            holder.file.setOnLongClickListener(view -> {
                if (uID.equals(messages.get(position).getSenderId()))
                {
                    PopupMenu popupMenu = new PopupMenu(mContext,holder.file);
                    popupMenu.inflate(R.menu.menu_chat);
                    popupMenu.setOnMenuItemClickListener(item -> {
                        switch (item.getItemId())
                        {
                            case R.id.menu_delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setTitle("Delete");
                                builder.setCancelable(false);
                                builder.setMessage("Are you sure to delete your message ?");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
                                        databaseReference.child(messages.get(position).getMessageId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(mContext, "Message deleted successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(mContext, "Failed to delete message", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
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

                        return true;
                    });

                    popupMenu.show();
                    return true;
                }
                return true;
            });
        }else if (messages.get(position).getType().equals("voice")){

            holder.showMessage.setVisibility(View.GONE);
            holder.showImage.setVisibility(View.GONE);
            holder.file.setVisibility(View.GONE);
            holder.voiceItem.setVisibility(View.VISIBLE);
            holder.file.setText("Voice Message");
            holder.file.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_speaker_new, 0, 0, 0);

            holder.voiceItem.setOnClickListener(view -> {

                final Dialog dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_voice_message_player);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
                TextView timer = dialog.findViewById(R.id.timer_music_player_dialog);
                TextView duration = dialog.findViewById(R.id.duration_music_player_dialog);
                TextView closeBtn = dialog.findViewById(R.id.close_button_music_player_dialog);
                SeekBar seekBar = dialog.findViewById(R.id.seekbar_music_player_dialog);
                ImageButton playPause = dialog.findViewById(R.id.play_pause_button_music_player_dialog);
                seekBar.setMax(99);

                this.timer = timer;
                this.seekBar = seekBar;

                prepareMediaPlayer(duration,messages.get(position).getMessage(),playPause,seekBar);

                playPause.setOnClickListener(view1 -> {

                    if (mediaPlayer.isPlaying()){

                        handler.removeCallbacks(updater);
                        mediaPlayer.pause();
                        playPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);

                    } else {
                        mediaPlayer.start();
                        playPause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                        updateSeekBar(seekBar);
                    }
                });

                seekBar.setOnTouchListener((view12, motionEvent) -> {
                    SeekBar seekBar1 = (SeekBar) view12;
                    int musicPosition  = (mediaPlayer.getDuration()/100)*seekBar1.getProgress();
                    mediaPlayer.seekTo(musicPosition);
                    return false;
                });


                closeBtn.setOnClickListener(view1 -> {
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                        mediaPlayer = new MediaPlayer();
                    }

                    dialog.dismiss();
                });

                dialog.show();
            });

            holder.voiceItem.setOnLongClickListener(view -> {
                if (uID.equals(messages.get(position).getSenderId()))
                {
                    PopupMenu popupMenu = new PopupMenu(mContext,holder.file);
                    popupMenu.inflate(R.menu.menu_chat);
                    popupMenu.setOnMenuItemClickListener(item -> {
                        switch (item.getItemId())
                        {
                            case R.id.menu_delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setTitle("Delete");
                                builder.setCancelable(false);
                                builder.setMessage("Are you sure to delete your message ?");
                                builder.setPositiveButton("Yes", (dialog, which) -> {

                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
                                    databaseReference.child(messages.get(position).getMessageId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(mContext, "Message deleted successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(mContext, "Failed to delete message", Toast.LENGTH_SHORT).show();
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

                        return true;
                    });

                    popupMenu.show();
                    return true;
                }
                return true;
            });
        }

        loadSenderImage(holder.profileImage,messages.get(position).getSenderId());

    }

    private void loadSenderImage(CircleImageView profileImage, String senderId) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(senderId);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageUrl = dataSnapshot.child("imageThumbnail").getValue(String.class);
                if (imageUrl.equals("none")){
                    profileImage.setImageResource(R.drawable.user_low);
                } else {
                    Picasso.get().load(imageUrl).into(profileImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{

        TextView showMessage,file;
        ImageView showImage,voiceItem;
        CircleImageView profileImage;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

           showMessage = itemView.findViewById(R.id.show_message);
           showImage = itemView.findViewById(R.id.show_image);
           // profileImage = itemView.findViewById(R.id.profile_image_message);
           file = itemView.findViewById(R.id.file_image);
           voiceItem = itemView.findViewById(R.id.voice_icon_message);
        }
    }


    @Override
    public int getItemViewType(int position) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("UserInfo",MODE_PRIVATE);
        uID = sharedPreferences.getString("uID",null);
        if (messages.get(position).getSenderId().equals(uID)){
            return MESSAGE_RIGHT;
        } else {
            return MESSAGE_LEFT;
        }
    }

    private void startDownloading(String url, String type) {

        Toast.makeText(mContext, "Download Started", Toast.LENGTH_SHORT).show();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("file downloading ... ");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis()+"."+type);

        DownloadManager manager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    private void prepareMediaPlayer(TextView duration, String musicUrl,ImageButton playPause,SeekBar seekBar){

        try {

            mediaPlayer.setDataSource(musicUrl);
            mediaPlayer.prepare();
            duration.setText(milliSecondsToTime(mediaPlayer.getDuration()));
            mediaPlayer.start();
            playPause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
            updateSeekBar(seekBar);

        }catch (Exception e){
            Toast.makeText(mContext, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private Runnable updater  = new Runnable() {
        @Override
        public void run() {
            updateSeekBar(seekBar);
            long currentDuration = mediaPlayer.getCurrentPosition();
            timer.setText(milliSecondsToTime(currentDuration));
        }
    };

    private void updateSeekBar(SeekBar seekBar){
        if (mediaPlayer.isPlaying()){
            seekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }

    private String milliSecondsToTime(long milliseconds){
        String timerString = "",secondString;

        int hours = (int) (milliseconds / (1000 * 60 *60));
        int minutes  = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds  = (int) (milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000;


        if (hours > 0){
            timerString = hours + ":";
        }

        if (seconds < 10){
            secondString = "0" + seconds;
        } else {
            secondString = "" + seconds;
        }

        timerString = timerString + minutes + ":" + secondString;

        return timerString;
    }

}
