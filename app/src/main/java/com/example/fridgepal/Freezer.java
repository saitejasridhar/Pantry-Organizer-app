package com.example.fridgepal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Freezer extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freezer);

        mRecyclerView= findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("Fruits");
    }

    @Override
    protected void onStart()
    {
    super.onStart();
   FirebaseRecyclerAdapter<Member,ViewHolder> firebaseRecylerAdapter=
            new FirebaseRecyclerAdapter<Member, ViewHolder>(
                    Member.class,
                    R.layout.image,
                    ViewHolder.class,
                    reference

            ) {
                @Override
                protected void populateViewHolder(ViewHolder viewHolder, Member member, int i) {

                    viewHolder.setdetails(getApplicationContext(),member.getTitle(),member.getImage());
                }

            };
   mRecyclerView.setAdapter(firebaseRecylerAdapter);
    }
}
