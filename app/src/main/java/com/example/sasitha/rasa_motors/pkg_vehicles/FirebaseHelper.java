package com.example.sasitha.rasa_motors.pkg_vehicles;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {

    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    FirebaseAuth auth;
    private static FirebaseUser user;

    public interface DataStatus{

        void DataIsLoaded(List<Vehicle> vehicles, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseHelper() {
        this.auth = FirebaseAuth.getInstance();
        this.user = auth.getCurrentUser();
        this.db = FirebaseDatabase.getInstance();
        this.dbRef = db.getReference("vehicles");

    }

    public void addVehicle(Vehicle  vehicle, final DataStatus dataStatus){

        String userId = auth.getUid();
        String key = dbRef.push().getKey();
        dbRef.child(userId).child(key).setValue(vehicle).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }

    public void readVehicles(final DataStatus dataStatus){
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vehicles.clear();
                List<String> keys = new ArrayList<>();
                String userId = auth.getUid();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){

                    if(keyNode.getKey().equals(userId)) {
                        for (DataSnapshot vehNode : keyNode.getChildren()) {
                            keys.add(vehNode.getKey());

                            Vehicle vehicle = vehNode.getValue(Vehicle.class);
                            vehicles.add(vehicle);
                        }
                    }
//                    keys.add(keyNode.getKey());
//
//                    Vehicle vehicle = keyNode.getValue(Vehicle.class);
//                    vehicles.add(vehicle);
                }
                dataStatus.DataIsLoaded(vehicles, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateVehicle(String key, Vehicle vehicle, final DataStatus dataStatus){
        dbRef.child(key).setValue(vehicle).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });

    }

    public void deleteVehicle(String key, final DataStatus dataStatus){
        dbRef.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }


}



