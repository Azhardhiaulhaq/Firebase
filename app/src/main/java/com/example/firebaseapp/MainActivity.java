package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /* Atribut EditTet & TextView*/
        private EditText EmailET;
        private EditText PasswordET;
        private TextView TestTV;
    /* Atribut Button */
        private Button LoginBtn;
        private Button SignupBtn;
    /* Atribut Firebase */
        public FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Inisialisasi Atribut*/
        EmailET = (EditText) findViewById(R.id.EmailET);
        PasswordET = (EditText) findViewById(R.id.PasswordET);
        TestTV = (TextView) findViewById(R.id.TestTV);
        LoginBtn = (Button) findViewById(R.id.LoginBtn);
        SignupBtn = (Button) findViewById(R.id.SignUpBtn);


        LoginBtn.setOnClickListener(this);
        SignupBtn.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }


    /* Override onClick */
    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.LoginBtn){
            SignIn(EmailET.getText().toString(),PasswordET.getText().toString());
        } else if (i == R.id.SignUpBtn){
            SignUp();
        }
    }

    //Function for SIgn In
    private void SignIn(String email, String password){
        Log.d("emailpassword", "signIn:" + email);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //Sign in success
                    Log.d("emailpassword","createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("createUserWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
        }});
    }
    //Function for Sign Up
    private void SignUp(){
        Intent intent2 = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent2);
    }

    @Override
    public void onStart(){
        super.onStart();
        //Check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI(FirebaseUser user){
        if (user != null){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            intent.putExtra("Email",EmailET.getText().toString().trim());
            startActivity(intent);
        } else {
            TestTV.setText("User Belum Diverified");
        }
    }


}
