package com.example.sasitha.rasa_motors.customers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sasitha.rasa_motors.R;

import java.util.List;

public class Customers extends AppCompatActivity
{
    private EditText mEditTextName;
    private EditText mEditTextAddress;
    private EditText mEditTextPhone;
    private Button mBtnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextAddress = (EditText) findViewById(R.id.editTextAddress);
        mEditTextPhone = (EditText) findViewById(R.id.editTextPhone);
        mBtnReg = (Button) findViewById(R.id.btnReg);

        mBtnReg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                customer cust = new customer();

                cust.setName(mEditTextName.getText().toString());
                cust.setAddress(mEditTextAddress.getText().toString());
                cust.setPhone(mEditTextPhone.getText().toString());

                new firebaseDatabaseHelper().addCustomer(cust, new firebaseDatabaseHelper.DataStatus()
                {
                    @Override
                    public void DataIsLoaded(List<customer> customers, List<String> keys)
                    {

                    }

                    @Override
                    public void DataIsInserted()
                    {
                        Toast.makeText(Customers.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });


    }
}
