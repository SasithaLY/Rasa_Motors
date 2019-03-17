package com.example.sasitha.rasa_motors.Quotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sasitha.rasa_motors.R;

import java.util.List;


public class NewQuotationActivity extends AppCompatActivity {

    private EditText mVehicleNo_edit;
    private EditText mJob_edit;
    private EditText mDamage_edit;
    private Spinner mCatergory_edit;
    private Button mAdd_btn;
    private Button mBack_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quotation);

        mVehicleNo_edit = (EditText) findViewById(R.id.VehicleNo_edit);
        mJob_edit = (EditText) findViewById(R.id.Job_edit);
        mDamage_edit = (EditText) findViewById(R.id.Damage_edit);
        mCatergory_edit =(Spinner) findViewById(R.id.Catergory_edit);



        mAdd_btn = (Button) findViewById(R.id.Add_btn);
        mBack_btn = (Button) findViewById(R.id.Back_btn);

        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Quotations quotations = new Quotations();
                quotations.setVehicle_no(mVehicleNo_edit.getText().toString());
                quotations.setJobs_Appointed(mJob_edit.getText().toString());
                quotations.setDamage_Description(mDamage_edit.getText().toString());
                quotations.setType(mCatergory_edit.getSelectedItem().toString());

                new FireBaseDatabaseHelper().AddQuotation(quotations, new FireBaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Quotations> quotations, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewQuotationActivity.this,"The new Quotation has been inserted successfully",Toast.LENGTH_LONG).show();
                        finish(); return;
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
        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); return;
            }
        });
    }
}
