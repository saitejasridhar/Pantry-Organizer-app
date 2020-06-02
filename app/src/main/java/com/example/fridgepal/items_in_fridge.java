package com.example.fridgepal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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

public class items_in_fridge extends AppCompatActivity {

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
        setContentView(R.layout.activity_items_in_fridge);
        Button button1=findViewById(R.id.addfridge);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            opennext();
            }
        });
        mDatabase= FirebaseDatabase.getInstance().getReference().child(uid).child("Fridge");
        mDatabase.keepSynced(true);

        mBlogList=(RecyclerView)findViewById(R.id.itemsfridgerecycleview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart()
    {
        final MediaPlayer added=MediaPlayer.create(items_in_fridge.this,R.raw.added);
        Toolbar  TbFridge = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(TbFridge);
        getSupportActionBar().setTitle("Fridge");
        super.onStart();
        final MediaPlayer delete=MediaPlayer.create(items_in_fridge.this,R.raw.delete_sound);

        FirebaseRecyclerAdapter<Blog, items_in_fridge.BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, items_in_fridge.BlogViewHolder>
                (Blog.class,R.layout.added_items_row, items_in_fridge.BlogViewHolder.class,mDatabase )
        {
            @Override
            protected void populateViewHolder(items_in_fridge.BlogViewHolder blogViewHolder, final Blog blog, final int i) {

                blogViewHolder.setTitle(blog.getTitle());
                blogViewHolder.setQuantity(blog.getQuantity());
                blogViewHolder.setImage(getApplicationContext(),blog.getImage());
                blogViewHolder.setTime(blog.getTime());
                blogViewHolder.update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final DialogPlus dialog = DialogPlus.newDialog(items_in_fridge.this)
                                .setGravity(Gravity.CENTER)
                                .setMargin(50,0,50,0)
                                .setContentHolder(new ViewHolder(R.layout.content1))
                                .setExpanded(false)
                                .create();
                        dialog.show();

                        View holderView = (LinearLayout)dialog.getHolderView();

                        final EditText title = holderView.findViewById(R.id.quantity);
                        Button button=holderView.findViewById(R.id.diaadd);

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

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

                                Toast.makeText(items_in_fridge.this,"Fridge Updated",Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });
                blogViewHolder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final DialogPlus dialog = DialogPlus.newDialog(items_in_fridge.this)
                                .setGravity(Gravity.CENTER)
                                .setMargin(50,0,50,0)
                                .setContentHolder(new ViewHolder(R.layout.content2))
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

                                FirebaseDatabase.getInstance().getReference().child(uid).child("Shopping List").child(getRef(i).getKey()).child("title").setValue(blog.getTitle());
                                FirebaseDatabase.getInstance().getReference().child(uid).child("Shopping List").child(getRef(i).getKey()).child("image").setValue(blog.getImage());
                                FirebaseDatabase.getInstance().getReference().child(uid).child("Shopping List").child(getRef(i).getKey()).child("quantity").setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialog.dismiss();
                                    }
                                });

                                Toast.makeText(items_in_fridge.this,"Item added to List",Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });
                blogViewHolder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        delete.start();
                        FirebaseDatabase.getInstance().getReference()
                                .child(uid).child("Fridge")
                                .child(getRef(i).getKey())
                                .removeValue();
                    }
                });


            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        ImageView delete;
        ImageView update;
        ImageView add;

        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
             delete = mView.findViewById(R.id.delete);
            update = mView.findViewById(R.id.update);
            add=mView.findViewById(R.id.addlist);
        }

        public void setTitle(String title)
        {
            TextView added_title=(TextView)mView.findViewById(R.id.added_title);
            added_title.setText(title);
        }

        public void setTime(String time)
        {
            TextView added_time=(TextView)mView.findViewById(R.id.added_time);
            added_time.setText(time);
        }

        public void setQuantity(int quantity)
        {
            TextView added_kgs=(TextView)mView.findViewById(R.id.added_kgs);
            added_kgs.setText(quantity+" Kgs Remaining");
        }
        public void setImage(Context ctx, String image)
        {
            ImageView added_image=(ImageView)mView.findViewById(R.id.added_image);
            Picasso.get().load(image).into(added_image);
        }

    }

    public void opennext()
    {
        Intent intent = new Intent(this, Fridge.class);
        startActivity(intent);

    }

}
