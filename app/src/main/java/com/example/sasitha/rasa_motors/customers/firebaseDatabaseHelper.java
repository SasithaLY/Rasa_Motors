package com.example.sasitha.rasa_motors.customers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class firebaseDatabaseHelper
{
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceCustomers;
    private List<customer> customers = new ArrayList<>();

    public interface DataStatus
    {
        void DataIsLoaded(List<customer> customers, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public firebaseDatabaseHelper()
    {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceCustomers = mDatabase.getReference("customers");
    }

    public void viewCustomers(final DataStatus dataStatus)
    {
        mReferenceCustomers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                customers.clear();
                List<String> keys = new ArrayList<>();

                for(DataSnapshot keyNode : dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    customer cust = keyNode.getValue(customer.class);
                    customers.add(cust);
                }
                dataStatus.DataIsLoaded(customers, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addCustomer(customer cust, final DataStatus dataStatus)
    {
        String key = mReferenceCustomers.push().getKey();
        mReferenceCustomers.child(key).setValue(cust).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                dataStatus.DataIsInserted();
            }
        });
    }

    public void updateCustomer(String key, customer cust, final DataStatus dataStatus)
    {
        mReferenceCustomers.child(key).setValue(cust).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deleteCustomer(String key, final DataStatus dataStatus)
    {
        mReferenceCustomers.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                dataStatus.DataIsDeleted();
            }
        });
    }


}
