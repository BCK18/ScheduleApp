package com.example.scheduleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ShiftAdapter extends ArrayAdapter<Shift> {
    public ShiftAdapter(@NonNull Context context, List<Shift> shifts) {
        super(context, 0, shifts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Shift shift = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shift_cell, parent, false);
        }

        TextView dateTV = convertView.findViewById(R.id.dateTV);
        TextView shiftTypeTV = convertView.findViewById(R.id.shiftTypeTV);
        TextView supervisorTV = convertView.findViewById(R.id.supervisorTV);
        TextView secondTV = convertView.findViewById(R.id.secondTV);

        String date = "Date: " + shift.getDate().toString();
        String shiftType = "Shift Type: " + shift.getShiftType();
        String supervisorName = "Supervisor: " + shift.getSupervisor().first_Name + " " + shift.getSupervisor().getLastName();
        String secondName = "Staff: " + shift.getSecond().first_Name + " " + shift.getSecond().getLastName();


        dateTV.setText(date);
        shiftTypeTV.setText(shiftType);
        supervisorTV.setText(supervisorName);
        secondTV.setText(secondName);

        return convertView;
    }
}
