package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import com.example.app.databinding.ActivityJournalListBinding;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import java.util.Set;


public class JournalList extends AppCompatActivity {
    private ActivityJournalListBinding binding;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_journals = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJournalListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnAddJournal.bringToFront();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_of_journals);
        binding.listView.setAdapter(arrayAdapter);

        loadJournal();

        binding.btnAddJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
                String date = df.format(c.getTime());

                File path = getApplicationContext().getFilesDir();
                File file = new File(path,"journal.txt");
                if (file.exists()) {
                    try {
                        FileWriter writer = new FileWriter(file,true);
                        writer.append(date.toString() + "\r\n");
                        writer.flush();
                        writer.close();
                        Toast.makeText(JournalList.this, "Saved", Toast.LENGTH_SHORT).show();
                        loadJournal();
                    } catch (Exception e) {
                        Toast.makeText(JournalList.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getApplicationContext(),Journal.class);
                intent.putExtra("entry_title",((TextView)view).getText().toString());
                startActivity(intent);
            }
        });

        binding.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Entry = ((TextView)view).getText().toString();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(JournalList.this);
                builder1.setMessage("Do you want to delete this journal?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // perform delete entry
                                deleteJournal(Entry);
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                return false;
            }
        });
    }

    public void deleteJournal(String entry){
        File path = getApplicationContext().getFilesDir();
        Set<String> set= new HashSet<String>();

        String line;

        BufferedReader in = null;
        // load all entries to memory then delete that entry
        try {
            in = new BufferedReader(new FileReader(new File(path, "journal.txt")));

            while ((line = in.readLine()) != null) {
                set.add(line);
            }

        } catch (FileNotFoundException e) {
            Toast.makeText(JournalList.this, e.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(JournalList.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        // remove that string at line...
        set.remove(entry);

        // rewrite back to journal.txt
        File file = new File(path,"journal.txt");

        if (file.exists()) {
            try {
                FileWriter writer = new FileWriter(file,false);
                for (String s : set){
                    writer.append(s + "\r\n");
                }

                writer.flush();
                writer.close();
                Toast.makeText(JournalList.this, "Saved", Toast.LENGTH_SHORT).show();
                loadJournal();
            } catch (Exception e) {
                Toast.makeText(JournalList.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        // also delete <entry>.txt...
    }

    public void loadJournal(){
        File path = getApplicationContext().getFilesDir();
        File file = new File(path,"journal.txt");

        String line;
        BufferedReader in = null;

        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                Toast.makeText(JournalList.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }


        try {
            list_of_journals.clear();
            in = new BufferedReader(new FileReader(file));

            while ((line = in.readLine()) != null) {
                list_of_journals.add(line);
            }

        } catch (FileNotFoundException e) {
            Toast.makeText(JournalList.this, e.toString(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(JournalList.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        arrayAdapter.notifyDataSetChanged();
    }
}