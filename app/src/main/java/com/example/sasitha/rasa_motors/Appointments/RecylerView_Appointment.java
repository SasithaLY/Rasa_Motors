package com.example.sasitha.rasa_motors.Appointments;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sasitha.rasa_motors.AppointmentDetailsActivity;
import com.example.sasitha.rasa_motors.R;

import java.util.List;

public class RecylerView_Appointment {

    private Context mContext;
    private AppointmnetAdapter mAppointmentAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<AppointmentNew> appointments, List<String> keys){
        mContext = context;
        mAppointmentAdapter = new AppointmnetAdapter(appointments, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAppointmentAdapter);


    }

    class AppointmentItemView extends RecyclerView.ViewHolder{

        private TextView mVehicle;
        private TextView mJob;
        private  TextView mDate;

        private String key;

        public AppointmentItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.appointment_list_item, parent, false));

            mVehicle = (TextView) itemView.findViewById(R.id.vehicle_txtview);
            mJob = (TextView) itemView.findViewById(R.id.job_txtview);
            mDate = (TextView) itemView.findViewById(R.id.date_txtview);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,AppointmentDetailsActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("vehicle",mVehicle.getText().toString());
                    intent.putExtra("job",mJob.getText().toString());
                    intent.putExtra("date",mDate.getText().toString());

                    mContext.startActivity(intent);

                }
            });


        }
        public void bind(AppointmentNew appointmentNew, String key){

            mVehicle.setText(appointmentNew.getVehicle());
            mJob.setText(appointmentNew.getJob());
            mDate.setText(appointmentNew.getDate());
            this.key = key;


        }
    }

    class AppointmnetAdapter extends RecyclerView.Adapter<AppointmentItemView>{

        private List<AppointmentNew> mAppointmnetList;
        private List<String> mKeys;

        public AppointmnetAdapter(List<AppointmentNew> mAppointmnetList, List<String> mKeys) {
            this.mAppointmnetList = mAppointmnetList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public AppointmentItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AppointmentItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull AppointmentItemView holder, int position) {

            holder.bind(mAppointmnetList.get(position),mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mAppointmnetList.size();
        }
    }

}
