package com.example.huku.infrastructure.repository.book;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;


public class BookSpecification<T> {
    public Specification<T> nameContains(String name) {

        return org.thymeleaf.util.StringUtils.isEmpty(name) ? null : (root, query, builder) -> {
            Predicate[] predicates = {
                    builder.like(root.get("title"), "%" + name + "%"),
                    builder.like(root.get("genre"), "%" + name + "%")
            };

            return builder.or(predicates);
        };
    }
}
