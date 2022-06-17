package com.example.sage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText username;
    EditText password;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        username = findViewById(R.id.password);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signin);

        signin.setOnClickListener(v -> register());
    }

    private void register(){
        String name = username.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Enter your details!", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            auth.createUserWithEmailAndPassword(name, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Welcome to Sage!", Toast.LENGTH_LONG).show();
                    goToLoginActivity();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Failed to register!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void goToLoginActivity(){
        Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginActivity);
    }
}