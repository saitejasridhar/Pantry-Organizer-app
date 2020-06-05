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

public class Recipes extends AppCompatActivity{
    RecyclerView recyclerView;
    private DatabaseReference myRef;
    private ArrayList<Recipe> messagesList;
    private RecyclerAdapter1 recyclerAdapter;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        recyclerView = findViewById(R.id.recipesrecycleview);
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
        Query query=myRef.child("Recipes");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Recipe recipe=new Recipe();
                    recipe.setName(snapshot.child("name").getValue().toString());
                    recipe.setImage(snapshot.child("image").getValue().toString());
                    recipe.setIng(snapshot.child("ing").getValue().toString());
                    recipe.setLink(snapshot.child("link").getValue().toString());
                    recipe.setPro(snapshot.child("pro").getValue().toString());
                    messagesList.add(recipe);
                }
                RecyclerAdapter1 recyclerAdapter=new RecyclerAdapter1(messagesList,Recipes.this);
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
        ArrayList<Recipe> mylist=new ArrayList<>();

        str = str.replaceAll("[,;\\s]", "");

        for(Recipe object: messagesList)
        {
           String str1=object.getIng().replaceAll("[,;\\s]", "");
            if(str1.toLowerCase().contains(str.toLowerCase()))
            {
                mylist.add(object);
            }
        }
        RecyclerAdapter1 adapter=new RecyclerAdapter1(mylist,Recipes.this);
        recyclerView.setAdapter(adapter);
    }

}