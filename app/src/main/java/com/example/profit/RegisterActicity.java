package com.example.profit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActicity extends AppCompatActivity {
FirebaseAuth auth;
EditText emailText, passwordText;
Button SignUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_acticity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.editTextTextEmailAddress2);
        passwordText = findViewById(R.id.editTextTextPassword2);
        SignUpBtn = findViewById(R.id.button3);
        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });

    }
    //method to register the user to firebase Authentication
    private void registerNewUser(){
        String email = emailText.getText().toString().trim();
        String pass = passwordText.getText().toString().trim();
        if(email.isEmpty()||pass.isEmpty()){
            Toast.makeText(this, "please fill all credentials", Toast.LENGTH_SHORT).show();
        }
        else{
         Task<AuthResult> result = auth.createUserWithEmailAndPassword(email,pass);
         result.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     Toast.makeText(RegisterActicity.this, "Login successful", Toast.LENGTH_SHORT).show();
                     Intent i = new Intent(RegisterActicity.this,HomePage.class);
                     startActivity(i);
                     finish();
                 }
                 else{
                     Toast.makeText(RegisterActicity.this, "Login failed please try again", Toast.LENGTH_SHORT).show();
                 }
             }
         });
        }
    }
}