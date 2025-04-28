package com.example.laba05;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class EmployeeListFragment extends Fragment {

    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;
    private ArrayList<Employee> employees = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewEmployees);

        adapter = new EmployeeAdapter(employees, () -> {});
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(employee -> showDetails(employee));

        return view;
    }

    private void showDetails(Employee employee) {
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        if (isTablet) {
            // планшет - показать детальный фрагмент
            EmployeeDetailFragment detailFragment = EmployeeDetailFragment.newInstance(employee);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detailContainer, detailFragment)
                    .commit();
        } else {
            // телефон - открыть отдельную активность
            Intent intent = new Intent(getContext(), EmployeeDetailActivity.class);
            intent.putExtra("employee", employee);
            startActivity(intent);
        }
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        adapter.notifyItemInserted(employees.size() - 1);
    }
}
