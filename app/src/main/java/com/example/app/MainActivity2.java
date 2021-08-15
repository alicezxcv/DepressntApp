package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;


import com.example.app.databinding.ActivityMainScreenBinding;


public class MainActivity2 extends AppCompatActivity {

    private ActivityMainScreenBinding binding;
    private String _name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        request_name();

        // handle click video image button
        binding.videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// start activity to youtube player

                startActivity(new Intent(MainActivity2.this, ViewVideo.class));

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
        startActivity(new Intent(MainActivity2.this, MainActivity.class));
        finish();
    }
}