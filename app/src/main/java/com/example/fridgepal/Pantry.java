package com.example.fridgepal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;

public class Pantry extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        Toolbar  TbFridge = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(TbFridge);
        getSupportActionBar().setTitle("Categories");



        CardView button1=findViewById(R.id.flour);
        CardView button2=findViewById(R.id.grains);
        CardView button3=findViewById(R.id.dal);
        CardView button4=findViewById(R.id.spices);
        CardView button5=findViewById(R.id.root_vegetables);
        CardView button6=findViewById(R.id.snacks);
        CardView button7=findViewById(R.id.oil);
        CardView button8=findViewById(R.id.pickels);
        CardView button9=findViewById(R.id.others);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.flour:
                opennext( flour.class);
                break;
            case R.id.grains:
                opennext(grains.class);
                break;
            case R.id.dal:
                opennext(dal.class);
                break;
            case R.id.spices:
                opennext(spices.class);
                break;
            case R.id.root_vegetables:
                opennext(root_vegetables.class);
                break;
            case R.id.snacks:
                opennext(snacks.class);
                break;
            case R.id.oil:
                opennext(oil.class);
                break;
            case R.id.pickels:
                opennext(pickels.class);
                break;
            case R.id.others:
                opennext(other.class);
                break;

        }
    }

    private void opennext( final Class<? extends Activity> ActivityToOpen)
    {
        startActivity(new Intent(getBaseContext(), ActivityToOpen));
    }
}