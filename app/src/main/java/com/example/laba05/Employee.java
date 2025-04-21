package com.example.laba05;

import java.io.Serializable;

public class Employee implements Serializable {
    private String number, name, rank, rate;

    public Employee(String number, String name, String rank, String rate) {
        this.number = number;
        this.name = name;
        this.rank = rank;
        this.rate = rate;
    }

    public String getNumber() { return number; }
    public String getName() { return name; }
    public String getRank() { return rank; }
    public String getRate() { return rate; }
}
