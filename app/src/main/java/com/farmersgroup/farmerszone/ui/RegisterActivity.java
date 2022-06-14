package com.farmersgroup.farmerszone.ui;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.farmersgroup.farmerszone.R;
import com.farmersgroup.farmerszone.models.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.createUserButton) Button mCreateUserButton;
    @BindView(R.id.nameEditText) EditText mNameEditText;
    @BindView(R.id.emailEditText) EditText mEmailEditText;
    @BindView(R.id.passwordEditText) EditText mPasswordEditText;
    @BindView(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @BindView(R.id.progressBarRegister) ProgressBar progressBar;
    @BindView(R.id.loginTextView) TextView mLoginTextView;

    private FirebaseAuth mAuth; //    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener; //    private DatabaseReference databaseReference;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference("users");
        DatabaseReference userRef = firebaseDatabase.getReference("users");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };

        mCreateUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            } //call registerUser method on click of the create user button

        });
        mLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    private void registerUser(){
        String name = mNameEditText.getText().toString().trim();
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        if (name.isEmpty()){
            mNameEditText.setError("Full Name is required");
            mNameEditText.requestFocus();
            return;
        }
        if (password.isEmpty()){
            mPasswordEditText.setError("Password is required");
            mPasswordEditText.requestFocus();
            return;
        }
        if (confirmPassword.isEmpty()){
            mConfirmPasswordEditText.setError("Password is required");
            mConfirmPasswordEditText.requestFocus();
            return;
        }
        if (email.isEmpty()){
            mEmailEditText.setError("Email is required");
            mEmailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmailEditText.setError("Please provide Valid email");
            mEmailEditText.requestFocus();
            return;
        }
        if (!password.equals(confirmPassword)){
            mConfirmPasswordEditText.setError("Password does not match");
            mConfirmPasswordEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registration error. Please confirm your details.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userid=firebaseUser.getUid();
                            Toast.makeText(RegisterActivity.this, "Registration Complete!",
                                    Toast.LENGTH_SHORT).show();
                            User account = new User(userid, name, email);
                            String user_Id = mAuth.getCurrentUser().getUid();

                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference().child("users");
                            databaseReference.child(user_Id).setValue(account);

                            Log.d(TAG, "Value is: " + userid + name + email);
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });
    }
}