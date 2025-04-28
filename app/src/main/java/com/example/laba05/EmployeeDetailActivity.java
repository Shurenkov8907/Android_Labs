package com.example.laba05;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class EmployeeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        if (savedInstanceState == null) {
            Employee employee = (Employee) getIntent().getSerializableExtra("employee");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detailContainer, EmployeeDetailFragment.newInstance(employee))
                    .commit();
        }
    }
}
