package ru.netology.mballod.operationhistory.service;

import org.springframework.stereotype.Service;
import ru.netology.mballod.operationhistory.domain.Customer;
import ru.netology.mballod.operationhistory.domain.Operation;
import ru.netology.mballod.operationhistory.domain.OperationData;
import ru.netology.mballod.operationhistory.exceptions.CustomerOperationOutOfBoundException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
@Service
public class IOService {

    public static Operation OperationInput(){ //ввод операции
        Scanner scanner = new Scanner(System.in);
        Operation operation = new Operation();
        try {
            System.out.println("Введите дату транзакции в формате: " + Operation.pattern + ", например 2024-2-21");
            String DateForInput = scanner.next();
            operation.setDate(LocalDate.parse(DateForInput, Operation.formatter));
            System.out.println("Введите сумму транзакции в рублях, разделяя точкой целую и дробную часть");
            operation.setAmount(scanner.nextDouble());
            System.out.println("Транзакция входящая (y) или исходящая (n)?");
            operation.setDebitFromString(scanner.next());
            return operation;

        } catch (InputMismatchException | DateTimeParseException e1) {
            System.out.println("Неверный формат ввода");
            return null;
        }
    }
    public static Customer whoIsClient (OperationData operationData){ // ввод номера клиента
        Scanner scanner = new Scanner(System.in);
        System.out.println("Чья это транзакция: введите номер клиента - от 1 до " +OperationData.N_OF_CUSTOMERS);
        int clientId = scanner.nextInt() - 1;
        if (clientId > OperationData.N_OF_CUSTOMERS-1 || clientId<0)
            throw new CustomerOperationOutOfBoundException(clientId+1);
        return operationData.getCustomers().findCustomerById(clientId);
    }
    public static Customer CustomerInput() {// ввод информации о клиенте
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        try {
            System.out.println("Введите полное имя клиента: ");
            customer.setName(scanner.next());
            System.out.println("Введите сумму на счете клиента");
            customer.setWealth(scanner.nextDouble());
            System.out.println("Введите возраст клиента");
            customer.setAge(scanner.nextInt());
            return customer;
        } catch (InputMismatchException | DateTimeParseException e1) {
            System.out.println("Неверный формат ввода");
            return null;
        }
    }
    public static void printOperations(OperationData operationData, int clientId){  // вывод всех операций клиента
        System.out.println("Операции клиента: " + (clientId+1));
        for (Operation op: operationData.getOperations(clientId).getOperations()) op.print();
    }

    public static void printOperations(OperationData operationData){
        System.out.println("Вывод информации об операциях клиента. Введите номер клиента");
        Scanner scanner = new Scanner(System.in);
        int clientId = scanner.nextInt()-1;
        if (clientId > OperationData.N_OF_CUSTOMERS -1 || clientId<0)
            throw new CustomerOperationOutOfBoundException(clientId+1); //реальной транзакции нет, но номер клиента неверный
        operationData.getCustomers().getCustomers().get(clientId).print();
        //operationData.getCustomers().getCustomers()[clientId].print();//.getCustomers()[clientId].print();
        printOperations(operationData, clientId);
    }
    public static void putOperationDataInFile(OperationData operationData) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp.out"); // to IOService
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(operationData);
        oos.flush();
        oos.close();
    }

    public static OperationData takeOperationDataFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("temp.out"); // to IOService
        ObjectInputStream oin = new ObjectInputStream(fis);
        OperationData operationsDataFromFile;
        operationsDataFromFile = (OperationData) oin.readObject();
        return operationsDataFromFile;
    }
}
