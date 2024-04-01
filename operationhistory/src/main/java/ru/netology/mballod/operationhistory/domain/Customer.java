package ru.netology.mballod.operationhistory.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class Customer implements ConsolePrintable, Serializable {
    private int id;
    private String name;
    private int age;
    private double wealth;
    public Customer() {
        this.name = null;
        this.age = 18;
        this.wealth = 0;
    }
    public Customer(String name, int age, double wealth) {
        this.name = name;
        this.age = age;
        this.wealth = wealth;
    }
    public Customer(int id, String name, int age, double wealth) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.wealth = wealth;
    }
    public void setAge(int age) {
        this.age = (age > 0 && age < 200) ? age : this.age;
    }
    @Override
    public void print(){
        System.out.println("Имя: " + this.name + ", возраст: " + this.age + " сумма на счете: " + this.wealth);
    }
}
