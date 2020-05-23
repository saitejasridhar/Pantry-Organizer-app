package com.example.fridgepal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class items_in_freezer extends AppCompatActivity implements View.OnClickListener {

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_in_freezer);
        Button button1=findViewById(R.id.addfreezer);
        button1.setOnClickListener(this);
        }

@Override
public void onClick(View view) {

        opennext(Freezer.class);
        }

private void opennext( final Class<? extends Activity> ActivityToOpen)
        {
        startActivity(new Intent(getBaseContext(), ActivityToOpen));
        }
        }