package com.example.sasitha.rasa_motors.customers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sasitha.rasa_motors.Login;
import com.example.sasitha.rasa_motors.R;
import com.example.sasitha.rasa_motors.pkg_vehicles.HomePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        auth = FirebaseAuth.getInstance();

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

                auth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            Toast.makeText(CustomerDetailsActivity.this, getString(R.string.cDelete), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(CustomerDetailsActivity.this, Login.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else{
                            Toast.makeText(CustomerDetailsActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

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
                       //Toast.makeText(CustomerDetailsActivity.this, getString(R.string.cDelete), Toast.LENGTH_LONG).show();
//                       firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                           @Override
//                           public void onComplete(@NonNull Task<Void> task) {
//                                if(task.isComplete()){
//                                    Toast.makeText(CustomerDetailsActivity.this, getString(R.string.cDelete), Toast.LENGTH_LONG).show();
//                                    Intent intent = new Intent(CustomerDetailsActivity.this, Login.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    startActivity(intent);
//                                }else{
//                                    Toast.makeText(CustomerDetailsActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                                }
//                           }
//                       });

                   }
               });
            }
        });
    }
}
