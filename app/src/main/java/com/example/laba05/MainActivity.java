package com.example.laba05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;
    private List<Employee> employeeList;
    private TextView emptyText;
    private static final int ADD_EMPLOYEE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyText = findViewById(R.id.emptyText);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        employeeList = new ArrayList<>();
        adapter = new EmployeeAdapter(employeeList, this::updateEmptyState);
        recyclerView.setAdapter(adapter);

        updateEmptyState();

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEmployeeActivity.class);
            startActivityForResult(intent, ADD_EMPLOYEE_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_EMPLOYEE_REQUEST && resultCode == RESULT_OK) {
            Employee employee = (Employee) data.getSerializableExtra("employee");
            employeeList.add(employee);
            adapter.notifyItemInserted(employeeList.size() - 1);
            updateEmptyState();
        }
    }

    private void updateEmptyState() {
        emptyText.setVisibility(employeeList.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
