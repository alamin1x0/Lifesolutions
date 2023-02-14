package com.lifesolutions.bd.kotlinCode.ui.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001:B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ(\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020#H\u0016J\u0010\u0010%\u001a\u00020#2\u0006\u0010\"\u001a\u00020#H\u0016J\u0012\u0010&\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\'\u001a\u00020(H\u0002J\u001c\u0010)\u001a\u00020\u001d2\n\u0010*\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\"\u001a\u00020#H\u0017J\u001c\u0010+\u001a\u00060\u0002R\u00020\u00002\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020#H\u0016J(\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u00172\u0006\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0015H\u0002J\u0018\u00105\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\u0006\u00106\u001a\u00020\u000bH\u0002J\u0018\u00107\u001a\u00020\u001d2\u0006\u00108\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u000bH\u0002J\u0010\u00109\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u0015H\u0002R\u000e\u0010\r\u001a\u00020\u000bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/adapter/GroupMessageAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/GroupMessageAdapter$ViewHolderGroupMessage;", "activity", "Landroid/app/Activity;", "context", "Landroid/content/Context;", "messages", "Ljava/util/ArrayList;", "Lcom/lifesolutions/bd/kotlinCode/data/model/GroupMessage;", "groupId", "", "(Landroid/app/Activity;Landroid/content/Context;Ljava/util/ArrayList;Ljava/lang/String;)V", "TAG", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "handler", "Landroid/os/Handler;", "mediaPlayer", "Landroid/media/MediaPlayer;", "seekBar", "Landroid/widget/SeekBar;", "timer", "Landroid/widget/TextView;", "updater", "Ljava/lang/Runnable;", "userPreferences", "Landroid/content/SharedPreferences;", "deleteMessage", "", "holderItem", "Landroid/view/View;", "currentUserID", "message", "position", "", "getItemCount", "getItemViewType", "milliSecondsToTime", "milliseconds", "", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "prepareMediaPlayer", "duration", "musicUrl", "playPause", "Landroid/widget/ImageButton;", "seek", "showDownloadDialog", "type", "startDownloading", "url", "updateSeekBar", "ViewHolderGroupMessage", "app_debug"})
public final class GroupMessageAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.lifesolutions.bd.kotlinCode.ui.adapter.GroupMessageAdapter.ViewHolderGroupMessage> {
    private final android.app.Activity activity = null;
    private final android.content.Context context = null;
    private final java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.GroupMessage> messages = null;
    private final java.lang.String groupId = null;
    private final java.lang.String TAG = "ChatMessageAdapter";
    private android.media.MediaPlayer mediaPlayer;
    private final android.os.Handler handler = null;
    private android.widget.SeekBar seekBar;
    private android.widget.TextView timer;
    private android.content.SharedPreferences userPreferences;
    private com.google.firebase.database.FirebaseDatabase db;
    private final java.lang.Runnable updater = null;
    
    public GroupMessageAdapter(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.GroupMessage> messages, @org.jetbrains.annotations.NotNull()
    java.lang.String groupId) {
        super();
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.lifesolutions.bd.kotlinCode.ui.adapter.GroupMessageAdapter.ViewHolderGroupMessage onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.lifesolutions.bd.kotlinCode.ui.adapter.GroupMessageAdapter.ViewHolderGroupMessage holder, int position) {
    }
    
    /**
     * Function
     */
    private final void deleteMessage(android.view.View holderItem, java.lang.String currentUserID, com.lifesolutions.bd.kotlinCode.data.model.GroupMessage message, int position) {
    }
    
    private final void prepareMediaPlayer(android.widget.TextView duration, java.lang.String musicUrl, android.widget.ImageButton playPause, android.widget.SeekBar seek) {
    }
    
    private final void updateSeekBar(android.widget.SeekBar seek) {
    }
    
    private final java.lang.String milliSecondsToTime(long milliseconds) {
        return null;
    }
    
    private final void showDownloadDialog(int position, java.lang.String type) {
    }
    
    private final void startDownloading(java.lang.String url, java.lang.String type) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\fR\u0011\u0010\u0017\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0011\u0010\u0019\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0011\u0010\u001b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\bR\u0011\u0010\u001d\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\fR\u0011\u0010\u001f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\bR\u0011\u0010!\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0012R\u0011\u0010#\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\f\u00a8\u0006%"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/adapter/GroupMessageAdapter$ViewHolderGroupMessage;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/lifesolutions/bd/kotlinCode/ui/adapter/GroupMessageAdapter;Landroid/view/View;)V", "file", "Landroid/widget/TextView;", "getFile", "()Landroid/widget/TextView;", "fileMessageLayout", "Landroid/widget/LinearLayout;", "getFileMessageLayout", "()Landroid/widget/LinearLayout;", "imageMessageLayout", "getImageMessageLayout", "seenIcon", "Landroid/widget/ImageView;", "getSeenIcon", "()Landroid/widget/ImageView;", "senderImage", "getSenderImage", "senderLayout", "getSenderLayout", "senderName", "getSenderName", "showImage", "getShowImage", "showMessage", "getShowMessage", "textMessageLayout", "getTextMessageLayout", "tvMessageTime", "getTvMessageTime", "voiceItem", "getVoiceItem", "voiceMessageLayout", "getVoiceMessageLayout", "app_debug"})
    public final class ViewHolderGroupMessage extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout textMessageLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout imageMessageLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout fileMessageLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout voiceMessageLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView showMessage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView senderName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView senderImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvMessageTime = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView showImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView file = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView voiceItem = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView seenIcon = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout senderLayout = null;
        
        public ViewHolderGroupMessage(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getTextMessageLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getImageMessageLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getFileMessageLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getVoiceMessageLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getShowMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getSenderName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getSenderImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvMessageTime() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getShowImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getFile() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getVoiceItem() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getSeenIcon() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getSenderLayout() {
            return null;
        }
    }
}