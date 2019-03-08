package com.example.sasitha.rasa_motors;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class vehicles extends AppCompatActivity {

    Button btnAddVehicle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);


        btnAddVehicle = (Button) findViewById(R.id.btnAddVehicle);


        btnAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddVehicle();
            }
        });



    }



    public void openAddVehicle(){
        Intent intent = new Intent(this, addVehicle.class);
        startActivity(intent);
    }


}
