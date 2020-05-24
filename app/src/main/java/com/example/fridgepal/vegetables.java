package com.example.fridgepal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class vegetables extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Vegetables");
        mDatabase.keepSynced(true);

        mBlogList=(RecyclerView)findViewById(R.id.vegetablesrecycleview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));


    }
    @Override
    protected void onStart()
    {

        super.onStart();
        FirebaseRecyclerAdapter<Blog, vegetables.BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, vegetables.BlogViewHolder>
                (Blog.class,R.layout.blog_row, vegetables.BlogViewHolder.class,mDatabase )
        {
            @Override
            public void populateViewHolder(vegetables.BlogViewHolder blogViewHolder, final Blog blog, final int i) {

                blogViewHolder.setTitle(blog.getTitle());
//              blogViewHolder.setDesc(blog.getDesc());
                blogViewHolder.setImage(getApplicationContext(),blog.getImage());
                blogViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Vegetables");
                        databaseReference.child(getRef(i).getKey()).child("desc").setValue("1");
                        Log.i("Fuck","Fuckkkk");

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