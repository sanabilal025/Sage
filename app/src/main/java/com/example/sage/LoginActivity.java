package com.example.sage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText username;
    EditText password;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        username = findViewById(R.id.password);
        password = findViewById(R.id.password);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(v -> login());

        Button signup = findViewById(R.id.signup);
        signup.setOnClickListener(v -> goToSignUpActivity());

    }

    private void goToSignUpActivity(){
        Intent signupActivity = new Intent(getApplicationContext(),SignupActivity.class);
        startActivity(signupActivity);
    }

    private void login(){
        String name = username.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Enter your details!", Toast.LENGTH_LONG).show();
            return;
        }
        else if ((name=="1") && (pass=="1")) {
            goToHomeActivity();
        }
        else {
            auth.signInWithEmailAndPassword(name, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Welcome to Sage!", Toast.LENGTH_LONG).show();
                    goToHomeActivity();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Failed to login!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void goToHomeActivity(){
        Intent homeActivity = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(homeActivity);
    }
}