package com.example.sasitha.rasa_motors.Appointments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.sasitha.rasa_motors.AddNewAppointment;
import com.example.sasitha.rasa_motors.R;

import java.util.List;

public class Appointments extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button btnAddAppoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        btnAddAppoint = (Button) findViewById(R.id.btnAddAppoint);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_appointment);

        btnAddAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddAppointments();
            }
        });

        new FireBaseHelper().readAppointment(new FireBaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<AppointmentNew> appointmentNews, List<String> keys) {
                findViewById(R.id.loading_appointmnetProgressBar).setVisibility(View.GONE);
                new RecylerView_Appointment().setConfig(mRecyclerView,Appointments.this,appointmentNews,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.appointment_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_Appointment_menu:
                startActivity(new Intent(this,AddNewAppointment.class));
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openAddAppointments(){
        Intent intent = new Intent(this, AddNewAppointment.class);
        startActivity(intent);
    }
}
