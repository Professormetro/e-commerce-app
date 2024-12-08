package org.chernov.ecommerce.repository;

import org.chernov.ecommerce.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
