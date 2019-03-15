package com.example.sasitha.rasa_motors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sasitha.rasa_motors.customers.Customers;
import com.example.sasitha.rasa_motors.customers.viewCustomer;
import com.example.sasitha.rasa_motors.pkg_vehicles.HomePage;
import com.example.sasitha.rasa_motors.pkg_vehicles.vehicles;

public class Login extends AppCompatActivity {

    Button mSignUp;
    Button mSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSignUp = (Button) findViewById(R.id.btnSignUp);
        mSignIn = (Button) findViewById(R.id.btnSignIn) ;

        mSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               openCustomer();
            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openHome();
            }
        });
    }

    public void openCustomer(){
        Intent intent = new Intent(this, Customers.class);
        startActivity(intent);
    }

    public void openHome(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}
