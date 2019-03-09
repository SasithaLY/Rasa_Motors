package com.example.sasitha.rasa_motors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewConfig {

    private Context vContext;
    private VehiclesAdapter vehiclesAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Vehicle> vehicles, List<String> keys){
        vContext = context;
        vehiclesAdapter = new VehiclesAdapter(vehicles, keys);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(vehiclesAdapter);

    }


    class VehicleItemView extends RecyclerView.ViewHolder{

        private TextView vehModel;
        private TextView vehType;
        private TextView vehNumber;
        private TextView vehColor;

        private String key;

        public VehicleItemView(ViewGroup parent) {

            super(LayoutInflater.from(vContext).inflate(R.layout.vehiclelist_layout, parent, false));

            vehModel = (TextView) itemView.findViewById(R.id.txtViewModel);
            vehType = (TextView) itemView.findViewById(R.id.txtViewType);
            vehNumber = (TextView) itemView.findViewById(R.id.txtViewNumber);
            vehColor = (TextView) itemView.findViewById(R.id.txtViewColor);

        }

        public void bind(Vehicle vehicle,String key){
            vehModel.setText(vehicle.getVehModel());
            vehType.setText(vehicle.getVehType());
            vehNumber.setText(vehicle.getVehNumber());
            vehColor.setText(vehicle.getVehColor());
            this.key = key;
        }
    }

    class VehiclesAdapter extends RecyclerView.Adapter<VehicleItemView>{

        private List<Vehicle> vehicleList;
        private List<String> vKeys;

        public VehiclesAdapter(List<Vehicle> vehicleList, List<String> vKeys) {
            this.vehicleList = vehicleList;
            this.vKeys = vKeys;
        }

        public VehiclesAdapter() {
            super();
        }

        @NonNull
        @Override
        public VehicleItemView onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            return new VehicleItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull VehicleItemView holder, int position) {
            holder.bind(vehicleList.get(position), vKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return vehicleList.size();
        }
    }
}
