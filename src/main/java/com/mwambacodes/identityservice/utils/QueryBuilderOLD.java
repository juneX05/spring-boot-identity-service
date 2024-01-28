package com.mwambacodes.identityservice.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class QueryBuilderOLD<T> {
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<T> criteriaQuery;
    private Root<T> root;
    private Specification<T> query;
    private Boolean firstWhere = true;

    public final QueryBuilderOLD<T> init(Class<T> tClass, EntityManager entityManager) {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(tClass);
        root = criteriaQuery.from(tClass);

        return this;
    }
    private Specification<T> item(String column, Object value, String operator) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setKey(column);
        criteria.setValue(value);
        criteria.setOperator(operator);

        return ((root, query, criteriaBuilder) -> {
            if (criteria.getOperator().equalsIgnoreCase(">")) {
                return criteriaBuilder.greaterThan(
                        root.get(criteria.getKey()),
                        criteria.getValue().toString()
                );
            }
            else if (criteria.getOperator().equalsIgnoreCase("<")) {
                return criteriaBuilder.lessThan(
                        root.get(criteria.getKey()),
                        criteria.getValue().toString()
                );
            }
            else if (criteria.getOperator().equalsIgnoreCase("<=")) {
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(criteria.getKey()),
                        criteria.getValue().toString()
                );
            }
            else if (criteria.getOperator().equalsIgnoreCase(">=")) {
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()),
                        criteria.getValue().toString()
                );
            }
            else if (criteria.getOperator().equalsIgnoreCase("like")) {
                return criteriaBuilder.like(
                        root.get(criteria.getKey()),
                        "%" + criteria.getValue().toString() + "%"
                );
            }
            else if (criteria.getOperator().equalsIgnoreCase("join")) {
                return criteriaBuilder.like(
                        root.get(criteria.getKey()),
                        "%" + criteria.getValue().toString() + "%"
                );
            }
            else {
                return criteriaBuilder.equal(
                        root.get(criteria.getKey()),
                        criteria.getValue().toString()
                );
            }
        });
    }
    private Specification<T> item(String column, Object value) {
        return item(column, value, "=");
    }

    public final QueryBuilderOLD<T> where(String column, Object value, String operator) {
        if (firstWhere) {
            query = Specification.where(item(column, value, operator));
            firstWhere = false;
        }
        else query = query.and(item(column, value, operator));
        return this;
    }

    public final QueryBuilderOLD<T> where(String column, Object value) {
       return where(column, value, "=");
    }

    public final QueryBuilderOLD<T> likeWhere(String column, Object value) {
       return where(column, value, "like");
    }

    public final QueryBuilderOLD<T> andWhere(String column, Object value) {
        query = query.and(item(column, value));
        return this;
    }

    public final QueryBuilderOLD<T> orWhere(String column, Object value) {
        query = query.or(item(column, value));
        return this;
    }

    public Specification<T> build() {
        return query;
    }


}
