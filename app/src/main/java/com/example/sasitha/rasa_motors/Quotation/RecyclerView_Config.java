package com.example.sasitha.rasa_motors.Quotation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sasitha.rasa_motors.Quotation.Quotations;
import com.example.sasitha.rasa_motors.R;

import java.security.Key;
import java.util.List;

public class RecyclerView_Config {

    private Context mContext;
    private QuotationsAdapter mQuotationsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Quotations> quotations, List<String> keys){
        mContext = context;
        mQuotationsAdapter = new QuotationsAdapter(quotations,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mQuotationsAdapter);
    }

    class QuotationItemView extends RecyclerView.ViewHolder{
        private TextView mVehicleNo;
        private TextView mType;
        private TextView mJobsAppointed;
        private TextView mDamagesDes;
        private TextView mInDate;
        private TextView mReleasedDate;

        private String key;

        public QuotationItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.quotation_list_item, parent , false));


            mVehicleNo = (TextView) itemView.findViewById(R.id.VehicleNo_view);
            mType = (TextView) itemView.findViewById(R.id.Type_view);
            mJobsAppointed = (TextView) itemView.findViewById(R.id.JobsAppointed_view);
            mDamagesDes = (TextView) itemView.findViewById(R.id.DamagesDes_view);
            mInDate = (TextView) itemView.findViewById(R.id.InDate_view);
            mReleasedDate =  (TextView) itemView.findViewById(R.id.ReleasedDate_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,QuotationDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("VehicleNo",mVehicleNo.getText().toString());
                    intent.putExtra("JobsAppointed",mJobsAppointed.getText().toString());
                    intent.putExtra("DamageDescription",mDamagesDes.getText().toString());
                    intent.putExtra("Type",mType.getText().toString());

                    mContext.startActivity(intent);
                }
            });
        }
        public void bind(Quotations quotations,  String key){
            mVehicleNo.setText(quotations.getVehicle_no());
            mType.setText(quotations.getType());
            mJobsAppointed.setText(quotations.getJobs_Appointed());
            mDamagesDes.setText(quotations.getDamage_Description());
            mInDate.setText(quotations.getIn_Date());
            mReleasedDate.setText(quotations.getReleased_Date());

            this.key = key;
}
    }
    class QuotationsAdapter extends RecyclerView.Adapter<QuotationItemView>{
        private List<Quotations> mQuotationList;
        private List<String> mKeys;

        public QuotationsAdapter(List<Quotations> mQuotationList, List<String> mKeys) {
            this.mQuotationList = mQuotationList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public QuotationItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new QuotationItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull QuotationItemView quotationItemView, int i) {
            quotationItemView.bind(mQuotationList.get(i), mKeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mQuotationList.size();
        }
    }
}
