package com.example.fridgepal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button1=findViewById(R.id.button1);
        Button button2=findViewById(R.id.button2);
        Button button3=findViewById(R.id.button3);
        Button button4=findViewById(R.id.button4);
        Button button5=findViewById(R.id.button5);
        Button button6=findViewById(R.id.button6);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    switch (view.getId())
    {
        case R.id.button1:
        Toast.makeText(MainActivity.this, "Button 1 clicked", Toast.LENGTH_SHORT).show();
            break;
        case R.id.button2:
            Toast.makeText(MainActivity.this, "Button 2 clicked", Toast.LENGTH_SHORT).show();
            break;
        case R.id.button3:
            Toast.makeText(MainActivity.this, "Button 3 clicked", Toast.LENGTH_SHORT).show();
            break;
        case R.id.button4:
            Toast.makeText(MainActivity.this, "Button 4 clicked", Toast.LENGTH_SHORT).show();
            break;
        case R.id.button5:
            Toast.makeText(MainActivity.this, "Button 5 clicked", Toast.LENGTH_SHORT).show();
            break;
        case R.id.button6:
            Toast.makeText(MainActivity.this, "Button 6 clicked", Toast.LENGTH_SHORT).show();
            break;
    }
    }
}
