package ru.netology.mballod.operationhistory.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
@Getter
@Setter
public class Operation implements ConsolePrintable, Serializable {
    private int id;
    private LocalDate date;
    private double amount;
    private boolean debit;
    public static String pattern = "y-M-d";
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    public Operation(LocalDate date, double amount, boolean debit){
        this.date = date;
        this.amount = amount;
        this.debit = debit;
    }

    public Operation() {

    }

    public void setDate(LocalDate date){
        if (date.isBefore(LocalDate.now())) {
            this.date = date;
        }
    }
    public void setAmount(double amount){
        if (amount>0) {
            this.amount = amount;
        }
    }
    public void setDebitFromString(String InOrOutTransaction){
        if (InOrOutTransaction.equals("y")){
            this.setDebit(true);
        } else if (InOrOutTransaction.equals("n")) {
            this.setDebit(false);
        } else {
            throw new InputMismatchException("Неверный формат ввода");
        }
    }

    public boolean getDebit(){
        return debit;
    }
    public String getDebitAsString(){
        return (debit) ? "входящая" : "исходящая";
    }
    public String getDateAsString(){
        return date.format(formatter);
    }
    @Override
    public void print(){
        System.out.println("Дата: " + this.getDateAsString() + ", сумма: " + amount + " тип: " + this.getDebitAsString());
    }
}
