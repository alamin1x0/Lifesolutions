package com.lifesolutions.bd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Client;
import com.algolia.search.saas.CompletionHandler;
import com.algolia.search.saas.Index;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lifesolutions.bd.Adapters.FindFriendListAdapter;
import com.lifesolutions.bd.Models.SearchUserModel;
import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.ui.adapter.NewSearchAdapter;
import com.lifesolutions.bd.kotlinCode.ui.auth.LoginKtActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FindFriendsActivity extends AppCompatActivity {

    final int ITEM_LOAD_COUNT = 15;
    int total_item = 0,last_visible_item;
    private boolean isLoading = false;
    LinearLayoutManager mLayoutManager;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String uID;
    RecyclerView mRecyclerView;
    ProgressBar progressBar;
    Toolbar toolbar;
    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FindFriendListAdapter mAdapter;
    NewSearchAdapter searchAdapter;
    ImageButton searchButton;
    private int pageCount= 1;
    Client client;
    Index index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        toolbar = findViewById(R.id.toolbar_find_friends);
        mRecyclerView = findViewById(R.id.recyclerView_find_friends);
        progressBar = findViewById(R.id.progressbar_find_friends);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_find_friends);
        // searchButton = findViewById(R.id.search_button_search_users);
        setSupportActionBar(toolbar);
        setTitle("Find Friends");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth != null){
            uID = mAuth.getUid();
        } else {
            Intent intent = new Intent(FindFriendsActivity.this, LoginKtActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            FindFriendsActivity.this.finish();
        }


        client = new Client("PUXDD507IK", "180e3b3788e00ec70e7a892a48b139d0");
        index = client.getIndex("UserDatabase");

//        JSONObject settings = null;
//        try {
//            settings = new JSONObject()
//                    .put("searchableAttributes", "email");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        index.setSettingsAsync(settings, null);



        mReference = FirebaseDatabase.getInstance().getReference().child("Users");

        mSwipeRefreshLayout.setEnabled(false);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        searchAdapter = new NewSearchAdapter(this);
        mAdapter = new FindFriendListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        getUsers();

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                total_item = mLayoutManager.getItemCount();
                last_visible_item = mLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && total_item <= (last_visible_item + ITEM_LOAD_COUNT)){
                    pageCount++;
                    getUsers();
                    isLoading  = true;
                    progressBar.setVisibility(View.VISIBLE);

                }
            }
        });

        //  searchButton.setOnClickListener(view -> startActivity(new Intent(FindFriendsActivity.this,SearchUsersActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_friends,menu);
        MenuItem item = menu.findItem(R.id.action_Search2);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //  fireSearch(query);
                AlgoliaSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //  fireSearch(newText);

                AlgoliaSearch(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void AlgoliaSearch(String query) {
        //Toast.makeText(getApplicationContext(),"productName gjhgjhgjhgjhjh",Toast.LENGTH_SHORT).show();
        ArrayList<SearchUserModel> searchUserModels = new ArrayList<>();
        com.algolia.search.saas.Query query1 = new com.algolia.search.saas.Query(query).setAttributesToRetrieve("searchName","id").setHitsPerPage(50);
        index.searchAsync(query1, new CompletionHandler() {
            @Override
            public void requestCompleted(JSONObject content, AlgoliaException error) {
                try {
                    JSONArray hits = content.getJSONArray("hits");
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < hits.length(); i++) {
                        JSONObject jsonObject = hits.getJSONObject(i);
                        String productName = jsonObject.getString("searchName");
                        String id = jsonObject.getString("id");
                        SearchUserModel searchUserModel = new SearchUserModel(productName,id);
                        searchUserModels.add(searchUserModel);
                        Log.d("hitssss : ",productName);
                       // Toast.makeText(getApplicationContext(),productName,Toast.LENGTH_SHORT).show();
                        list.add(productName);
                    }
                    Log.d("hitssss : ", String.valueOf(list.size()));
                    searchAdapter.removeAllItem();
                    searchAdapter.addAllUsers(searchUserModels);
                    searchAdapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(searchAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


//    private void fireSearch(String search){
//        Query fir = mReference.orderByChild("searchName").startAt(search).endAt(search + "\uf8ff");
//        //  Toast.makeText(getApplicationContext(),"-----------------------------",Toast.LENGTH_SHORT).show();
//        FirebaseRecyclerOptions<User> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<User>().setQuery(fir,User.class).build();
//        FirebaseRecyclerAdapter<User, FindFriendListAdapter.ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, FindFriendListAdapter.ViewHolder>(firebaseRecyclerOptions) {
//            @Override
//            protected void onBindViewHolder(@NonNull FindFriendListAdapter.ViewHolder viewHolder, int i, @NonNull User user) {
//                TextView userName, address;
//                CircleImageView user_image;
//                RelativeLayout item;
//                ImageView requestButton;
//                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//
//                user_image = viewHolder.itemView.findViewById(R.id.profile_image_user_find_friends);
//                userName =  viewHolder.itemView.findViewById(R.id.name_user_find_friends);
//                address =  viewHolder.itemView.findViewById(R.id.address_user_find_friends);
//                item =  viewHolder.itemView.findViewById(R.id.users_item_find_friends);
//                requestButton =  viewHolder.itemView.findViewById(R.id.request_button_find_friends_item);
//
//                userName.setText(user.getFirstName() + " " + user.getLastName());
//
//                address.setText(user.getAddress());
//                if (user.getImageThumbnail().equals("none")) {
//                    user_image.setImageResource(R.drawable.user_low);
//                } else {
//                    Picasso.get().load(user.getImageThumbnail()).into(user_image);
//                }
//
//                requestButton.setVisibility(View.GONE);
//
//       /* updateRequestButton(holder.requestButton,users.get(position).getuID());
//
//        holder.requestButton.setOnClickListener(view -> sendRequest(users.get(position).getuID()));*/
//
//                item.setOnClickListener(view -> {
//                    Intent intent = new Intent(getApplicationContext(), UserProfileActivityKt.class).putExtra("uID", user.getuID());
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    getApplicationContext().startActivity(intent);
//                });
//
//
//
//
//            }
//
//            @NonNull
//            @Override
//            public FindFriendListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View itemview =  LayoutInflater.from(parent.getContext()).inflate(R.layout.find_friends_item_layout,parent,false);
//
//                FindFriendListAdapter.ViewHolder viewHolder = new FindFriendListAdapter.ViewHolder(itemview);
//                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//
//                return viewHolder;
//            }
//        };
//
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        firebaseRecyclerAdapter.startListening();
//        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
//    }











    private void getUsers() {
        Query userReference = mReference.limitToFirst(ITEM_LOAD_COUNT * pageCount);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    ArrayList<User> users = new ArrayList<>();
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                        User user = userSnapshot.getValue(User.class);
                        if (!TextUtils.isEmpty(user.getFirstName())){
                            users.add(user);
                        }
                    }

                    mAdapter.removeAllItem();
                    mAdapter.addAllUsers(users);
                    mAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    isLoading = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
