package com.example.sasitha.rasa_motors.Appointments;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.example.sasitha.rasa_motors.pkg_vehicles.Vehicle;
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

public class FireBaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceAppointments;

    FirebaseAuth auth;
    private static FirebaseUser user;

    private List<AppointmentNew> appointments = new ArrayList<>();

    //create an interface

    public interface DataStatus{

        void DataIsLoaded(List<AppointmentNew> appointmentNews, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FireBaseHelper() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceAppointments = mDatabase.getReference("appointments");
    }

    public void readAppointment(final DataStatus dataStatus){


        mReferenceAppointments.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                appointments.clear();
                List<String> keys = new ArrayList<>();

                String userId = auth.getUid();
                for( DataSnapshot keyNode : dataSnapshot.getChildren()){

                    if(keyNode.getKey().equals(userId)) {
                        for (DataSnapshot appNode : keyNode.getChildren()) {
                            keys.add(appNode.getKey());
                            AppointmentNew appointmentNew = appNode.getValue(AppointmentNew.class);
                            appointments.add(appointmentNew);
                        }
                    }
//                    keys.add(keyNode.getKey());
//                    AppointmentNew appointmentNew = keyNode.getValue(AppointmentNew.class);
//                    appointments.add(appointmentNew);
                }

                dataStatus.DataIsLoaded(appointments, keys);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }

    public void addAppointment(AppointmentNew appointments,final DataStatus dataStatus){
        String userId = auth.getUid();
        String key = mReferenceAppointments.push().getKey();
        mReferenceAppointments.child(userId).child(key).setValue(appointments).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });

    }

    public void updateAppointment(String key,AppointmentNew appointment ,final DataStatus dataStatus){
        String userId = auth.getUid();
        mReferenceAppointments.child(userId).child(key).setValue(appointment)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });

    }
    public void deleteAppointment(String key,final DataStatus dataStatus){
        String userId = auth.getUid();
        mReferenceAppointments.child(userId).child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
