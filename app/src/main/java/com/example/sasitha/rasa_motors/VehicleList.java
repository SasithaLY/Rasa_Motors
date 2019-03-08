package com.example.sasitha.rasa_motors;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class VehicleList extends ArrayAdapter<Vehicle> {

    private Activity context;
    private List<Vehicle> vehiclesList;

    public VehicleList(Activity context, List<Vehicle> vehiclesList){

        super(context, R.layout.vehiclelist_layout, vehiclesList);
        this.context = context;
        this.vehiclesList = vehiclesList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.vehiclelist_layout, null, true);

        TextView textViewModel = (TextView) listViewItem.findViewById(R.id.txtViewModel);
        TextView textViewType = (TextView) listViewItem.findViewById(R.id.txtVehicleType);
        TextView textViewNo = (TextView) listViewItem.findViewById(R.id.txtViewNumber);
        TextView textViewColor = (TextView) listViewItem.findViewById(R.id.txtViewColor);

        Vehicle vehicle = vehiclesList.get(position);

        textViewModel.setText(vehicle.getVehModel());
        textViewType.setText(vehicle.getVehType());
        textViewNo.setText(vehicle.getVehNumber());
        textViewColor.setText(vehicle.getVehColor());

        return listViewItem;
    }
}
