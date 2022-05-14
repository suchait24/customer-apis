package com.workforce.customer.apis.domain.repo;

import com.workforce.customer.apis.domain.model.entity.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AddressRepository extends CrudRepository<Address, UUID> {

    Address save(Address address);
}
