package com.example.fridgepal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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



        Button button1=findViewById(R.id.fruits);
        Button button2=findViewById(R.id.vegetables);
        Button button3=findViewById(R.id.dairy);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
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
        }
        }

private void opennext( final Class<? extends Activity> ActivityToOpen)
        {
        startActivity(new Intent(getBaseContext(), ActivityToOpen));
        }
        }
