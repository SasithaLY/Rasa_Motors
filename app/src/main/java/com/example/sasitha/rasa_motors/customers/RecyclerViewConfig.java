package com.example.sasitha.rasa_motors.customers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sasitha.rasa_motors.R;

import java.util.List;

public class RecyclerViewConfig
{
    private Context mContext;
    private CustomersAdapter mCustomersAdapter;

    public void setConfiger(RecyclerView recyclerView, Context context, List<customer> customers, List<String> keys)
    {
        mContext = context;
        mCustomersAdapter = new CustomersAdapter(customers, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mCustomersAdapter);
    }

    class CustomersView extends RecyclerView.ViewHolder
    {
        private TextView mName;
        private TextView mAddress;
        private TextView mPhone;

        private String key;

        public CustomersView(ViewGroup parent)
        {
            super(LayoutInflater.from(mContext).inflate(R.layout.customer_list, parent, false));

            mName = (TextView) itemView.findViewById(R.id.nameTxtView);
            mAddress = (TextView) itemView.findViewById(R.id.addressTxtView);
            mPhone = (TextView) itemView.findViewById(R.id.phoneTxtView);
        }

        public void bind(customer cust, String key)
        {
            mName.setText(cust.getName());
            mAddress.setText(cust.getAddress());
            mPhone.setText(cust.getPhone());
            this.key = key;
        }
    }

    class CustomersAdapter extends RecyclerView.Adapter<CustomersView>
    {
        private List<customer> mCustomerList;
        private List<String> mKeys;

        public CustomersAdapter(List<customer> mCustomerList, List<String> mKeys)
        {
            this.mCustomerList = mCustomerList;
            this.mKeys = mKeys;
        }

        public CustomersAdapter() {
            super();
        }

        @NonNull
        @Override
        public CustomersView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
        {
            return new CustomersView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomersView customersView, int i)
        {
            customersView.bind(mCustomerList.get(i), mKeys.get(i));
        }

        @Override
        public int getItemCount()
        {
            return mCustomerList.size();
        }
    }
}
