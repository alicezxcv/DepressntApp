package com.example.app;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Chat extends AppCompatActivity {

    private ImageButton btn_send_msg;
    private ImageButton backBtn;
    private EditText input_msg;
    private ListView chat_conversation;

    private String user_name,room_name;
    private DatabaseReference root;
    private String tmp_key;

    private TextView room_name_header;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(Chat.this, android.R.layout.simple_list_item_1, arrayList);

        btn_send_msg = (ImageButton) findViewById(R.id.btn_send);
        input_msg = (EditText) findViewById(R.id.msg_input);
        chat_conversation = (ListView) findViewById(R.id.messagesContainer);
        backBtn = (ImageButton) findViewById(R.id.back);
        room_name_header = findViewById(R.id.header);

        // Here, you set the data in your ListView
        chat_conversation.setAdapter(adapter);


        user_name = getIntent().getExtras().get("user_name").toString();
        room_name =getIntent().getExtras().get("room_name").toString();
        setTitle(" Room - " + room_name);
        room_name_header.setText(room_name);

        root = FirebaseDatabase.getInstance().getReference().child("Room").child(room_name);

        btn_send_msg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(input_msg.getText().toString().isEmpty() || input_msg.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(Chat.this, "Please enter something", Toast.LENGTH_SHORT).show();
                }
                else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    tmp_key = root.push().getKey();
                    root.updateChildren(map);

                    DatabaseReference message_root = root.child(tmp_key);
                    Map<String, Object> map2 = new HashMap<String, Object>();
                    map2.put("name", user_name);
                    map2.put("msg", input_msg.getText().toString());
                    input_msg.getText().clear();
                    message_root.updateChildren(map2);
                }
            }
        });


        root.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s){
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s){
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),Room.class);
                intent.putExtra("user_name",user_name);
                finish();
            }
        });
    }
    private String chat_msg, chat_user_name;
    private void append_chat_conversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()){
            chat_msg= (String) ((DataSnapshot)i.next()).getValue();
            chat_user_name= (String) ((DataSnapshot)i.next()).getValue();

            arrayList.add("@" + chat_user_name + "\n" +chat_msg);
            adapter.notifyDataSetChanged();
            //adapter.add(chat_user_name+ " : "+chat_msg);
            //chat_conversation.(chat_user_name+ " : "+chat_msg+"\n");
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent= new Intent(getApplicationContext(),Room.class);
        intent.putExtra("user_name",user_name);
        startActivity(intent);
    }
}