package com.example.sasitha.rasa_motors.Quotation;

import android.content.Intent;
import android.icu.util.ULocale;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sasitha.rasa_motors.R;

import java.util.List;
import java.util.Locale;

public class QuotationDetailsActivity extends AppCompatActivity {

    private EditText mVehicleNo_edit;
    private EditText mJob_edit;
    private EditText mDamage_edit;
    private Spinner mCatergory_edit;


    private Button mUpdate_btn;
    private Button mDelete_btn;
    private Button mBack_btn;

    private String key;
    private String VehicleNo;
    private String JobsAppointed;
    private String DamageDescription;
    private String Type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_details);

        key = getIntent().getStringExtra("key");
        VehicleNo = getIntent().getStringExtra("VehicleNo");
        JobsAppointed = getIntent().getStringExtra("JobsAppointed");
        DamageDescription = getIntent().getStringExtra("DamageDescription");
        Type = getIntent().getStringExtra("Type");


        mVehicleNo_edit = (EditText) findViewById(R.id.VehicleNo_edit);
        mVehicleNo_edit.setText(VehicleNo);
        mJob_edit = (EditText) findViewById(R.id.Job_edit);
        mJob_edit.setText(JobsAppointed);
        mDamage_edit = (EditText) findViewById(R.id.Damage_edit);
        mDamage_edit.setText(DamageDescription);
        mCatergory_edit = (Spinner) findViewById(R.id.Catergory_edit);
        mCatergory_edit.setSelection(getIndex_SpinnerItem(mCatergory_edit, Type));


        mUpdate_btn = (Button) findViewById(R.id.Update_btn);
        mDelete_btn = (Button) findViewById(R.id.Delete_btn);
        mBack_btn = (Button) findViewById(R.id.Back_btn);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quotations quotations = new Quotations();
                quotations.setVehicle_no(mVehicleNo_edit.getText().toString());
                quotations.setJobs_Appointed(mJob_edit.getText().toString());
                quotations.setDamage_Description(mDamage_edit.getText().toString());
                quotations.setType(mCatergory_edit.getSelectedItem().toString());

                new FireBaseDatabaseHelper().updateQuotation(key, quotations, new FireBaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Quotations> quotations, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(QuotationDetailsActivity.this,"Quatation record has been updated Successfuly", Toast.LENGTH_LONG).show();
                        finish(); return;
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new FireBaseDatabaseHelper().deleteQuotation(key, new FireBaseDatabaseHelper.DataStatus() {
                     @Override
                     public void DataIsLoaded(List<Quotations> quotations, List<String> keys) {

                     }

                     @Override
                     public void DataIsInserted() {

                     }

                     @Override
                     public void DataIsUpdated() {

                     }

                     @Override
                     public void DataIsDeleted() {
                         Toast.makeText(QuotationDetailsActivity.this,"Quotation record has been deleted succesfully!",Toast.LENGTH_LONG).show();
                         finish();
                         return;
                     }
                 });
                 mBack_btn.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         finish();
                         return;
                     }
                 });
            }
        });

        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }


    private int getIndex_SpinnerItem(Spinner spinner, String item) {
        int index = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(item))
                index = i;
            break;

        }
       return index;
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}