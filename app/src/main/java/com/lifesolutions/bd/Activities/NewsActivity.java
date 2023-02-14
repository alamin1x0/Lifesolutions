/**
package com.stardigiinternational.starnotee.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Client;
import com.algolia.search.saas.CompletionHandler;
import com.algolia.search.saas.Index;
import com.github.tamir7.contacts.Contact;
import com.github.tamir7.contacts.Contacts;
import com.github.tamir7.contacts.Query;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.GsonBuilder;
import com.stardigiinternational.starnotee.Adapters.ContactListAdapter;
import com.stardigiinternational.starnotee.Models.SearchUserModel;
import com.stardigiinternational.starnotee.Models.User;
import com.stardigiinternational.starnotee.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;

public class NewsActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    private static final int READ_CONTACT_PERMISSION_REQUEST_CODE = 76;
    List<JSONObject> array = new ArrayList<JSONObject>();
    Client client = new Client("PUXDD507IK", "180e3b3788e00ec70e7a892a48b139d0");
    Index index = client.getIndex("UserDatabase");



    DatabaseReference mReference = FirebaseDatabase.getInstance().getReference().child("Users");
    HashMap<String,String> nos = new HashMap<>();
    HashMap<String,String> ids = new HashMap<>();
    List<Contact> finalContacts = new ArrayList<>();
    List<String> finalContactsID = new ArrayList<>();
    ProgressBar progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);
        Contacts.initialize(this);

        toolbar = findViewById(R.id.toolbar_leader_board);
        setSupportActionBar(toolbar);
        setTitle("Meet Your Friends");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        progressBar = findViewById(R.id.simpleProgressBar);
        recyclerView = findViewById(R.id.test_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        getData();

        //checkPermission();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED) {
//            Query q = Contacts.getQuery();
//            q.hasPhoneNumber();
//            List<Contact> contacts = q.find();
            AlgoliaSearch();

//            for (int i = 0; i < contacts.size() ; i++) {
//                String no = contacts.get(i).getPhoneNumbers().get(0).getNormalizedNumber();
//                if(no!=null){
//                    Log.d("num",no);
//                    if(!no.startsWith("+88")){
//                        no = "+88"+no;
//                        if(syncContract(no)){
//                            finalContacts.add(contacts.get(i));
//                        }
//                    }else{
//                        if(syncContract(no)){
//                            finalContacts.add(contacts.get(i));
//                        }
//                    }
//                }
//
//            }
//
//
//
//            ContactListAdapter contactListAdapter = new ContactListAdapter(getApplicationContext(),finalContacts);
//            recyclerView.setAdapter(contactListAdapter);

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                        READ_CONTACT_PERMISSION_REQUEST_CODE);
            }
        }
    }

    private void doSync(List<Contact> contacts) {
        for (int i = 0; i < contacts.size() ; i++) {
            String no = contacts.get(i).getPhoneNumbers().get(0).getNormalizedNumber();
            if(no!=null){
                Log.d("num",no);
                if(!no.startsWith("+88")){
                    no = "+88"+no;
                    if(syncContract(no)){
                        finalContacts.add(contacts.get(i));
                        finalContactsID.add(ids.get(no));
                    }
                }else{
                    if(syncContract(no)){
                        finalContacts.add(contacts.get(i));
                        finalContactsID.add(ids.get(no));
                    }
                }
            }

        }

    }

    private boolean syncContract(String no) {
        if(nos.containsKey(no)){
           Log.d("num",no+" is found");
           return true;
        }else {
            Log.d("num",no+" is not found");
            return false;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == READ_CONTACT_PERMISSION_REQUEST_CODE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            AlgoliaSearch();
        }
    }





    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    void getData(){
        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    ArrayList<User> users = new ArrayList<>();
                    ArrayList<String> phoneNos = new ArrayList<>();
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                        User user = userSnapshot.getValue(User.class);
                        String no = "";
                        if(userSnapshot.child("phone").exists()){
                            no = userSnapshot.child("phone").getValue(String.class);
                            if(!no.startsWith("+88") && (no.length()!=0)){
                                no = "+88"+no;
                            }
                        }
                        if (no.length() != 0) {
                            try {
                                //  Log.d("Number",no);
                                array.add(
                                        new JSONObject().put("phoneNo", no).put("id",user.getuID())
                                );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d("name: " , String.valueOf(array.size()));
                        //  }
                    }
                //    for (int i = 0; i <2000; i++) {
                  //      Log.d("data", "adding for : "+i);
                        index.addObjectsAsync(new JSONArray(array), null);
                    //}

                      Toast.makeText(getApplicationContext(),"ADded Nos",Toast.LENGTH_LONG);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void AlgoliaSearch() {
        com.algolia.search.saas.Query query1 = new com.algolia.search.saas.Query("+88").setAttributesToRetrieve("phoneNo","id").setHitsPerPage(20000);
        index.searchAsync(query1, new CompletionHandler() {
            @Override
            public void requestCompleted(JSONObject content, AlgoliaException error) {
                try {
                    JSONArray hits = content.getJSONArray("hits");
                    for (int i = 0; i < hits.length(); i++) {
                        JSONObject jsonObject = hits.getJSONObject(i);
                        String phoneNo = jsonObject.getString("phoneNo");
                        String id = jsonObject.getString("id");
                        ids.put(phoneNo,id);
                        nos.put(phoneNo,phoneNo);
                        Log.d("num","No added: "+ nos.get(phoneNo));
                    }
                    Query q = Contacts.getQuery();
                    q.hasPhoneNumber();
                    List<Contact> contacts = q.find();
                    doSync(contacts);
                    progressBar.setVisibility(View.INVISIBLE);
                    ContactListAdapter contactListAdapter = new ContactListAdapter(getApplicationContext(),finalContacts,finalContactsID);
                    recyclerView.setAdapter(contactListAdapter);


                    Log.d("hitssss : ", String.valueOf(nos.size()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

 */