package com.example.sasitha.rasa_motors.pkg_vehicles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sasitha.rasa_motors.R;

import java.util.List;

public class VehicleDetails extends AppCompatActivity {

    private EditText vehModel;
    private EditText vehType;
    private EditText vehNumber;
    private EditText vehColor;

    private Button btnUpdate, btnDelete, btnBack;

    private String key;
    private String model, type, number, color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);

        key = getIntent().getStringExtra("key");
        model = getIntent().getStringExtra("model");
        type = getIntent().getStringExtra("type");
        number = getIntent().getStringExtra("number");
        color = getIntent().getStringExtra("color");


        vehModel = (EditText) findViewById(R.id.txtVehicleModel);
        vehModel.setText(model);
        vehType = (EditText) findViewById(R.id.txtVehicleType);
        vehType.setText(type);
        vehNumber = (EditText) findViewById(R.id.txtVehicleNo);
        vehNumber.setText(number);
        vehColor = (EditText) findViewById(R.id.txtVehicleColor);
        vehColor.setText(color);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vehicle vehicle = new Vehicle();

                String model = vehModel.getText().toString();
                String type = vehType.getText().toString();
                String number = vehNumber.getText().toString();
                String color = vehColor.getText().toString();

                if(TextUtils.isEmpty(model)){
                    Toast.makeText(VehicleDetails.this, getString(R.string.modelNull), Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(type)){
                    Toast.makeText(VehicleDetails.this, getString(R.string.typeNull), Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(number)){
                    Toast.makeText(VehicleDetails.this, getString(R.string.numberNull), Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(color)){
                    Toast.makeText(VehicleDetails.this, getString(R.string.colorNull), Toast.LENGTH_SHORT).show();
                }else{
                    vehicle.setVehModel(model);
                    vehicle.setVehType(type);
                    vehicle.setVehNumber(number);
                    vehicle.setVehColor(color);

                    new FirebaseHelper().updateVehicle(key, vehicle, new FirebaseHelper.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<Vehicle> vehicles, List<String> keys) {

                        }

                        @Override
                        public void DataIsInserted() {

                        }

                        @Override
                        public void DataIsUpdated() {

                            Toast.makeText(VehicleDetails.this, getString(R.string.updateSuccess), Toast.LENGTH_LONG).show();
                            finish(); return;

                        }

                        @Override
                        public void DataIsDeleted() {

                        }
                    });
                }


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseHelper().deleteVehicle(key, new FirebaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Vehicle> vehicles, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(VehicleDetails.this, getString(R.string.deleteSuccess), Toast.LENGTH_LONG).show();
                        finish();
                        return;

                    }
                });
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
