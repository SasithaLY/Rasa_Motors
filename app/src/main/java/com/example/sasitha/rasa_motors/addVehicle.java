package com.example.sasitha.rasa_motors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addVehicle extends AppCompatActivity {

    Button btnAdd;
    EditText vehModel;
    EditText vehType;
    EditText vehNumber;
    EditText vehColor;

    DatabaseReference vehicleDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        vehicleDatabase = FirebaseDatabase.getInstance().getReference("vehicles");

        vehModel = (EditText) findViewById(R.id.txtVehicleModel);
        vehType = (EditText) findViewById(R.id.txtVehicleType);
        vehNumber = (EditText) findViewById(R.id.txtVehicleNo);
        vehColor = (EditText) findViewById(R.id.txtVehicleColor);

        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVehicle();
            }
        });
    }

    public void addVehicle(){
        String model = vehModel.getText().toString().trim();
        String type = vehType.getText().toString().trim();
        String number = vehNumber.getText().toString().trim();
        String color = vehColor.getText().toString().trim();

        if(TextUtils.isEmpty(model)){
            Toast.makeText(this, this.getResources().getString(R.string.modelNull), Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(type)){
            Toast.makeText(this, this.getResources().getString(R.string.typeNull), Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(number)){
            Toast.makeText(this, this.getResources().getString(R.string.numberNull), Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(color)){
            Toast.makeText(this, this.getResources().getString(R.string.colorNull), Toast.LENGTH_SHORT).show();
        }else{
            String vehicleID =  vehicleDatabase.push().getKey();

            Vehicle vehicle = new Vehicle(model, type, number, color);

            vehicleDatabase.child(vehicleID).setValue(vehicle);

            Toast.makeText(this, this.getResources().getString(R.string.vehSuccess), Toast.LENGTH_SHORT).show();
        }
    }
}
