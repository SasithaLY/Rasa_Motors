package com.example.sasitha.rasa_motors.customers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sasitha.rasa_motors.R;

import java.util.List;

public class CustomerDetailsActivity extends AppCompatActivity
{
    private EditText mEditTextName;
    private EditText mEditTextAddress;
    private EditText mEditTextphone;
    private Button mBtnUpdate;
    private Button mBtnDelete;

    private String key;
    private String name;
    private String address;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("Name");
        address = getIntent().getStringExtra("Address");
        phone = getIntent().getStringExtra("Phone");


        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextName.setText(name);

        mEditTextAddress = (EditText) findViewById(R.id.editTextAddress);
        mEditTextAddress.setText(address);

        mEditTextphone = (EditText) findViewById(R.id.editTextPhone);
        mEditTextphone.setText(phone);

        mBtnUpdate = (Button) findViewById(R.id.btnUpdate);
        mBtnDelete = (Button) findViewById(R.id.btnDelete);

        mBtnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                customer cust = new customer();
                cust.setName(mEditTextName.getText().toString());
                cust.setAddress(mEditTextAddress.getText().toString());
                cust.setPhone(mEditTextphone.getText().toString());

                new firebaseDatabaseHelper().updateCustomer(key, cust, new firebaseDatabaseHelper.DataStatus()
                {
                    @Override
                    public void DataIsLoaded(List<customer> customers, List<String> keys)
                    {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated()
                    {
                        Toast.makeText(CustomerDetailsActivity.this, getString(R.string.cUpdate), Toast.LENGTH_LONG).show();
                        finish(); return;
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        mBtnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               new firebaseDatabaseHelper().deleteCustomer(key, new firebaseDatabaseHelper.DataStatus()
               {
                   @Override
                   public void DataIsLoaded(List<customer> customers, List<String> keys)
                   {

                   }

                   @Override
                   public void DataIsInserted() {

                   }

                   @Override
                   public void DataIsUpdated() {

                   }

                   @Override
                   public void DataIsDeleted()
                   {
                       Toast.makeText(CustomerDetailsActivity.this, getString(R.string.cDelete), Toast.LENGTH_LONG).show();
                       finish(); return;
                   }
               });
            }
        });
    }
}
