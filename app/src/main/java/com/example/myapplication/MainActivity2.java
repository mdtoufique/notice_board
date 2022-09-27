package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    EditText name,pass,email,dept,ser,roll;
    EditText rol;
    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootref = database.getReference();
    DatabaseReference usrref = rootref.child("users");
    DatabaseReference main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name= findViewById(R.id.Name);
        email= findViewById(R.id.Email);
        pass= findViewById(R.id.password);
        dept= findViewById(R.id.dept);
        ser= findViewById(R.id.series);
        rol=findViewById(R.id.role);
        roll=findViewById(R.id.roll);
        mAuth = FirebaseAuth.getInstance();
        //mUser = mAuth.getCurrentUser();
    }

    public void goToSignIn(View view) {
        Intent intent = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(intent);
    }

    public void check(View view) {
        String mail= email.getText().toString();
        String pas= pass.getText().toString();

        if (mail.matches("") || pas.matches("")  ) {
            Toast.makeText(this, "You did not enter a email or password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(mail, pas)
                .addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            mAuth = FirebaseAuth.getInstance();

                            FirebaseUser user = mAuth.getCurrentUser();
                            String id= user.getUid();
                            main=usrref.child(id);

                            main.child("name").setValue(name.getText().toString());
                            main.child("roll").setValue(roll.getText().toString());
                            main.child("role").setValue(rol.getText().toString());
                            main.child("series").setValue(ser.getText().toString());
                            main.child("dept").setValue(dept.getText().toString());
                            Toast.makeText(MainActivity2.this, "Sign Up Complete", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }) .addOnFailureListener(MainActivity2.this , new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }
}