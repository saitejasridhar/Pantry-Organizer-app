package com.example.fridgepal;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View view;

     public ViewHolder(@NonNull View itemView) {
        super(itemView);
        view=itemView;
    }

    public void setdetails(Context context,String title,String image){
        TextView mtitletv=view.findViewById(R.id.rTitleView);
        ImageView mImagetv=view.findViewById(R.id.rImageView);

        mtitletv.setText(title);
        Picasso.get().load(image).into(mImagetv);

        Animation animation = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        itemView.startAnimation(animation);
    }

}
