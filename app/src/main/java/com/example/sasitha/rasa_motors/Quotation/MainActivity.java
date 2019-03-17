package com.example.sasitha.rasa_motors.Quotation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.sasitha.rasa_motors.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    Button btnNewQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView =(RecyclerView) findViewById(R.id.recycler_view_quotations);

        btnNewQuote = (Button) findViewById(R.id.btnNewQuote);

        btnNewQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewQuote();
            }
        });


        new FireBaseDatabaseHelper().showInvoices(new FireBaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Quotations> quotations, List<String> keys) {
                new  RecyclerView_Config().setConfig(mRecyclerView,MainActivity.this, quotations,keys);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_quote:
                startActivity(new Intent(this, NewQuotationActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void openNewQuote(){
        Intent intent = new Intent(MainActivity.this, NewQuotationActivity.class);
        startActivity(intent);
    }
}
