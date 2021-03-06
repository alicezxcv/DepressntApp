package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import com.example.app.databinding.ActivityJournalBinding;
import com.realpacific.clickshrinkeffect.ClickShrinkEffect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Journal extends AppCompatActivity {
    private ActivityJournalBinding binding;
    private String journal_name;
    private TextView journal_header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJournalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadJournalFromFile(getIntent().getExtras().get("entry_title").toString());

        //Button fx
        new ClickShrinkEffect(binding.saveBtn);

        journal_name = getIntent().getExtras().get("entry_title").toString();
        journal_header = (TextView) findViewById(R.id.header);

        journal_header.setText(journal_name);

        binding.journalText.setSelection(binding.journalText.getText().length());

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pgraph = binding.journalText.getText().toString();
                writeJournalToFile(pgraph,getIntent().getExtras().get("entry_title").toString());
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Journal.this, JournalList.class));
                finish();
            }
        });
    }

    public void writeJournalToFile(String pgraph, String entry_title){
        File path = getApplicationContext().getFilesDir();
        File file = new File(path,entry_title+".txt");
        if (file.exists()) {
            try {
                FileWriter writer = new FileWriter(file,false);
                writer.append(pgraph);
                writer.flush();
                writer.close();
                Toast.makeText(Journal.this, "Saved successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                //Toast.makeText(Journal.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        else{
            try {
                file.createNewFile();
                writeJournalToFile(pgraph,entry_title); // call the function itself again
            } catch (IOException e) {
               // Toast.makeText(Journal.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loadJournalFromFile(String entry_title){
        File path = getApplicationContext().getFilesDir();
        String line;
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        try {
            in = new BufferedReader(new FileReader(new File(path, entry_title+".txt")));

            while ((line = in.readLine()) != null) {
                sb.append("\n").append(line);
            }

        } catch (FileNotFoundException e) {
           // Toast.makeText(Journal.this, e.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //Toast.makeText(Journal.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        binding.journalText.setText(sb.toString());
    }
}