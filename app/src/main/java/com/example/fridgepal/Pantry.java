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



        CardView button1=findViewById(R.id.fruits1);
        CardView button2=findViewById(R.id.vegetables1);
        CardView button3=findViewById(R.id.dairy1);
        CardView button4=findViewById(R.id.greens1);
        CardView button5=findViewById(R.id.beverages1);
        CardView button6=findViewById(R.id.herbs1);
        CardView button7=findViewById(R.id.meat1);
        CardView button8=findViewById(R.id.bakery1);
        CardView button9=findViewById(R.id.sauces1);
        Button button10=findViewById(R.id.essen1);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fruits1:
                opennext( fruits.class);
                break;
            case R.id.vegetables1:
                opennext(vegetables.class);
                break;
            case R.id.dairy1:
                opennext(dairy.class);
                break;
            case R.id.greens1:
                opennext(greens.class);
                break;
            case R.id.beverages1:
                opennext(beverages.class);
                break;
            case R.id.herbs1:
                opennext(herbs.class);
                break;
            case R.id.meat1:
                opennext(meat.class);
                break;
            case R.id.bakery1:
                opennext(bakery.class);
                break;
            case R.id.sauces1:
                opennext(sauces.class);
                break;
            case R.id.essen1:
                opennext(essen.class);
                break;

        }
    }

    private void opennext( final Class<? extends Activity> ActivityToOpen)
    {
        startActivity(new Intent(getBaseContext(), ActivityToOpen));
    }
}