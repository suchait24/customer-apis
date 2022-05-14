package com.workforce.customer.apis.domain.model.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<ContactNumber> contactNumberList;

    @OneToMany(mappedBy = "customer")
    private List<Address> addressList;

    @CreationTimestamp
    @Column(name = "create_time")
    private OffsetDateTime createTime;

    @UpdateTimestamp
    @Column(name = "last_updated_time")
    private OffsetDateTime lastUpdatedTime;

}
