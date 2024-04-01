package ru.netology.mballod.operationhistory.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.mballod.operationhistory.domain.Customer;
import ru.netology.mballod.operationhistory.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

       private final CustomerService customerService;

        public CustomerController(CustomerService customerService) {
            this.customerService = customerService;
        }

        @GetMapping
        public CustomersGetResponse getCustomers(){
            List<Customer> customers = customerService.getCustomers();
            List<CustomerDTO> customerDTOS = customers.stream()
                    .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                    .collect(Collectors.toList());
            return new CustomersGetResponse(customerDTOS);
        }
        @GetMapping("{id}")
        public CustomerDTO getCustomer(@PathVariable("id") int id){
            Customer customer = customerService.findCustomerById(id);
            return new CustomerDTO(customer.getId(), customer.getName());
        }
        @PostMapping
        public void createCustomer(@RequestBody Customer customer){
            customerService.addCustomer(customer);
        }
}

