package ru.netology.mballod.operationhistory.domain;
import lombok.Getter;
import lombok.Setter;
import ru.netology.mballod.operationhistory.service.CustomerService;
import ru.netology.mballod.operationhistory.service.OperationService;
import ru.netology.mballod.operationhistory.service.StatementService;

import java.io.Serializable;


@Setter
@Getter
public class OperationData implements Serializable {
    public static final int N_OF_CUSTOMERS = 2;
    public static final int N_OF_TRANSACTIONS = 2;
    private CustomerService customers = new CustomerService();
    private OperationService operations = new OperationService();
    private StatementService statement = new StatementService();

    public OperationService getOperations(int clientId){ // Все операции клиента из statement
        OperationService result = new OperationService();
        result.setOperations(this.statement.getStorage().get(clientId));  // storage - мапа, где по ключу clientId находятся все транзакции клиента
        return result;

    }

}
