package com.workforce.customer.apis.domain.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {


    @Id
    @GeneratedValue
    @Column(name = "address_uuid")
    private UUID addressUUID;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "landmark")
    private String landmark;

    @CreationTimestamp
    @Column(name = "create_time")
    private OffsetDateTime createTime;

    @UpdateTimestamp
    @Column(name = "last_updated_time")
    private OffsetDateTime lastUpdatedTime;
}
