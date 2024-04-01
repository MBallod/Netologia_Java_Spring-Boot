package ru.netology.mballod.operationhistory.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.mballod.operationhistory.configuration.OperationProperties;
import ru.netology.mballod.operationhistory.domain.Operation;

import java.util.LinkedList;
import java.util.Queue;
@Service
@Getter
@Setter
public class AsyncInputOperationService {
    private final Queue<Operation> queue = new LinkedList<>();
    @Autowired
    private OperationService operationService;
    //private final OperationProperties operationProperties; - пишет, Spring Boot Configuartion files supported in IJ Ultimane

    @PostConstruct
    public void init(){
        this.startProcessing();
    }
    public void startProcessing() {
        Thread t = new Thread(this::processQueue);
        t.start();
    }
    AsyncInputOperationService(OperationService operationService){
        this.operationService = operationService;
    }
    public boolean offerOperation(Operation operation) {
        return queue.offer(operation);
    }
    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
                    System.out.println("Waiting for next operation in queue");
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing operation: " + operation);
                operationService.addOperation(operation);
            }
        }
    }
    public void startAsyncOperationProcessing() {
        Thread t = new Thread() {
            @Override
            public void run() {
                processQueue();
            }
        };
        t.start();
    }

    public boolean addOperation(Operation operation) {
        System.out.println("Operation added for processing " + operation);
        return queue.offer(operation);
    }

    private void processOperation(Operation operation) {
        operationService.saveOperation(operation);
    }
}
