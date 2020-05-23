package com.example.fridgepal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class fruits extends AppCompatActivity {
    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Fruits");
        mDatabase.keepSynced(true);

        mBlogList=(RecyclerView)findViewById(R.id.fruitsrecycleview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseRecyclerAdapter<Blog, fruits.BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, fruits.BlogViewHolder>
                (Blog.class,R.layout.blog_row, fruits.BlogViewHolder.class,mDatabase )
        {
            @Override
            protected void populateViewHolder(fruits.BlogViewHolder blogViewHolder, Blog blog, int i) {
                blogViewHolder.setTitle(blog.getTitle());
//                blogViewHolder.setDesc(blog.getDesc());
                blogViewHolder.setImage(getApplicationContext(),blog.getImage());

            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
        }

        public void setTitle(String title)
        {
            TextView post_title=(TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
//        public void setDesc(String desc)
//        {
//            TextView post_desc=(TextView)mView.findViewById(R.id.post_desc);
//            post_desc.setText(desc);
//        }
        public void setImage(Context ctx, String image)
        {
            ImageView post_Image=(ImageView)mView.findViewById(R.id.post_image);
            Picasso.get().load(image).into(post_Image);
        }

    }
}
