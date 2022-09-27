package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootref = database.getReference();
    DatabaseReference notref = rootref.child("notices");
    DatabaseReference main;
    String name,mail,time,not;
    EditText post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mAuth = FirebaseAuth.getInstance();
        post=findViewById(R.id.notice);
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){

            mail=currentUser.getEmail();

        }
        else
        {
            Intent intent = new Intent(MainActivity3.this, MainActivity.class);
            startActivity(intent);
        }



    }

    public void post(View view) {

        not=post.getText().toString();
        if (!not.matches("") ) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            time=dtf.format(now);


            String id = notref.push().getKey();
            main = notref.child(id);

            main.child("time").setValue(time);
            main.child("mail").setValue(mail);
            main.child("chk").setValue(not);
            Toast.makeText(MainActivity3.this,"Notice Posted", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
        startActivity(intent);

    }
}