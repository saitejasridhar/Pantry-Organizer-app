package com.example.fridgepal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button1=findViewById(R.id.freezer);
        Button button2=findViewById(R.id.fridge);
        Button button3=findViewById(R.id.pantry);
        Button button4=findViewById(R.id.kitchen);
        Button button5=findViewById(R.id.shoppinglist);
        Button button6=findViewById(R.id.recipes);

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
        case R.id.freezer:
           opennext( Freezer.class);
            break;
        case R.id.fridge:
            opennext(Fridge.class);
           break;
        case R.id.pantry:
            opennext(Pantry.class);
            break;
        case R.id.kitchen:
            opennext(MainActivity.class);
            break;
        case R.id.shoppinglist:
            opennext(ShoppingList.class);
            break;
        case R.id.recipes:
            opennext(Recipes.class);
            break;
    }
    }

    private void opennext( final Class<? extends Activity> ActivityToOpen)
    {
        startActivity(new Intent(getBaseContext(), ActivityToOpen));
    }
}
