package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Room extends AppCompatActivity {

    private ImageButton add_room;
    private ImageButton back;
    private String room_name;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_rooms = new ArrayList<>();
    private String name;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Room");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        add_room = (ImageButton) findViewById(R.id.btn_add_room);
        listView = (ListView) findViewById(R.id.listView);
        back = (ImageButton) findViewById(R.id.back);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_of_rooms);
        listView.setAdapter(arrayAdapter);

        try {
            name = getIntent().getExtras().get("user_name").toString();
        }
        catch (Exception e){
            Toast.makeText(Room.this, "Join chat using google account", Toast.LENGTH_SHORT).show();
            name = "";
        }
        if (name.equals("") == true){
            request_user_name();
        }


        add_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    room_name = getIntent().getExtras().get("room_name").toString();
                }
                catch (Exception e){
                    Toast.makeText(Room.this, "Make you own room", Toast.LENGTH_SHORT).show();
                    room_name = "";
                }
                if (room_name.equals("") == true){
                    request_room_name();
                }

            }
        });

        root.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){

                Set<String> set= new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                list_of_rooms.clear();
                list_of_rooms.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getApplicationContext(),Chat.class);
                intent.putExtra("room_name",((TextView)view).getText().toString());
                intent.putExtra("user_name",name);
                startActivity(intent);
                finish(); // prevent duplicate activity
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Room.this, MainActivity.class));
                finish();
            }
        });
    }

    private void request_room_name() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Room.this);
        builder.setCancelable(true);
        builder.setTitle("Enter room name");
        final EditText input_field = new EditText(this);
        builder.setView(input_field);

        builder.setPositiveButton("Ok", null);

        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.show();

        Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                room_name = input_field.getText().toString();
                if (isValidName(room_name)){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(room_name, "");
                    root.updateChildren(map);
                    dialog.dismiss();
                }
                else{
                    Toast.makeText(Room.this, "Invalid room name!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button negative = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    private void request_user_name() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Room.this);
        builder.setCancelable(false);
        builder.setTitle("Enter name:");
        final EditText input_field = new EditText(this);
        builder.setView(input_field);

        builder.setPositiveButton("Ok", null);

        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.show();

        Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = input_field.getText().toString();
                if (isValidName(name)){
                    dialog.dismiss();
                }
                else{
                    Toast.makeText(Room.this, "Invalid username!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button negative = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private boolean isValidName(String name){
        if (name.isEmpty()|| name.trim().isEmpty() || name.toLowerCase().equals("admin")){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        //startActivity(new Intent(Room.this, MainActivity.class));
        finish();
    }
}
