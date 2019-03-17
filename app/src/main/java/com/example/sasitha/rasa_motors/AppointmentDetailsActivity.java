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

public class AppointmentDetailsActivity extends AppCompatActivity {

    private EditText mVehicle_edittxt;
    private EditText mJob_edittxt;
    private EditText mDate_edittxt;

    private Button mUpdate_btn;
    private Button mdelete_btn;
    private Button mBack_btn;

    private String key;
    private String vehicle;
    private  String job;
    private String date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);

        key = getIntent().getStringExtra("key");
        vehicle = getIntent().getStringExtra("vehicle");
        job = getIntent().getStringExtra("job");
        date = getIntent().getStringExtra("date");

        mVehicle_edittxt = (EditText) findViewById(R.id.vehicle_txt);
        mVehicle_edittxt.setText(vehicle);
        mJob_edittxt = (EditText) findViewById(R.id.job_txt);
        mJob_edittxt.setText(job);
        mDate_edittxt = (EditText) findViewById(R.id.date_txt);
        mDate_edittxt.setText(date);

        mUpdate_btn = (Button)findViewById(R.id.btn_Update);
        mdelete_btn = (Button) findViewById(R.id.btn_Delete);
        mBack_btn = (Button) findViewById(R.id.btn_Back);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppointmentNew appointmentNew = new AppointmentNew();

                appointmentNew.setVehicle(mVehicle_edittxt.getText().toString());
                appointmentNew.setJob(mJob_edittxt.getText().toString());
                appointmentNew.setDate(mDate_edittxt.getText().toString());

                new FireBaseHelper().updateAppointment(key, appointmentNew, new FireBaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<AppointmentNew> appointmentNews, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                        Toast.makeText(AppointmentDetailsActivity.this,"Appointment record has been "+
                        " updated successfully",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        mdelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FireBaseHelper().deleteAppointment(key, new FireBaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<AppointmentNew> appointmentNews, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(AppointmentDetailsActivity.this, "Appointment has been"+
                        " deleted successfully",Toast.LENGTH_LONG).show();
                        finish();
                        return;

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
