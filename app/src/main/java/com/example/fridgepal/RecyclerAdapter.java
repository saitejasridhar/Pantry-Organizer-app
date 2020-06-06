package com.example.fridgepal;

import android.app.Activity;
import android.content.Context;
import android.content.RestrictionEntry;
import android.graphics.Color;
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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private Activity context;
    private ArrayList<Messages> messagesList;
    public static int selected_item = 0;


    public RecyclerAdapter(ArrayList<Messages> messagesList,Activity context) {
        this.context = context;
        this.messagesList = messagesList;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shoplist_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

//        if(position == selected_item) {
//            holder.textView.setTextColor(Color.parseColor("#0000ff"));
////            holder.imageView.setBackgroundResource(R.drawable.add);
//        } else {
//            holder.textView.setTextColor(Color.parseColor("#000000"));
////            holder.imageView.setBackgroundResource(0);
//        }

        holder.relative.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade));

        holder.textView.setText(messagesList.get(position).getName());


        Glide.with(context).load(messagesList.get(position).getImageUrl()).into(holder.imageView);

        holder.addlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               RecyclerAdapter.selected_item = position;
                final DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.content))
                        .setExpanded(false)
                        .create();
                dialog.show();

                View holderView = (LinearLayout)dialog.getHolderView();

                final EditText title = holderView.findViewById(R.id.quantity);
                final EditText unit = holderView.findViewById(R.id.unit);
                Button button=holderView.findViewById(R.id.diaadd);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int map= Integer.parseInt(title.getText().toString());
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        Date date = new Date();
                        String strDate = dateFormat.format(date).toString();
                        String units=unit.getText().toString();

                        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Shopping List").child(String.valueOf(holder.getAdapterPosition())).child("title").setValue(messagesList.get(position).getName());
                        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Shopping List").child(String.valueOf(holder.getAdapterPosition())).child("unit").setValue(units);
                        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Shopping List").child(String.valueOf(holder.getAdapterPosition())).child("image").setValue(messagesList.get(position).getImageUrl());
                        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Shopping List").child(String.valueOf(holder.getAdapterPosition())).child("time").setValue(strDate);
                        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Shopping List").child(String.valueOf(holder.getAdapterPosition())).child("quantity").setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                dialog.dismiss();

                            }
                        });
                        Toast.makeText(context,"Item added",Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, final int position, boolean isLongClick) {
                if(isLongClick)
                {

                }
                else {
                    Log.i("Fuck em all","Fuck em all");
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
        public ImageView addlist;
        RelativeLayout relative;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image);
            relative=itemView.findViewById(R.id.rel);
            textView=itemView.findViewById(R.id.title);
            addlist=itemView.findViewById(R.id.addlist);

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
