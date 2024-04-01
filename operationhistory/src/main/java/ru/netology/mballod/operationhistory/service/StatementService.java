package ru.netology.mballod.operationhistory.service;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.netology.mballod.operationhistory.domain.Customer;
import ru.netology.mballod.operationhistory.domain.Operation;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
@Service
@Setter
@Getter
public class StatementService implements Serializable {
    private Map<Integer, List<Operation>> storage = new HashMap<>();

    public void AddOpToStatement(Customer customer, Operation operation){
        if(storage.containsKey(customer.getId())){
            storage.get(customer.getId()).add(operation);
        }else {
            List<Operation> oneOp = new ArrayList<>(Arrays.asList(operation));
            storage.put(customer.getId(), oneOp);
        }

    }
}
