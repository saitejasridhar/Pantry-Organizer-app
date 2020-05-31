package com.example.fridgepal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class fruits extends AppCompatActivity {
    private ArrayList<Blog> items;

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        user= FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        uid=user.getUid();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Fruits");
        mDatabase.keepSynced(true);
        items=new ArrayList<>();

        mBlogList=(RecyclerView)findViewById(R.id.fruitsrecycleview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));


    }
    @Override
    protected void onStart()
    {

        super.onStart();
        final MediaPlayer added=MediaPlayer.create(fruits.this,R.raw.added);
        FirebaseRecyclerAdapter<Blog, fruits.BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, fruits.BlogViewHolder>
                (Blog.class,R.layout.blog_row, fruits.BlogViewHolder.class,mDatabase )
        {

            @Override
            public void populateViewHolder(fruits.BlogViewHolder blogViewHolder, final Blog blog, final int i) {

                blogViewHolder.setTitle(blog.getTitle());
                blogViewHolder.setImage(getApplicationContext(),blog.getImage());

                blogViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final DialogPlus dialog = DialogPlus.newDialog(fruits.this)
                                .setGravity(Gravity.CENTER)
                                .setMargin(50,0,50,0)
                                .setContentHolder(new ViewHolder(R.layout.content))
                                .setExpanded(false)
                                .create();
                        dialog.show();

                        View holderView = (LinearLayout)dialog.getHolderView();

                        final EditText title = holderView.findViewById(R.id.quantity);
                        Button button=holderView.findViewById(R.id.diaadd);

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                added.start();
                                int map= Integer.parseInt(title.getText().toString());

                                FirebaseDatabase.getInstance().getReference().child(uid).child("Fridge").child(getRef(i).getKey()).child("title").setValue(blog.getTitle());
                                FirebaseDatabase.getInstance().getReference().child(uid).child("Fridge").child(getRef(i).getKey()).child("image").setValue(blog.getImage());
                                FirebaseDatabase.getInstance().getReference().child(uid).child("Fridge").child(getRef(i).getKey()).child("desc").setValue(blog.getDesc());
                                FirebaseDatabase.getInstance().getReference().child(uid).child("Fridge").child(getRef(i).getKey()).child("quantity").setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialog.dismiss();
                                    }
                                });

                                Toast.makeText(fruits.this,"Item added",Toast.LENGTH_LONG).show();
                            }
                        });


                    }
                });
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        Button add;



        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;

            add=(Button) mView.findViewById(R.id.add);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        public void setTitle(String title) {

            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setImage(Context ctx, String image)
        {
            ImageView post_Image=(ImageView)mView.findViewById(R.id.post_image);
            Picasso.get().load(image).into(post_Image);
        }

    }
}