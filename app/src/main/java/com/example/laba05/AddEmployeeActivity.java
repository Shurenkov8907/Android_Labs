package com.example.laba05;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEmployeeActivity extends AppCompatActivity {

    private EditText etNumber, etName, etRank, etRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);


        initViews();


        findViewById(R.id.btnAddEmp).setOnClickListener(v -> onAddButtonClick());
    }

    private void initViews() {
        etNumber = findViewById(R.id.etNumber);
        etName = findViewById(R.id.etName);
        etRank = findViewById(R.id.etRank);
        etRate = findViewById(R.id.etRate);
    }

    private void onAddButtonClick() {
        String number = etNumber.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String rank = etRank.getText().toString().trim();
        String rate = etRate.getText().toString().trim();


        if (number.isEmpty() || name.isEmpty() || rank.isEmpty() || rate.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }


        Employee employee = new Employee(number, name, rank, rate);


        Intent resultIntent = new Intent();
        resultIntent.putExtra("employee", employee);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
