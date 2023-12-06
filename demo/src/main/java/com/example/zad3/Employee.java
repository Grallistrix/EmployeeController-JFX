package com.example.zad3;

public class Employee implements Comparable<Employee>
{
    String imie;
    String nazwisko;
    EmployeeCondition state;
    int rokUr;
    int salary;

    Employee( String newName, String newNazwisko,EmployeeCondition newCondition, int newYear, int newSalary)
    {
        imie =newName;
        nazwisko=newNazwisko;
        state=newCondition;
        rokUr=newYear;
        salary=newSalary;}

    public void Print()
    {
        System.out.println("dane Osobowe: "+ imie + " " +  nazwisko);
        System.out.println("state: " +state );
        System.out.println("rok Urodzenia: "+ rokUr);
        System.out.println("Płaca: " +salary );

    };
    @Override
    public int compareTo(Employee pracownik1)
    {
        int results = this.nazwisko.compareTo(pracownik1.nazwisko);
        return results;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public EmployeeCondition getState() {
        return state;
    }

    public int getRokUr() {
        return rokUr;
    }

    public double getSalary() {
        return salary;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setState(EmployeeCondition stan_pracownika) {
        this.state = stan_pracownika;
    }

    public void setRokUr(int rok_urodzenia) {
        this.rokUr = rok_urodzenia;
    }

    public void setSalary(int newSalary) {
        this.salary = newSalary;
    }
}
