package com.lifesolutions.bd.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tamir7.contacts.Contact;
import com.lifesolutions.bd.R;

import java.util.List;

public class ContactListAdapter extends  RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    Context context;
    List<Contact> contacts;
    List<String> contactsIDS;


    public ContactListAdapter(Context context, List<Contact> contacts, List<String> contactsIDS) {
        this.context = context;
        this.contacts = contacts;
        this.contactsIDS = contactsIDS;
    }


    public ContactListAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }




    @NonNull
    @Override
    public ContactListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.contact_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.ViewHolder holder, int position) {
        holder.m_sender.setText(contacts.get(position).getDisplayName());
        String no = contacts.get(position).getPhoneNumbers().get(0).getNormalizedNumber();
        holder.m_sender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "No "+no, Toast.LENGTH_SHORT).show();
            }
        });






        String message = "Hey what's up ? Join Starnote Social today to enjoy hd voice and video call : https://play.google.com/store/apps/details?id=com.stardigiinternational.starnotee&hl=en&gl=US  ";
        holder.send_invite.setImageResource(R.drawable.send_invite);
        holder.send_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + no));
                intent.putExtra("sms_body", message);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
//                String userId = contactsIDS.get(position);
//                Intent myIntent = new Intent(context, UserProfileActivityKt.class).putExtra("uID", userId);
//                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(myIntent);
            }
        });
      //  getData();


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView m_sender;
        ImageView send_invite;
        ImageView user_ic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            m_sender = itemView.findViewById(R.id.textView3);
            send_invite = itemView.findViewById(R.id.imageView7);
            user_ic = itemView.findViewById(R.id.imageView6);
            user_ic.setImageResource(R.drawable.contacts_logo);



        }
    }





}
