package com.example.sasitha.rasa_motors.Quotation;
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

public class FireBaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceQuotations;

    FirebaseAuth auth;
    private static FirebaseUser user;
    private List<Quotations> quotationsList = new ArrayList<> ();

    public interface DataStatus{
        void DataIsLoaded(List<Quotations> quotations, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public FireBaseDatabaseHelper() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceQuotations = mDatabase.getReference("Quotation") ;
    }

    public void showInvoices (final DataStatus dataStatus){
        mReferenceQuotations.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            quotationsList.clear();
            List<String> keys = new ArrayList<>();
            String userId = auth.getUid();
            for(DataSnapshot key_Node : dataSnapshot.getChildren()){
                if(key_Node.getKey().equals(userId)) {
                    for (DataSnapshot qNode : key_Node.getChildren()) {
                        keys.add(qNode.getKey());
                        Quotations quotations = qNode.getValue(Quotations.class);
                        quotationsList.add(quotations);
                    }
                }
//               keys.add(key_Node.getKey());
//               Quotations quotations = key_Node.getValue(Quotations.class);
//                quotationsList.add(quotations);

            }
            dataStatus.DataIsLoaded(quotationsList,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } ) ;
    }
 public void AddQuotation(Quotations quotations, final DataStatus dataStatus){
         String userId = auth.getUid();
         String key = mReferenceQuotations.push().getKey();
         mReferenceQuotations.child(userId).child(key).setValue(quotations)
                 .addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                     }
                 });
 }

 public void updateQuotation(String key, Quotations quotations, final DataStatus dataStatus){
        String userId = auth.getUid();
        mReferenceQuotations.child(userId).child(key).setValue(quotations)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });

 }
 public void deleteQuotation(String key, final DataStatus dataStatus){
        String userId = auth.getUid();
        mReferenceQuotations.child(userId).child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });

 }

}
