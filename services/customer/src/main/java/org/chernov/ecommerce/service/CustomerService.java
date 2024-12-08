package org.chernov.ecommerce.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.chernov.ecommerce.dto.CustomerRequest;
import org.chernov.ecommerce.dto.CustomerMapper;
import org.chernov.ecommerce.dto.CustomerResponse;
import org.chernov.ecommerce.entity.Customer;
import org.chernov.ecommerce.exception.CustomerNotFoundException;
import org.chernov.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public String createCustomer(@Valid CustomerRequest request) {
        var customer = customerRepository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void update(@Valid CustomerRequest request) {
        var customer = customerRepository.findById(request.id()).orElseThrow(() -> new CustomerNotFoundException(
                format("Cannot update customer:: No customer found with %s", request.id())
        ));

        mergerCustomer(customer, request);
        customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }

        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }

        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }

        if(request.address() != null){
            customer.setAddress(request.address());
        }

    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(mapper::toCustomerResponse)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update customer:: No customer found with %s", customerId)));



    }

    public void deleteById(String customerId) {

        customerRepository.deleteById(customerId);

    }
}
