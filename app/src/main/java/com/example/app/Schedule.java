package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import com.example.app.databinding.ActivityScheduleBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.realpacific.clickshrinkeffect.ClickShrinkEffect;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Schedule extends AppCompatActivity {

    private ActivityScheduleBinding binding;

    private SimpleDateFormat simpleDate = new SimpleDateFormat("MMMM yyyy");
    private SimpleDateFormat simpleDatev2 = new SimpleDateFormat("dd-MM-yyyy");

    private Date selectedDate = new Date();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Button fx
        new ClickShrinkEffect(binding.addNoteButton);
        new ClickShrinkEffect(binding.deleteNoteBtn);
        new ClickShrinkEffect(binding.deleteAllNoteBtn);

        getSupportActionBar().setTitle(simpleDate.format(binding.compactcalendarView.getFirstDayOfCurrentMonth()));

        // read file and load event
        readEventFromFile();

        binding.addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.noteEditText.getText().toString().equals("") == false) {
                    // remove old event first to prevent duplicate
                    binding.compactcalendarView.removeEvents(selectedDate.getTime());

                    Event ev = new Event(Color.BLACK, selectedDate.getTime(), binding.noteEditText.getText().toString());
                    binding.compactcalendarView.addEvent(ev);
                    /// save event on file
                    // Toast.makeText(Schedule.this,simpleDatev2.format(selectedDate) , Toast.LENGTH_SHORT).show();
                    writeEventToFile(simpleDatev2.format(selectedDate), binding.noteEditText.getText().toString());
                    Toast.makeText(Schedule.this, "Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.deleteNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.compactcalendarView.removeEvents(selectedDate.getTime());
                deleteEvent(simpleDatev2.format(selectedDate));
                Toast.makeText(Schedule.this, "Saved", Toast.LENGTH_SHORT).show();
                binding.noteEditText.setText("");
            }
        });

        binding.deleteAllNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Schedule.this);
                builder1.setMessage("This action will delete all the event\nDo you want to proceed?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                binding.compactcalendarView.removeAllEvents();
                                // empty file
                                deleteAll();
                                binding.noteEditText.setText("");
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
            }
        });

        binding.compactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                selectedDate = dateClicked;
                List<Event> events = binding.compactcalendarView.getEvents(dateClicked);
                String note = "";
                for (Event e : events){
                    note += e.getData().toString() + "\n";
                }
                binding.noteEditText.setText(note);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                getSupportActionBar().setTitle(simpleDate.format(firstDayOfNewMonth));

            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Schedule.this, MainActivity2.class));
                finish();
            }
        });
    }

    public void writeEventToFile(String date, String note) {
        File path = getApplicationContext().getFilesDir();
        File file = new File(path, "event.txt");
        if (file.exists()) {
            try {
                FileWriter writer = new FileWriter(file, true);
                writer.append(date + "\n");
                writer.append(note+ "\n");
                writer.flush();
                writer.close();
            } catch (Exception e) {
                //Toast.makeText(Schedule.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        } else {
            try {
                file.createNewFile();
                writeEventToFile(date,note); // call the function itself again
            } catch (IOException e) {
                Toast.makeText(Schedule.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void readEventFromFile() {
        File path = getApplicationContext().getFilesDir();
        String line1,line2;
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        try {
            in = new BufferedReader(new FileReader(new File(path, "event.txt")));

            while ((line1 = in.readLine()) != null && ((line2 = in.readLine()) != null)) {
                Date date = simpleDatev2.parse(line1);
                Event ev = new Event(Color.BLACK, date.getTime(), line2);
                binding.compactcalendarView.addEvent(ev);
            }

        } catch (FileNotFoundException e) {
            Toast.makeText(Schedule.this, e.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(Schedule.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteEvent(String date) {
        List<Event> events = new ArrayList<>();
        File path = getApplicationContext().getFilesDir();
        String line1,line2;
        BufferedReader in = null;
        // load all date and event to map
        try {
            in = new BufferedReader(new FileReader(new File(path, "event.txt")));

            while ((line1 = in.readLine()) != null && ((line2 = in.readLine()) != null)) {
                if (line1.equals(date) == false){
                    Date d = simpleDatev2.parse(line1);
                    Event ev = new Event(Color.BLACK, d.getTime(), line2);
                    events.add(ev);
                }

            }
        } catch (FileNotFoundException e) {
                Toast.makeText(Schedule.this, e.toString(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(Schedule.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        // rewrite data back to file
        // empty file first
        File file = new File(path,"event.txt");
        try {
            FileWriter writer = new FileWriter(file, false);
            writer.append("");
            writer.close();
        } catch (IOException e) {
            Toast.makeText(Schedule.this, e.toString(), Toast.LENGTH_SHORT).show();
        }


        for (Event e : events){
            Date d = new Date(e.getTimeInMillis());
            writeEventToFile(simpleDatev2.format(d),e.getData().toString());
        }
    }

    public void deleteAll(){
        File path = getApplicationContext().getFilesDir();
        File file = new File(path,"event.txt");
        try {
            FileWriter writer = new FileWriter(file, false);
            writer.close();
        } catch (IOException e) {
        }
    }

    @Override
    public void onBackPressed(){
        // startActivity(new Intent(Schedule.this, MainActivity2.class));
        finish();
    }
}