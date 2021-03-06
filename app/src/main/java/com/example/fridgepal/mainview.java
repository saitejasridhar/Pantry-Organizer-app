package com.example.fridgepal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

public class mainview extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainview);

        Toolbar  TbMain = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(TbMain);
        getSupportActionBar().setTitle("Fridge Pal");

        final TapBarMenu tapBarMenu=findViewById(R.id.tapBarMenu);

        Button button1=findViewById(R.id.freezer);
        Button button2=findViewById(R.id.fridge);
        Button button3=findViewById(R.id.pantry);
        Button button4=findViewById(R.id.list);
        Button button5=findViewById(R.id.shoppinglist);
        Button button6=findViewById(R.id.recipes);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);

        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                tapBarMenu.toggle();
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.freezer:
                opennext( items_in_freezer.class);

                break;
            case R.id.fridge:
                opennext(items_in_fridge.class);
                break;
            case R.id.pantry:
                opennext(items_in_pantry.class);
                break;
            case R.id.list:
                opennext(items_in_list.class);
                break;
            case R.id.shoppinglist:
            {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                System.exit(0);

            }

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