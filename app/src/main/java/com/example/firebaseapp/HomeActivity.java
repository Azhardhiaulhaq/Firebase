package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    /* Atribut TextView & EditText */
    private TextView testTV;
    /* Atribut Button */
    private Button SignoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String email = intent.getStringExtra("Email");
        testTV = (TextView) findViewById(R.id.Test2TV);
        testTV.setText("Welcome, " + email);

        SignoutBtn = (Button) findViewById(R.id.SignOutBtn);
        SignoutBtn.setOnClickListener(this);


    }

        /* SignOut Method*/
    private void SignOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent2);
    }

    /* Override onClick*/
    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.SignOutBtn){
            SignOut();
        }
    }

}
