package com.example.zad3;

import java.util.HashMap;
public class ClassContainer
{
    HashMap<String, ClassEmployee> Groups;

    ClassContainer(){
        Groups = new HashMap<String, ClassEmployee>();
    }
    public void addClass(String nazwa, double x){
        Groups.put(nazwa, new ClassEmployee(nazwa,x));
    }
    public void removeClass(String nazwa){
        Groups.remove(nazwa);
    }
    public void findEmpty(){
        for (HashMap.Entry<String, ClassEmployee> set :Groups.entrySet())
            if(set.getValue().employeeList.size()== 0)
                System.out.println(set.getKey());

    }
}