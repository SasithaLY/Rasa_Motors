package com.example.sasitha.rasa_motors.pkg_vehicles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.sasitha.rasa_motors.Appointments.Appointments;
import com.example.sasitha.rasa_motors.Login;
import com.example.sasitha.rasa_motors.Quotation.MainActivity;
import com.example.sasitha.rasa_motors.R;
import com.example.sasitha.rasa_motors.customers.viewCustomer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {

    private Button btnVehicle, btnCustomers, btnAppointments, btnQuotation;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        auth = FirebaseAuth.getInstance();

        btnVehicle = (Button) findViewById(R.id.btnVehicles);
        btnCustomers = (Button) findViewById(R.id.btnCustomers);
        btnAppointments = (Button) findViewById(R.id.btnAppointments);
        btnQuotation = (Button) findViewById(R.id.btnQuotation);

        btnAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppointments();
            }
        });

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

        btnQuotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuotation();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        FirebaseUser user = auth.getCurrentUser();
        getMenuInflater().inflate(R.menu.rasa_menu, menu);

        if(user != null){
            menu.getItem(0).setVisible(true);
        }else {
            menu.getItem(0).setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        FirebaseUser user = auth.getCurrentUser();

        if(user != null){
            menu.getItem(0).setVisible(true);
        }else {
            menu.getItem(0).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                auth.signOut();
                invalidateOptionsMenu();
                RecyclerViewConfig.logout();
                openLogin();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openAddVehicle(){
        Intent intent = new Intent(this, vehicles.class);
        startActivity(intent);
    }

    public void openCustomers(){
        Intent intent = new Intent(this, viewCustomer.class);
        startActivity(intent);
    }

    public void openLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void openAppointments(){
        Intent intent = new Intent(this, Appointments.class);
        startActivity(intent);
    }

    public void openQuotation(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
