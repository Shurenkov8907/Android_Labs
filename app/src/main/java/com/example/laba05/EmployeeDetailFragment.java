package com.example.laba05;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EmployeeDetailFragment extends Fragment {

    private static final String ARG_EMPLOYEE = "employee";
    private Employee employee;

    public static EmployeeDetailFragment newInstance(Employee employee) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_EMPLOYEE, employee);
        EmployeeDetailFragment fragment = new EmployeeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_detail, container, false);

        if (getArguments() != null) {
            employee = (Employee) getArguments().getSerializable(ARG_EMPLOYEE);
            TextView tvDetail = view.findViewById(R.id.tvDetail);
            tvDetail.setText("Таб. №: " + employee.getNumber() + "\nФИО: " + employee.getName()
                    + "\nРазряд: " + employee.getRank() + "\nСтавка: " + employee.getRate());
        }
        return view;
    }
}
