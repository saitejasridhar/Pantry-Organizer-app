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



                CardView button1=findViewById(R.id.fruits);
                CardView button2=findViewById(R.id.vegetables);
                CardView button3=findViewById(R.id.dairy);
                CardView button4=findViewById(R.id.greens);


                button1.setOnClickListener(this);
                button2.setOnClickListener(this);
                button3.setOnClickListener(this);
                button4.setOnClickListener(this);

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
                }
        }

        private void opennext( final Class<? extends Activity> ActivityToOpen)
        {
                startActivity(new Intent(getBaseContext(), ActivityToOpen));
        }
}