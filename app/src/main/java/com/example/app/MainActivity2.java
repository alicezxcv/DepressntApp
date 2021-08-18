package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.app.databinding.ActivityMainScreenBinding;

import com.google.android.material.navigation.NavigationView;


public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainScreenBinding binding;

    // View Variables
    private String _name;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        request_name();

        // handle click psychiatrist image button
        binding.psychiatristBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,Psychiatrist.class);
                intent.putExtra("type","user");
                startActivity(intent);
            }
        });


        // handle click video image button
        binding.videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// start activity to youtube player
                startActivity(new Intent(MainActivity2.this, ViewVideo.class));
            }
        });
        // handle click activities image button
        binding.activitiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// start activity to healthy activities
                startActivity(new Intent(MainActivity2.this, HealthyActivities.class));
            }
        });

        // handle click schedule image button
        binding.scheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,Schedule.class));
            }
        });


        // handle click journal image button
        binding.journalBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // start journal activity
                startActivity(new Intent(MainActivity2.this,JournalList.class));

            }
        });


        /*---------------NAVIGATION DRAWER---------------*/

        /*---------------Hooks---------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.pfp_nav);
        toolbar = findViewById(R.id.pfp);

        /*---------------Toolbar---------------*/
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.user_pfp);

        /*---------------Navigation Drawer Menu---------------*/
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /*---------------Navigation Element View---------------*/
        NavigationView navigationView = findViewById(R.id.pfp_nav);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_chat:
                startActivity(new Intent(MainActivity2.this, Room.class));
                finish();
                break;

            case R.id.nav_help:
                Toast.makeText(MainActivity2.this, "Please help!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_about:
                Toast.makeText(MainActivity2.this, "19127327_19127428_19127555", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_bug:
                Toast.makeText(MainActivity2.this, "Reported!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
                break;

        }
        return true;
    }

    private void request_name(){
        try{
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String _name = preferences.getString("Name", "");
            if (_name.equals("")){
                throw new Exception();
            }
            binding.name.setText(_name);
        }
        catch (Exception e){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Enter name:");

            final EditText input_field = new EditText(this);

            builder.setView(input_field);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    _name = input_field.getText().toString();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity2.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Name",_name);
                    editor.apply();
                    binding.name.setText(_name);
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    startActivity(new Intent(MainActivity2.this, MainActivity.class));
                    finish();
                }
            });
            builder.show();
        }


    }

    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
        startActivity(new Intent(MainActivity2.this, MainActivity.class));
        finish();
        }
    }
}