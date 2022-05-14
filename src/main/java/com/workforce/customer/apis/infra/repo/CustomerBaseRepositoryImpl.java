package com.workforce.customer.apis.infra.repo;

import com.workforce.customer.apis.domain.model.criteria.CustomerFilterCriteria;
import com.workforce.customer.apis.domain.model.entity.Address;
import com.workforce.customer.apis.domain.model.entity.ContactNumber;
import com.workforce.customer.apis.domain.model.entity.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class CustomerBaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> {

    private final EntityManager entityManager;

    public CustomerBaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public Long retrieveCustomerCount(CustomerFilterCriteria customerFilterCriteria) {

        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Customer.class);
        var root = query.from(Customer.class);
        root.alias("customer_alias");

        var predicates = new ArrayList<>();

        if(nonNull(customerFilterCriteria) && !CollectionUtils.isEmpty(customerFilterCriteria.getCustomerIds())) {

            var customerIdPredicate = (root.get("customerId")).in(customerFilterCriteria.getCustomerIds());
            predicates.add(customerIdPredicate);
        }

        if(nonNull(customerFilterCriteria) && !CollectionUtils.isEmpty(customerFilterCriteria.getCustomerNames())) {

            var customerNamePredicate = (root.get("name")).in(customerFilterCriteria.getCustomerNames());
            predicates.add(customerNamePredicate);
        }

        if(nonNull(customerFilterCriteria) && customerFilterCriteria.getCreateStartDate() != null) {

            var customerCreateStartDatePredicate =
                    criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), customerFilterCriteria.getCreateStartDate());
            predicates.add(customerCreateStartDatePredicate);

        }

        if(nonNull(customerFilterCriteria) && customerFilterCriteria.getCreateEndDate()!= null) {

            var customerCreateStartDatePredicate =
                    criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), customerFilterCriteria.getCreateStartDate());
            predicates.add(customerCreateStartDatePredicate);

        }

        if(nonNull(customerFilterCriteria) && customerFilterCriteria.getLastUpdatedStartDate() != null) {

            var customerCreateStartDatePredicate =
                    criteriaBuilder.greaterThanOrEqualTo(root.get("lastUpdatedTime").as(Date.class), customerFilterCriteria.getLastUpdatedStartDate());
            predicates.add(customerCreateStartDatePredicate);

        }

        if(nonNull(customerFilterCriteria) && customerFilterCriteria.getLastUpdatedEndDate()!= null) {

            var customerCreateStartDatePredicate =
                    criteriaBuilder.lessThanOrEqualTo(root.get("lastUpdatedTime").as(Date.class), customerFilterCriteria.getLastUpdatedEndDate());
            predicates.add(customerCreateStartDatePredicate);

        }

        if(nonNull(customerFilterCriteria) && !CollectionUtils.isEmpty(customerFilterCriteria.getContactNumbers())) {

            Join<Customer, ContactNumber> customerContactNumberJoin =
                    root.join("contactNumberList", JoinType.INNER);

            var customerContactNumberPredicate =
                    criteriaBuilder.equal(customerContactNumberJoin.get("contactNumber"), customerFilterCriteria.getContactNumbers());

            predicates.add(customerContactNumberPredicate);
        }

        if(nonNull(customerFilterCriteria) && !CollectionUtils.isEmpty(customerFilterCriteria.getCitys())) {

            Join<Customer, Address> customerAddressJoin =
                    root.join("addressList", JoinType.INNER);

            var customerAddressCityPredicate =
                    criteriaBuilder.equal(customerAddressJoin.get("city"), customerFilterCriteria.getCitys());

            predicates.add(customerAddressCityPredicate);
        }

        //TODO: add more predicates for other filters

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Customer> countRoot = countQuery.from(Customer.class);
        countRoot.alias("customer_alias");

        return entityManager.createQuery(
                countQuery.select(criteriaBuilder.count(countRoot)).
                        where(predicates.toArray(new Predicate[] {}))
        ).getSingleResult();

    }

    public List<Customer> retrieveCustomers(CustomerFilterCriteria customerFilterCriteria) {

        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Customer.class);
        var root = query.from(Customer.class);

        var predicates = new ArrayList<>();

        if(nonNull(customerFilterCriteria) && !CollectionUtils.isEmpty(customerFilterCriteria.getCustomerIds())) {

            var customerIdPredicate = (root.get("customerId")).in(customerFilterCriteria.getCustomerIds());
            predicates.add(customerIdPredicate);
        }

        if(nonNull(customerFilterCriteria) && !CollectionUtils.isEmpty(customerFilterCriteria.getCustomerNames())) {

            var customerNamePredicate = (root.get("name")).in(customerFilterCriteria.getCustomerNames());
            predicates.add(customerNamePredicate);
        }

        if(nonNull(customerFilterCriteria) && customerFilterCriteria.getCreateStartDate() != null) {

            var customerCreateStartDatePredicate =
                    criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), customerFilterCriteria.getCreateStartDate());
            predicates.add(customerCreateStartDatePredicate);

        }

        if(nonNull(customerFilterCriteria) && customerFilterCriteria.getCreateEndDate()!= null) {

            var customerCreateStartDatePredicate =
                    criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), customerFilterCriteria.getCreateStartDate());
            predicates.add(customerCreateStartDatePredicate);

        }

        if(nonNull(customerFilterCriteria) && customerFilterCriteria.getLastUpdatedStartDate() != null) {

            var customerCreateStartDatePredicate =
                    criteriaBuilder.greaterThanOrEqualTo(root.get("lastUpdatedTime").as(Date.class), customerFilterCriteria.getLastUpdatedStartDate());
            predicates.add(customerCreateStartDatePredicate);

        }

        if(nonNull(customerFilterCriteria) && customerFilterCriteria.getLastUpdatedEndDate()!= null) {

            var customerCreateStartDatePredicate =
                    criteriaBuilder.lessThanOrEqualTo(root.get("lastUpdatedTime").as(Date.class), customerFilterCriteria.getLastUpdatedEndDate());
            predicates.add(customerCreateStartDatePredicate);

        }

        if(nonNull(customerFilterCriteria) && !CollectionUtils.isEmpty(customerFilterCriteria.getContactNumbers())) {

            Join<Customer, ContactNumber> customerContactNumberJoin =
                    root.join("contactNumberList", JoinType.INNER);

            var customerContactNumberPredicate =
                    criteriaBuilder.equal(customerContactNumberJoin.get("contactNumber"), customerFilterCriteria.getContactNumbers());

            predicates.add(customerContactNumberPredicate);
        }

        if(nonNull(customerFilterCriteria) && !CollectionUtils.isEmpty(customerFilterCriteria.getCitys())) {

            Join<Customer, Address> customerAddressJoin =
                    root.join("addressList", JoinType.INNER);

            var customerAddressCityPredicate =
                    criteriaBuilder.equal(customerAddressJoin.get("city"), customerFilterCriteria.getCitys());

            predicates.add(customerAddressCityPredicate);
        }

        //TODO: add more predicates for other filters

        if(!CollectionUtils.isEmpty(predicates)) {
            query.where(predicates.toArray(new Predicate[1]));
        }

        var typedQuery = entityManager.createQuery(query);
        return typedQuery.setFirstResult(customerFilterCriteria.getPage() * customerFilterCriteria.getCount())
                .setMaxResults(customerFilterCriteria.getCount())
                .getResultList();
    }

}
