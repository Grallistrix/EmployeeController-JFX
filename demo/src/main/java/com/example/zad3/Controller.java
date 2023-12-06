package com.example.zad3;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

public class Controller implements Initializable {
    

    @FXML
    private TableView<ClassEmployee> tableGroups;

    @FXML
    private TableView<Employee> tableEmployees;
    
//Collumns
    @FXML
    private TableColumn<Employee, String> nazwisko;
     @FXML
    private TableColumn<ClassEmployee, String> Nazwa_g;
    @FXML
    private TableColumn<ClassEmployee, String> currentCap;
    @FXML
    private TableColumn<Employee, String> imie;
    @FXML
    private TableColumn<Employee, EmployeeCondition> stan;


    @FXML
    private TableColumn<Employee, Integer> wyplata;
    @FXML
    private TableColumn<Employee, Integer> rok;

//Employees
    @FXML
    private TextField imieField;
    @FXML
    private TextField nazwiskoField;
    @FXML
    private TextField yearField;
    @FXML
    private ChoiceBox<EmployeeCondition> stateField;
    @FXML
    private TextField salaryField;


//Groups
    @FXML
    private TextField nazwaField;
    @FXML
    private TextField maxField;

    int currentGroup;

    ObservableList<ClassEmployee> mainGroupList = FXCollections.observableArrayList(
                    new ClassEmployee("MENADŻEROWIE", 2),
                    new ClassEmployee("PRACOWNICY", 12),
                    new ClassEmployee("STAŻYŚCI", 40),
                    new ClassEmployee("KONTRAKTORZY", 5));

    Employee Pracownik1 = new Employee("Michał", "Brzęczyszczykiewicz",EmployeeCondition.chory ,2000,3500);
    Employee Pracownik2 = new Employee("Krzysztof", "Konerski",EmployeeCondition.delegacja ,1993,6400);
    Employee Pracownik3 = new Employee("Jan", "Janowicz",EmployeeCondition.obecny ,20008,12000);
    Employee Pracownik4 = new Employee("Michał", "Michalski",EmployeeCondition.obecny ,1999,1400);
    Employee Pracownik5 = new Employee("Kuba", "Moniuszko" ,EmployeeCondition.obecny ,2002,3500);
    Employee Pracownik6 = new Employee("Karol", "Karolewicz" ,EmployeeCondition.obecny ,2002,3500);
    Employee Pracownik7 = new Employee("Andrzej", "Forbuszko",EmployeeCondition.delegacja ,1993,6400);

    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        stateField.setItems(FXCollections.observableArrayList( EmployeeCondition.values()));

        mainGroupList.get(0).employeeList.add(Pracownik1);
        mainGroupList.get(1).employeeList.add(Pracownik2);
        mainGroupList.get(1).employeeList.add(Pracownik3);
        mainGroupList.get(2).employeeList.add(Pracownik4);
        mainGroupList.get(2).employeeList.add(Pracownik5);
        mainGroupList.get(3).employeeList.add(Pracownik6);
        mainGroupList.get(3).employeeList.add(Pracownik7);

        mainGroupList.get(0).updateCap();
        mainGroupList.get(1).updateCap();
        mainGroupList.get(2).updateCap();
        mainGroupList.get(3).updateCap();

        Nazwa_g.setCellValueFactory(new PropertyValueFactory<ClassEmployee,String>("groupName"));
        currentCap.setCellValueFactory(new PropertyValueFactory<ClassEmployee,String>("currentCap"));

        tableGroups.setItems(mainGroupList);

        imie.setCellValueFactory(new PropertyValueFactory<Employee,String>("imie"));
        nazwisko.setCellValueFactory(new PropertyValueFactory<Employee,String>("nazwisko"));
        stan.setCellValueFactory(new PropertyValueFactory<Employee,EmployeeCondition>("state"));
        wyplata.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("salary"));
        rok.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("rokUr"));
    }
    
    @FXML
    private void showButton(ActionEvent event){
        tableEmployees.setItems(mainGroupList.get(tableGroups.getSelectionModel().getSelectedIndex()).employeeList);
        currentGroup = tableGroups.getSelectionModel().getSelectedIndex();
    }
    @FXML
    private void delGroup(ActionEvent event){
        tableGroups.getItems().removeAll(tableGroups.getSelectionModel().getSelectedItem());
        tableEmployees.refresh();
        tableGroups.refresh();
    }
    @FXML
    private void delEmployee(ActionEvent event){
        mainGroupList.get(currentGroup).removeEmployee(tableEmployees.getSelectionModel().getSelectedItem());
        tableGroups.refresh();
        tableEmployees.refresh();
    }
    @FXML
    private void EditButton(ActionEvent event){

        Employee local = tableEmployees.getSelectionModel().getSelectedItem();
        
        String name = imieField.getText();
        String nazwisko = nazwiskoField.getText();
        EmployeeCondition state=stateField.getValue();
        int salary = Integer.parseInt(salaryField.getText());
        int year = Integer.parseInt(yearField.getText());

        if(imieField.getText().isEmpty())
            name=local.imie;
        if(nazwiskoField.getText().isEmpty())
            nazwisko=local.nazwisko;
        if(salaryField.getText().isEmpty())
            salary=local.salary;
        if(yearField.getText().isEmpty())
            year=local.rokUr;

        mainGroupList.get(currentGroup).removeEmployee(tableEmployees.getSelectionModel().getSelectedItem());
        mainGroupList.get(currentGroup).addEmployee(new Employee(name, nazwisko , state,year, salary));

        tableGroups.refresh();
        imieField.clear();
        nazwiskoField.clear();
        salaryField.clear();
        yearField.clear();

    }

    @FXML
    private void addGroup(ActionEvent event){
        String groupName = nazwaField.getText();
        Double groupMax = Double.parseDouble(maxField.getText());

        mainGroupList.add(new ClassEmployee(groupName,groupMax));

        tableGroups.refresh();
        nazwaField.clear();
        maxField.clear();
    }
   @FXML
    private void addEmployee(ActionEvent event){
        String name = imieField.getText();
        String nazwisko = nazwiskoField.getText();
        EmployeeCondition state=stateField.getValue();
        int salary = Integer.parseInt(salaryField.getText());
        int year = Integer.parseInt(yearField.getText());

        mainGroupList.get(currentGroup).addEmployee(new Employee(name, nazwisko , state,salary, year));
        tableGroups.refresh();

        imieField.clear();
        nazwiskoField.clear();
        salaryField.clear();
        yearField.clear();

    }

    @FXML
    private void EditGroup(ActionEvent event){
        ClassEmployee local = tableGroups.getSelectionModel().getSelectedItem();
        
        String groupName = nazwaField.getText();
        Double groupMax = Double.parseDouble(maxField.getText());

        if(nazwaField.getText().isEmpty())
            groupName = local.groupName;

        if(maxField.getText().isEmpty())
            groupMax=local.maxSize;

        tableGroups.getSelectionModel().getSelectedItem().setGroupName(groupName);
        tableGroups.getSelectionModel().getSelectedItem().setMaxSize(groupMax);
        tableGroups.getSelectionModel().getSelectedItem().updateCap();
        tableGroups.refresh();

        tableEmployees.refresh();
        nazwaField.clear();
        maxField.clear();


    }

    @FXML
    private void sortButton(ActionEvent event){
        /*
         SortedList<SOMETHING> sortedData = new SortedList<>(data);
         sortedData.comparatorProperty().bind(tableView.comparatorProperty());
         // Ustaw TableView na posortowane dane
         tableView.setItems(sortedData);
         tableView.refresh();
         */
        }
}