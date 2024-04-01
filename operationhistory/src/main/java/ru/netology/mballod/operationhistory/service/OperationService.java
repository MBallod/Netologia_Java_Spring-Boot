package ru.netology.mballod.operationhistory.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.netology.mballod.operationhistory.domain.Operation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@Setter
@Getter
public class OperationService implements Serializable {

    private List<Operation> operations = new ArrayList<>();

    public void addOperation(Operation operation){
        this.operations.add(operation);
    }

    public void saveOperation(Operation operation){
        this.operations.add(operation.getId(), operation);
    }
    public void AddOpToOperations(int transaction_n){ //ввод операции, добавляет операцию в список операций
        Operation newOperation = IOService.OperationInput();
        operations.add(transaction_n, newOperation);
    }
    public void FindTransactions(LocalDate startDate, LocalDate endDate){
        //выводит инфомацию о транзакциях между заданными датами, включая сами даты
        for (Operation op : this.getOperations()){
            if (op == null || op.getDate() == null) continue;
            if (op.getDate().isAfter(startDate.minusDays(1)) && op.getDate().isBefore(endDate.plusDays(1))){
                op.print();
            }
        }
    }

}
