package ru.netology.mballod.operationhistory.exceptions;


public class CustomerOperationOutOfBoundException extends OperationRuntimeException{
    public static final String MESSAGE = "Exception while trying to save customer %s";
    private int customerId;

    public CustomerOperationOutOfBoundException(int customerId) {
        super();
        this.customerId = customerId;
    }


    @Override
    public String getMessage() {
        return MESSAGE.formatted(customerId);
    }
}
