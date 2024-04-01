package ru.netology.mballod.operationhistory.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.netology.mballod.operationhistory.domain.Customer;
import ru.netology.mballod.operationhistory.domain.Operation;
import ru.netology.mballod.operationhistory.exceptions.CustomerOperationOutOfBoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Service
@Setter
@Getter
public class CustomerService implements Serializable {
    private List<Customer> customers = new ArrayList<>();

    public void setCustomers(int customerId, Customer customer){
        if (customerId<0)
            throw new CustomerOperationOutOfBoundException(customerId+1);
        customers.add(customerId, customer);
    }
    public Customer findCustomerById (int Id){ // не проверяете клиентов на совпадение Id
        for (Customer client : customers){
            if (Id == client.getId()){
                return client;
            }
        }
        return null;

    }

    public void addCustomer(Customer customer){
        this.customers.add(customer);
    }
    @PostConstruct
    public void initStorage() {
        customers.add(new Customer(1, "Spring", 30, 100));
        customers.add(new Customer(2, "Boot", 31, 200));
    }

}
