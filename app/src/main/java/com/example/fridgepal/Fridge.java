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

public class Fridge extends AppCompatActivity implements View.OnClickListener {



        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_fridge);
                Toolbar  TbFridge = (Toolbar) findViewById(R.id.toolbarf);
                setSupportActionBar(TbFridge);
                getSupportActionBar().setTitle("Categories");



                CardView button1=findViewById(R.id.fruits);
                CardView button2=findViewById(R.id.vegetables);
                CardView button3=findViewById(R.id.dairy);
                CardView button4=findViewById(R.id.greens);
                CardView button5=findViewById(R.id.beverages);
                CardView button6=findViewById(R.id.herbs);
                CardView button7=findViewById(R.id.meat);
                CardView button8=findViewById(R.id.bakery);
                CardView button9=findViewById(R.id.sauces);



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
                        case R.id.fruits:
                                opennext( fruits.class);
                                break;
                        case R.id.vegetables:
                                opennext(vegetables.class);
                                break;
                        case R.id.dairy:
                                opennext(dairy.class);
                                break;
                        case R.id.greens:
                                opennext(greens.class);
                                break;
                        case R.id.beverages:
                                opennext(beverages.class);
                                break;
                        case R.id.herbs:
                                opennext(herbs.class);
                                break;
                        case R.id.meat:
                                opennext(meat.class);
                                break;
                        case R.id.bakery:
                                opennext(bakery.class);
                                break;
                        case R.id.sauces:
                                opennext(sauces.class);
                                break;
                }
        }

        private void opennext( final Class<? extends Activity> ActivityToOpen)
        {
                startActivity(new Intent(getBaseContext(), ActivityToOpen));
        }
}