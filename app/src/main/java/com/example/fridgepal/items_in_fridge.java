package com.example.fridgepal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class items_in_fridge extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_in_fridge);
        Button button1=findViewById(R.id.addfridge);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        opennext(Fridge.class);
    }

    private void opennext( final Class<? extends Activity> ActivityToOpen)
    {
        startActivity(new Intent(getBaseContext(), ActivityToOpen));
    }
}
