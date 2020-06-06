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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class spices extends AppCompatActivity {
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
        setContentView(R.layout.activity_spices);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Spices");
        mDatabase.keepSynced(true);
        items=new ArrayList<>();

        mBlogList=(RecyclerView)findViewById(R.id.spicesrecycleview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));


    }
    @Override
    protected void onStart()
    {

        super.onStart();
        final MediaPlayer added=MediaPlayer.create(spices.this,R.raw.added);
        FirebaseRecyclerAdapter<Blog, spices.BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, spices.BlogViewHolder>
                (Blog.class,R.layout.blog_row, spices.BlogViewHolder.class,mDatabase )
        {

            @Override
            public void populateViewHolder(spices.BlogViewHolder blogViewHolder, final Blog blog, final int i) {

                blogViewHolder.setTitle(blog.getTitle());
                blogViewHolder.setImage(getApplicationContext(),blog.getImage());

                blogViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final DialogPlus dialog = DialogPlus.newDialog(spices.this)
                                .setGravity(Gravity.CENTER)
                                .setMargin(50,0,50,0)
                                .setContentHolder(new ViewHolder(R.layout.content))
                                .setExpanded(false)
                                .create();
                        dialog.show();

                        View holderView = (LinearLayout)dialog.getHolderView();

                        final EditText title = holderView.findViewById(R.id.quantity);
                        final EditText unit=holderView.findViewById(R.id.unit);
                        Button button=holderView.findViewById(R.id.diaadd);

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                added.start();
                                int map= Integer.parseInt(title.getText().toString());
                                String units=unit.getText().toString();
                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = new Date();
                                String strDate = dateFormat.format(date).toString();

                                FirebaseDatabase.getInstance().getReference().child(uid).child("Pantry").child(getRef(i).getKey()).child("title").setValue(blog.getTitle());
                                FirebaseDatabase.getInstance().getReference().child(uid).child("Pantry").child(getRef(i).getKey()).child("image").setValue(blog.getImage());
                                FirebaseDatabase.getInstance().getReference().child(uid).child("Pantry").child(getRef(i).getKey()).child("desc").setValue(blog.getDesc());
                                FirebaseDatabase.getInstance().getReference().child(uid).child("Pantry").child(getRef(i).getKey()).child("time").setValue(strDate);
                                FirebaseDatabase.getInstance().getReference().child(uid).child("Pantry").child(getRef(i).getKey()).child("unit").setValue(units);
                                FirebaseDatabase.getInstance().getReference().child(uid).child("Pantry").child(getRef(i).getKey()).child("quantity").setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialog.dismiss();
                                    }
                                });

                                Toast.makeText(spices.this,"Item added",Toast.LENGTH_LONG).show();
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