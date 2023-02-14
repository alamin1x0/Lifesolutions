package com.lifesolutions.bd.kotlinCode.ui.adapter

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Activities.ViewImageActivity
import com.lifesolutions.bd.Models.Message
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.utils.toast
import java.text.SimpleDateFormat
import java.util.*


const val MESSAGE_LEFT = 0
const val MESSAGE_RIGHT = 1

class ChatMessageAdapter(
    private val activity: Activity,
    private val context: Context,
    private val messages: ArrayList<Message>,
    private val receiverId: String
): RecyclerView.Adapter<ChatMessageAdapter.ViewHolderMessage>(){

    private val TAG = "ChatMessageAdapter"
    private var mediaPlayer = MediaPlayer()
    private val handler = Handler()
    private var seekBar: SeekBar? = null
    private var timer: TextView? = null
    // Shared Pref Current User data..
    private var userPreferences: SharedPreferences =  context.getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)!!

    private var db = FirebaseDatabase.getInstance()

    override fun getItemViewType(position: Int): Int {
        val currentUserID = userPreferences.getString("uID", null)

        return if (messages[position].senderId == currentUserID) {
            MESSAGE_RIGHT
        } else {
            MESSAGE_LEFT
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMessage {
        return if (viewType == MESSAGE_RIGHT) {
            ViewHolderMessage(
                LayoutInflater.from(context).inflate(R.layout.chat_item_right, parent, false)
            )
        } else {
            ViewHolderMessage(
                LayoutInflater.from(context).inflate(R.layout.chat_item_left, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ChatMessageAdapter.ViewHolderMessage, position: Int) {
        val currentUserID = userPreferences.getString("uID", null)
        val message = messages[position]

        if (messages[position].senderId == currentUserID) {
            if (message.isSeen) {
                holder.seenIcon.setImageResource(R.drawable.ic_double_check_black)
            } else {
                holder.seenIcon.setImageResource(R.drawable.ic_check_black_24dp)
            }
        }

        val sdf = SimpleDateFormat("dd MMMM yy hh:mm aa")
        // val sdf = SimpleDateFormat("dd/MM/yy")
        val netDate = Date(message.time as Long)
        val date =sdf.format(netDate)

       holder.messageTime.text = date

        when(messages[position].type) {
            "text" -> {
                holder.imageMessageLayout.visibility = View.GONE
                holder.fileMessageLayout.visibility = View.GONE
                holder.voiceMessageLayout.visibility = View.GONE
                holder.textMessageLayout.visibility = View.VISIBLE

                holder.showMessage.text = messages[position].message

                holder.textMessageLayout.setOnLongClickListener {
                    if (currentUserID == message.senderId) {
                        deleteMessage(holder.textMessageLayout, currentUserID!!, message, position)
                    }
                    true
                }
            }
            "image" -> {
                holder.textMessageLayout.visibility = View.GONE
                holder.fileMessageLayout.visibility = View.GONE
                holder.voiceMessageLayout.visibility = View.GONE
                holder.imageMessageLayout.visibility = View.VISIBLE


                Picasso.get().load(messages[position].message).into(holder.showImage)

                holder.imageMessageLayout.setOnClickListener {

                    Intent(context, ViewImageActivity::class.java).apply {
                        putExtra("imageUrl", messages[position].imageUrlHD)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        context.startActivity(this)
                    }
                }

                holder.imageMessageLayout.setOnLongClickListener {
                    if (currentUserID == message.senderId) {
                        deleteMessage(holder.textMessageLayout, currentUserID!!, message, position)
                    }
                    true
                }
            }
            "voice" -> {
                holder.imageMessageLayout.visibility = View.GONE
                holder.fileMessageLayout.visibility = View.GONE
                holder.textMessageLayout.visibility = View.GONE
                holder.voiceMessageLayout.visibility = View.VISIBLE

                holder.voiceItem.setOnClickListener {
                    val dialog = Dialog(activity)
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    dialog.setContentView(R.layout.dialog_voice_message_player)
                    dialog.setCancelable(false)
                    dialog.window?.attributes?.windowAnimations =
                        R.style.animation_dialog
                    val timer =
                        dialog.findViewById<TextView>(R.id.timer_music_player_dialog)
                    val duration =
                        dialog.findViewById<TextView>(R.id.duration_music_player_dialog)
                    val closeBtn =
                        dialog.findViewById<TextView>(R.id.close_button_music_player_dialog)
                    val seek =
                        dialog.findViewById<SeekBar>(R.id.seekbar_music_player_dialog)
                    val playPause =
                        dialog.findViewById<ImageButton>(R.id.play_pause_button_music_player_dialog)
                    seek.max = 99

                    this.timer = timer
                    seekBar = seek

                    prepareMediaPlayer(duration, messages[position].message, playPause, seek)

                    playPause.setOnClickListener {
                        if (mediaPlayer.isPlaying) {
                            handler.removeCallbacks(updater)
                            mediaPlayer.pause()
                            playPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp)
                        } else {
                            mediaPlayer.start()
                            playPause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp)
                            updateSeekBar(seek)
                        }
                    }

                    seek.setOnTouchListener { v, _ ->
                        val seekBar1 = v as SeekBar
                        val musicPosition = mediaPlayer.duration / 100 * seekBar1.progress
                        mediaPlayer.seekTo(musicPosition)
                        false
                    }


                    closeBtn.setOnClickListener {
                        if (mediaPlayer.isPlaying) {
                            mediaPlayer.stop()
                            mediaPlayer = MediaPlayer()
                        }
                        dialog.dismiss()
                    }

                    dialog.show()
                }

                holder.voiceItem.setOnLongClickListener {
                    if (currentUserID == message.senderId) {
                        deleteMessage(holder.textMessageLayout, currentUserID!!, message, position)
                    }
                    true
                }
            }
            "pdf" -> {
                holder.imageMessageLayout.visibility = View.GONE
                holder.textMessageLayout.visibility = View.GONE
                holder.voiceMessageLayout.visibility = View.GONE
                holder.fileMessageLayout.visibility = View.VISIBLE


                holder.file.setOnClickListener {
                    showDownloadDialog(position, "pdf")
                }

                holder.file.setOnLongClickListener {
                    if (currentUserID == message.senderId) {
                        deleteMessage(holder.textMessageLayout, currentUserID!!, message, position)
                    }
                    true
                }
            }
            "docx" -> {
                holder.imageMessageLayout.visibility = View.GONE
                holder.textMessageLayout.visibility = View.GONE
                holder.voiceMessageLayout.visibility = View.GONE
                holder.fileMessageLayout.visibility = View.VISIBLE

                holder.file.text = "DOCX File"
                holder.file.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_docs_file, 0, 0, 0)

                holder.file.setOnClickListener {
                    showDownloadDialog(position, "docx")
                }

                holder.file.setOnLongClickListener {
                    if (currentUserID == message.senderId) {
                        deleteMessage(holder.textMessageLayout, currentUserID!!, message, position)
                    }
                    true
                }
            }
        }
    }



    //Message View Holder...

    inner class ViewHolderMessage(view: View) : RecyclerView.ViewHolder(view) {
        val textMessageLayout: LinearLayout = view.findViewById(R.id.text_message_layout)
        val imageMessageLayout: LinearLayout = view.findViewById(R.id.image_message_layout)
        val fileMessageLayout: LinearLayout = view.findViewById(R.id.file_message_layout)
        val voiceMessageLayout: LinearLayout = view.findViewById(R.id.voice_message_layout)

        val showMessage: TextView = view.findViewById(R.id.show_message)


        val messageTime: TextView = view.findViewById(R.id.tv_message_time)

        val showImage: ImageView = view.findViewById(R.id.show_image)
        // val profileImage: CircleImageView = view.findViewById(R.id.profile_image_message)
        val file: TextView = view.findViewById(R.id.file_image)
        val voiceItem: ImageView = view.findViewById(R.id.voice_icon_message)

        val seenIcon: ImageView = view.findViewById(R.id.iv_seen_icon)

    }


    /**
     * Function
     */

    private fun deleteMessage(holderItem: View, currentUserID: String, message: Message, position: Int) {
        val popupMenu = PopupMenu(context, holderItem)
        popupMenu.inflate(R.menu.menu_chat)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_delete -> {
                    val ref = db.getReference("Messages").child(currentUserID).child(receiverId).child(message.messageId)
                    val refConversation = db.getReference("StarnoteConversation").child(currentUserID).child(receiverId)
                    ref.removeValue()

                    // Conversation Update
                    refConversation.child("lastMessage").setValue("You removed a message")
                }
                R.id.menu_delete_everyone -> {
                    val ref1 = db.getReference("Messages").child(currentUserID).child(receiverId).child(message.messageId)
                    val ref2 = db.getReference("Messages").child(receiverId).child(currentUserID).child(message.messageId)
                    val refConversation1 = db.getReference("StarnoteConversation").child(currentUserID).child(receiverId)
                    val refConversation2 = db.getReference("StarnoteConversation").child(receiverId).child(currentUserID)

                    ref1.removeValue()
                    ref2.removeValue()

                    // Conversation Update
                    refConversation1.child("lastMessage").setValue("A message removed")
                    refConversation2.child("lastMessage").setValue("A message removed")
                }

            }
            true
        }
        popupMenu.show()
    }

    private fun prepareMediaPlayer(
        duration: TextView,
        musicUrl: String,
        playPause: ImageButton,
        seek: SeekBar
    ) {
        try {
            mediaPlayer.setDataSource(musicUrl)
            mediaPlayer.prepare()
            duration.text = milliSecondsToTime(mediaPlayer.duration.toLong())
            mediaPlayer.start()
            playPause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp)
            updateSeekBar(seek)
        } catch (e: Exception) {
            Toast.makeText(context, "" + e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private val updater = Runnable {
        updateSeekBar(seekBar!!)
        val currentDuration = mediaPlayer.currentPosition.toLong()
        timer?.text = milliSecondsToTime(currentDuration)
    }

    private fun updateSeekBar(seek: SeekBar) {
        if (mediaPlayer.isPlaying) {
            seek.progress = ((mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration * 100).toInt())
            handler.postDelayed(updater, 1000)
        }
    }


    private fun milliSecondsToTime(milliseconds: Long): String? {
        var timerString = ""
        val secondString: String
        val hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = (milliseconds % (1000 * 60 * 60)).toInt() / (1000 * 60)
        val seconds = (milliseconds % (1000 * 60 * 60)).toInt() % (1000 * 60) / 1000
        if (hours > 0) {
            timerString = "$hours:"
        }
        secondString = if (seconds < 10) {
            "0$seconds"
        } else {
            "" + seconds
        }
        timerString = "$timerString$minutes:$secondString"
        return timerString
    }


    private fun showDownloadDialog(position: Int, type: String) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((activity), Manifest.permission.READ_PHONE_STATE)) {
                context.toast("Please go to app info and accept for required permission")
            } else {
                ActivityCompat.requestPermissions(
                    (activity),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1000
                )
            }
        } else {
            AlertDialog.Builder(activity)
                .setTitle("Are you sure to download ?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialogInterface, _ ->
                    startDownloading(messages[position].message, type)
                    dialogInterface.dismiss()
                }
                .setNegativeButton("No") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }.show()
        }
    }


    private fun startDownloading(url: String, type: String) {
        Toast.makeText(context, "Download Started", Toast.LENGTH_SHORT).show()
        val request = DownloadManager.Request(Uri.parse(url))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle("Download")
        request.setDescription("file downloading ... ")
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "" + System.currentTimeMillis() + "." + type
        )
        val manager =
            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }


}