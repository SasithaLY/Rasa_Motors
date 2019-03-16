package com.example.sasitha.rasa_motors;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sasitha.rasa_motors.customers.Customers;
import com.example.sasitha.rasa_motors.customers.viewCustomer;
import com.example.sasitha.rasa_motors.pkg_vehicles.HomePage;
import com.example.sasitha.rasa_motors.pkg_vehicles.vehicles;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private Button mSignUp;
    private Button mSignIn;

    private EditText email, password;
    private ProgressBar progressBar;

    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.editTextMail);
        password = (EditText) findViewById(R.id.editTextPass);

        mSignUp = (Button) findViewById(R.id.btnSignUp);
        mSignIn = (Button) findViewById(R.id.btnSignIn) ;

        mSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isEmpty()){
                    return;
                }else {
                    //inProgress(true);
                    //openCustomer();
                    auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Login.this, getString(R.string.regSuccess), Toast.LENGTH_LONG).show();
                           // inProgress(false);
                            openCustomer();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //inProgress(false);
                            Toast.makeText(Login.this, getString(R.string.regFailed)+e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });



                }


            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(isEmpty()){
                    return;
                }else {
                    //inProgress(true);
                    //openHome();
                    auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Login.this, getString(R.string.signedIn), Toast.LENGTH_LONG).show();
                            openHome();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //inProgress(false);

                            Toast.makeText(Login.this, getString(R.string.signFail)+e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
                }



            }
        });
    }

    private void inProgress(boolean x){
        if(x){
            progressBar.setVisibility(View.VISIBLE);
            mSignIn.setEnabled(false);
            mSignUp.setEnabled(false);
        }else{
            progressBar.setVisibility(View.GONE);
            mSignIn.setEnabled(true);
            mSignUp.setEnabled(true);
        }
    }

    private boolean isEmpty(){
        if(TextUtils.isEmpty(email.getText().toString())){
            email.setError(getString(R.string.required));
            return true;
        }
        if(TextUtils.isEmpty(password.getText().toString())){
            password.setError(getString(R.string.required));
            return true;
        }
        return false;
    }

    public void openCustomer(){
        Intent intent = new Intent(this, Customers.class);
        startActivity(intent);
    }

    public void openHome(){
        Intent intent = new Intent(this, HomePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish(); return;
    }

    public void openLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
