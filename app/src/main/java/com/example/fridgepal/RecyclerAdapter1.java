package com.example.fridgepal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.RestrictionEntry;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.ViewHolder> {


    private Activity context;
    private ArrayList<Recipe> messagesList;


    public RecyclerAdapter1(ArrayList<Recipe> messagesList,Activity context) {
        this.context = context;
        this.messagesList = messagesList;
    }


    @NonNull
    @Override
    public RecyclerAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.relative.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade));

        holder.textView.setText(messagesList.get(position).getName());

        holder.textView1.setText(messagesList.get(position).getIng());

        holder.textView2.setText(messagesList.get(position).getPro());


        Glide.with(context).load(messagesList.get(position).getImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=messagesList.get(position).getLink();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.google.android.youtube");
                context.startActivity(intent);
            }
        });

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, final int position, boolean isLongClick) {
                if(isLongClick)
                {

                }
                else {
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

        ImageView imageView;
        TextView textView;
        TextView textView1;
        TextView textView2;
        RelativeLayout relative;

        Button youtube;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image);
            relative=itemView.findViewById(R.id.rel);
            textView=itemView.findViewById(R.id.title);
            textView1=itemView.findViewById(R.id.inc);
            textView2=itemView.findViewById(R.id.pro);
            youtube=itemView.findViewById(R.id.youtube);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);

        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),true);
            return true;
        }
    }



}
