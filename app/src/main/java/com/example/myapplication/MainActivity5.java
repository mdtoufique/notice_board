package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rf = database.getReference();
    DatabaseReference nor = rf.child("notices");
    DatabaseReference main;
    ListView l;
    ArrayList<Info> al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);
        l= findViewById(R.id.lv);
        al= new ArrayList<>();
        nor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                al.clear();
                for(DataSnapshot mSnapshot: dataSnapshot.getChildren())
                {


                        Info a= mSnapshot.getValue(Info.class);
                        al.add(a);
                        Pst np=new Pst(MainActivity5.this,al);
                        l.setAdapter(np);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}