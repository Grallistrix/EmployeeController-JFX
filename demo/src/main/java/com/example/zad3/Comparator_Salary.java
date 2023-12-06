package com.example.zad3;

import java.util.Comparator;

public class Comparator_Salary  implements Comparator<Employee> {
    @Override
    public int compare(Employee prac1, Employee prac2) {
        return (int) (prac2.salary - prac1.salary);
    }
}