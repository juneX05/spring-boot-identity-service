package com.mwambacodes.identityservice.modules.Core.User.Actions.UserList;

import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserListSearch {

    static List<Specification<UserEntity>> specificationList;
    static Map<String, String> params;

    private static void addParam(String paramType, String column) {
        String value = params.get(column) != null ? params.get(column) : "";

        if (value.isEmpty() || value.isBlank()) return;

        if (paramType.equals("string")) {
            System.out.println("Column " + column + " Specification added.");

            specificationList.add(
                    (root, query, criteriaBuilder) -> criteriaBuilder
                            .like(root.get(column), "%" + value + "%")
            );
        } else if (paramType.equals("datetime")) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime fromValue = LocalDateTime.from(formatter.parse(value + " 00:00:00"));
            LocalDateTime toValue = LocalDateTime.from(formatter.parse(value + " 23:59:59"));

            specificationList.add(
                    (root, query, criteriaBuilder) -> criteriaBuilder
                            .between(root.get(column), fromValue, toValue)
            );
        }
    }

    private static void addForeignParam(List<String> joins, String column) {
        int value = Integer.parseInt(params.get(column) != null ? params.get(column) : "0");
        if (value > 0) {
            specificationList.add(
                    (root, query, criteriaBuilder) -> {
                        Join<Object, Object> join = root.join(joins.get(0));
                        for (int i = 1; i < joins.size(); i++) {
                            join = join.join(joins.get(i));
                        }
                        return criteriaBuilder.equal(join.get("id"), value);
                    }
            );
        }
    }

    static Specification<UserEntity> search(Map<String, String> requestParams) {
        params = requestParams;
        specificationList = new ArrayList<>();

        specificationList.add((root, query, criteriaBuilder) -> null);

        addParam("string", "firstName");
        addParam("string", "lastName");
        addParam("string", "email");
        addParam("string", "uuid");
        addParam("datetime", "createdAt");
        addForeignParam(List.of("createdBy"),"createdBy");
        addForeignParam(List.of("userType"),"userType");
        addForeignParam(List.of("userType","role"),"role");
        addForeignParam(List.of("userStatus"),"userStatus");

        Specification<UserEntity> specification
                = Specification.where(specificationList.get(0));

        for (int i = 1; i < specificationList.size(); i++) {
            specification = specification.and(specificationList.get(i));
        }

        return specification;
    }
}
