package com.workforce.customer.apis.domain.repo;

import com.workforce.customer.apis.domain.model.entity.ContactNumber;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ContactNumberRepository extends CrudRepository<ContactNumber, UUID> {

    ContactNumber save(ContactNumber contactNumber);
}
