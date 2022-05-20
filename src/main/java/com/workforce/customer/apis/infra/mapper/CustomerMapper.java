package com.workforce.customer.apis.infra.mapper;

import com.workforce.customer.apis.domain.model.entity.Address;
import com.workforce.customer.apis.domain.model.entity.ContactNumber;
import com.workforce.customer.apis.domain.model.entity.Customer;
import com.workforce.customer.apis.domain.model.framework.AddressResponse;
import com.workforce.customer.apis.domain.model.framework.ContactNumberResponse;
import com.workforce.customer.apis.domain.model.framework.CustomerDetailResponse;
import com.workforce.customer.apis.infra.model.AddressDTO;
import com.workforce.customer.apis.infra.model.ContactNumberDTO;
import com.workforce.customer.apis.infra.model.CustomerDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CustomerMapper {

    Customer customerFrom(CustomerDTO customerDTO);

    @Mapping(target = "contactNumber", source = "contactNumberDTO.contactNumber")
    @Mapping(target = "customer", source = "customer")
    ContactNumber contactNumberFrom(ContactNumberDTO contactNumberDTO, Customer customer);

    @Mapping(target = "street", source = "addressDTO.street")
    @Mapping(target = "city", source = "addressDTO.city")
    @Mapping(target = "state", source = "addressDTO.state")
    @Mapping(target = "country", source = "addressDTO.country")
    @Mapping(target = "landmark", source = "addressDTO.landmark")
    @Mapping(target = "pincode", source = "addressDTO.pincode")
    @Mapping(target = "customer", source = "customer")
    Address addressFrom(AddressDTO addressDTO, Customer customer);


    CustomerDetailResponse fromCustomer(Customer customer);
    AddressResponse fromAddress(Address address);
    ContactNumberResponse fromContactNumber(ContactNumber contactNumber);

    @AfterMapping
    default void updateValues(@MappingTarget CustomerDetailResponse customerDetailResponse,
                              Customer customer) {

        List<AddressResponse> addressResponseList =
          customer.getAddressList().stream().map(this::fromAddress).collect(Collectors.toList());

        List<ContactNumberResponse> contactNumberResponseList =
                customer.getContactNumberList().stream()
                .map(this::fromContactNumber).collect(Collectors.toList());

        customerDetailResponse.setAddressList(addressResponseList);
        customerDetailResponse.setContactNumberList(contactNumberResponseList);
    }

}
