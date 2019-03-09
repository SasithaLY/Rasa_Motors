package com.example.sasitha.rasa_motors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button btnVehicle, btnCustomers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnVehicle = (Button) findViewById(R.id.btnVehicles);
        btnCustomers = (Button) findViewById(R.id.btnCustomers);

        btnVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddVehicle();
            }
        });

        btnCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomers();
            }
        });
    }

    public void openAddVehicle(){
        Intent intent = new Intent(this, vehicles.class);
        startActivity(intent);
    }

    public void openCustomers(){
        Intent intent = new Intent(this, Customers.class);
        startActivity(intent);
    }
}
