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
@Table(name = "contact_number")
public class ContactNumber {

    @Id
    @GeneratedValue
    @Column(name = "contact_uuid")
    private UUID contactUUID;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "contact_number")
    private String contactNumber;

    @CreationTimestamp
    @Column(name = "create_time")
    private OffsetDateTime createTime;

    @UpdateTimestamp
    @Column(name = "last_updated_time")
    private OffsetDateTime lastUpdatedTime;

}
