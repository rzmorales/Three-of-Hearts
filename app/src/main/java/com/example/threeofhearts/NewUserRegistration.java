package com.example.threeofhearts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewUserRegistration extends AppCompatActivity {

    private static final String TAG = "FINDME";
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_new_user);
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void createNewAccount(String username, String password){
        firebaseAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             if (task.isSuccessful()){
                 FirebaseUser user = firebaseAuth.getCurrentUser();
                 updateUI(user);
             } else {
                 Toast.makeText(getApplicationContext(), "Authentication failed.",
                         Toast.LENGTH_SHORT).show();
                 updateUI(null);
             }


            }
        });


    }


    private void updateUI(FirebaseUser user)
    {
        if (user == null)
        {
            Log.d(TAG,"user is null");
        }
        else
        {
            setContentView(R.layout.activity_main);
        }
    }

}
