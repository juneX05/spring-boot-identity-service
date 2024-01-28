package com.mwambacodes.identityservice.modules.Domain;

import com.mwambacodes.identityservice.utils.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class DomainSpecification1 implements Specification<DomainEntity> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(
            Root<DomainEntity> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder
    ) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()),
                    criteria.getValue().toString()
            );
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(criteria.getKey()),
                    criteria.getValue().toString()
            );
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.get(criteria.getKey()),
                        "%" + criteria.getValue().toString() + "%"
                );
            } else {
                return criteriaBuilder.equal(
                        root.get(criteria.getKey()),
                        criteria.getValue().toString()
                );
            }
        } else {
            return null;
        }
    }
}
