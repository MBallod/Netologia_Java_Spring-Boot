package ru.netology.mballod.operationhistory.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.mballod.operationhistory.domain.Customer;
import ru.netology.mballod.operationhistory.domain.Operation;
import ru.netology.mballod.operationhistory.service.AsyncInputOperationService;
import ru.netology.mballod.operationhistory.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/operations")
public class OperationController {
    private final AsyncInputOperationService service;

    public OperationController(AsyncInputOperationService service) {
        this.service = service;
    }
    @PostMapping
    public void createOperation (@RequestBody Operation operation){
        service.addOperation(operation);
    }

}

