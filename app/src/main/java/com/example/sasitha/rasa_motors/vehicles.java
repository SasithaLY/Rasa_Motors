package com.example.sasitha.rasa_motors;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class vehicles extends AppCompatActivity {

    Button btnAddVehicle;

    private RecyclerView vRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);

        vRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewVehicles);
        new FirebaseHelper().readVehicles(new FirebaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Vehicle> vehicles, List<String> keys) {
                new RecyclerViewConfig().setConfig(vRecyclerView, vehicles.this, vehicles, keys);
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
