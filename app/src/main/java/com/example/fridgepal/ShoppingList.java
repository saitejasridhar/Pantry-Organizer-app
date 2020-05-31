package com.example.fridgepal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.ArrayList;

public class ShoppingList extends AppCompatActivity{
    RecyclerView recyclerView;
    private DatabaseReference myRef;
    private ArrayList<Messages> messagesList;
    private RecyclerAdapter recyclerAdapter;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        recyclerView = findViewById(R.id.shoppinglistrecycleview);
        searchView=findViewById(R.id.searchView);
        messagesList = new ArrayList<>();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        myRef = FirebaseDatabase.getInstance().getReference();




        ClearAll();

        GetDataFromFirebase();

    }

    @Override
    protected void onStart()
    {

        super.onStart();
        if(searchView!=null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }


    }
    private void GetDataFromFirebase(){
        Query query=myRef.child("Shopping List");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Messages messages=new Messages();
                    messages.setImageUrl(snapshot.child("image").getValue().toString());
                    messages.setName(snapshot.child("title").getValue().toString());
                    messagesList.add(messages);
                }
                RecyclerAdapter recyclerAdapter=new RecyclerAdapter(messagesList,ShoppingList.this);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

     }


     private void ClearAll()
     {
         if(messagesList!=null)
         {
             messagesList.clear();
             if(recyclerAdapter!=null){

                 recyclerAdapter.notifyDataSetChanged();
             }
         }
         messagesList=new ArrayList<>();
     }

     private void search(String str)
     {
         ArrayList<Messages> mylist=new ArrayList<>();
         for(Messages object: messagesList)
         {
             if(object.getName().toLowerCase().contains(str.toLowerCase()))
             {
                 mylist.add(object);
             }
         }
         RecyclerAdapter adapter=new RecyclerAdapter(mylist,ShoppingList.this);
         recyclerView.setAdapter(adapter);
     }

}