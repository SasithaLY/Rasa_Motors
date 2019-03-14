package com.example.sasitha.rasa_motors.customers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import com.example.sasitha.rasa_motors.R;
import com.example.sasitha.rasa_motors.customers.RecyclerViewConfig;
import com.example.sasitha.rasa_motors.customers.customer;
import com.example.sasitha.rasa_motors.customers.firebaseDatabaseHelper;

public class viewCustomer extends AppCompatActivity
{
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewCustomers);
        new firebaseDatabaseHelper().viewCustomers(new firebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<customer> customers, List<String> keys)
            {
                new RecyclerViewConfig().setConfiger(mRecyclerView, viewCustomer.this, customers, keys);
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

    }
}
