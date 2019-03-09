package com.example.sasitha.rasa_motors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class addVehicle extends AppCompatActivity {

    Button btnAdd, btnBack;
    EditText vehModel;
    EditText vehType;
    EditText vehNumber;
    EditText vehColor;

    DatabaseReference vehicleDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

//        vehicleDatabase = FirebaseDatabase.getInstance().getReference("vehicles");

        vehModel = (EditText) findViewById(R.id.txtVehicleModel);
        vehType = (EditText) findViewById(R.id.txtVehicleType);
        vehNumber = (EditText) findViewById(R.id.txtVehicleNo);
        vehColor = (EditText) findViewById(R.id.txtVehicleColor);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBack2);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vehicle vehicle = new Vehicle();

                String model = vehModel.getText().toString();
                String type = vehType.getText().toString();
                String number = vehNumber.getText().toString();
                String color = vehColor.getText().toString();

                if(TextUtils.isEmpty(model)){
                    Toast.makeText(addVehicle.this, getString(R.string.modelNull), Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(type)){
                    Toast.makeText(addVehicle.this, getString(R.string.typeNull), Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(number)){
                    Toast.makeText(addVehicle.this, getString(R.string.numberNull), Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(color)){
                    Toast.makeText(addVehicle.this, getString(R.string.colorNull), Toast.LENGTH_SHORT).show();
                }else{
                    vehicle.setVehModel(model);
                    vehicle.setVehType(type);
                    vehicle.setVehNumber(number);
                    vehicle.setVehColor(color);

                    new FirebaseHelper().addVehicle(vehicle, new FirebaseHelper.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<Vehicle> vehicles, List<String> keys) {

                        }

                        @Override
                        public void DataIsInserted() {
                            Toast.makeText(addVehicle.this, getString(R.string.vehSuccess), Toast.LENGTH_LONG).show();
                            finish(); return;
                        }

                        @Override
                        public void DataIsUpdated() {

                        }

                        @Override
                        public void DataIsDeleted() {

                        }
                    });
                }

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); return;
            }
        });
    }

}
