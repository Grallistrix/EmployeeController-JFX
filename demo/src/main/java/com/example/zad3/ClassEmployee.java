package com.example.zad3;


import java.text.DecimalFormat;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class ClassEmployee implements Comparator<String>
{
    String groupName;
    ObservableList<Employee> employeeList;
    double maxSize;
    String currentCap;



    ClassEmployee(String newName, double newMaxSize )//Button button1
    {
        groupName = newName;
        maxSize = newMaxSize;
        employeeList = FXCollections.observableArrayList();
        currentCap = "0%";
    }

    public void  sortByName(){
        Collections.sort(employeeList);
    }
    public void sortBySalary(){
        Collections.sort(employeeList, new Comparator_Salary());
    }
    public void max(){
        System.out.println("Found Max: ");
        Collections.max(employeeList).Print();
    }

    public void addEmployee(Employee newEmployee)
    {
        if(employeeList.size() < maxSize){
            for (int i = 0; i < employeeList.size(); i++)
                if(newEmployee.compareTo(employeeList.get(i)) == 0){
                    System.out.println("Już istnieje!");
                    return;
                }
            employeeList.add(newEmployee);
            this.updateCap();
        }
        else
           System.out.println("\nGrupa pełna");
    }
    public void addSalary(Employee employee, double newWynagrodzenie){
        employee.salary +=newWynagrodzenie;
    }
    public void removeEmployee(Employee employee){
        this.employeeList.remove(employee);
        this.updateCap();
    }
    public void changeCondition(Employee employee, EmployeeCondition newCond){
        employee.state = newCond;
    }
    public void setGroupName(String nazwa_grupy) {
        this.groupName = nazwa_grupy;
    }

    public void setEmployeeList(ObservableList<Employee> lista_pracownikow) {
        this.employeeList = lista_pracownikow;
    }

    public void  search(String neededNazwisko)
    {
        for (int i = 0; i < employeeList.size(); i++){
            if( compare(neededNazwisko, employeeList.get(i).nazwisko) == 0)
                employeeList.get(i).Print();
            else
                System.out.println("Not Found");
        }
    }
    public void   countByCondition (EmployeeCondition x)
    {
        int result =0;
        for (int i = 0; i < employeeList.size(); i++)
            if(x == employeeList.get(i).state)
                result++;

        System.out.println("Ilość Stanu: " + result);
    }
    public void  summary()
    {
        for (int i = 0; i < employeeList.size(); i++)
            employeeList.get(i).Print();
    }

    public int compare(String a, String b)
    {
        if(a == b )
            return 0;
        else
            return 1;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public double getMaxSize() {
        return maxSize;
    }

    public String getCurrentCap() {
        return currentCap;
    }

    public void updateCap() {
        Double myDouble = this.employeeList.size()/this.maxSize *100;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(myDouble);
        this.currentCap =   formattedValue+ "%";
    }


    public void setMaxSize(double max_p) {
        this.maxSize = max_p;
    }
}