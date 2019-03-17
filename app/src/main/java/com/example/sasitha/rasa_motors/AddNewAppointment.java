package com.example.sasitha.rasa_motors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sasitha.rasa_motors.Appointments.AppointmentNew;
import com.example.sasitha.rasa_motors.Appointments.FireBaseHelper;

import java.util.List;

public class AddNewAppointment extends AppCompatActivity {

    private EditText mVehicle_edittxt;
    private EditText mJob_edittxt;
    private EditText mDate_edittxt;

    private Button mAdd_btn;
    private Button mBack_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_appointment);

        mVehicle_edittxt = (EditText) findViewById(R.id.vehicle_txt);
        mJob_edittxt = (EditText) findViewById(R.id.job_txt);
        mDate_edittxt = (EditText) findViewById(R.id.date_txt);

        mAdd_btn =(Button) findViewById(R.id.btn_Update);
        mBack_btn = (Button) findViewById(R.id.btn_Back);

        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppointmentNew appointment = new AppointmentNew();
                appointment.setVehicle(mVehicle_edittxt.getText().toString());
                appointment.setJob(mJob_edittxt.getText().toString());
                appointment.setDate(mDate_edittxt.getText().toString());


                new FireBaseHelper().addAppointment(appointment, new FireBaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<AppointmentNew> appointmentNews, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                        Toast.makeText(AddNewAppointment.this,"The Appointment has"+
                         "been inserted successfully",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });


            }
        });
        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });
    }
}
