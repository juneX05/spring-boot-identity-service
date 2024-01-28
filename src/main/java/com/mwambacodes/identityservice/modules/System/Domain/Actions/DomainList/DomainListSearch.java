package com.mwambacodes.identityservice.modules.System.Domain.Actions.DomainList;

import com.mwambacodes.identityservice.modules.System.Domain.DomainEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DomainListSearch {
    static Specification<DomainEntity> search(Map<String, String> params) {

        String name = params.get("name") != null ? params.get("name") : "";
        String url = params.get("url") != null ? params.get("url") : "";
        String administratorEmail = params.get("administratorEmail") != null ? params.get("administratorEmail") : "";
        int status = Integer.parseInt(params.get("status") != null ? params.get("status") : "0");

        List<Specification<DomainEntity>> specificationList = new ArrayList<>();

        specificationList.add((root, query, criteriaBuilder) -> null);

        if (!name.isEmpty() && !name.isBlank()) {
            specificationList.add(
                    (root, query, criteriaBuilder) -> criteriaBuilder
                            .like(root.get("name"), "%" + name + "%")
            );
        }

        if (!url.isEmpty() && !url.isBlank()) {
            specificationList.add(
                    (root, query, criteriaBuilder) -> criteriaBuilder
                            .like(root.get("url"), "%" + url + "%")
            );
        }

        if (!administratorEmail.isEmpty() && !administratorEmail.isBlank()) {
            specificationList.add(
                    (root, query, criteriaBuilder) -> criteriaBuilder
                            .like(root.get("administratorEmail"), "%" + administratorEmail + "%")
            );
        }

        if (status > 0) {
            specificationList.add(
                    (root, query, criteriaBuilder) -> criteriaBuilder
                            .equal(root.join("domainStatus").get("id"),status)
            );
        }

        Specification<DomainEntity> specification
                = Specification.where(specificationList.get(0));

        for (int i = 1; i < specificationList.size(); i++) {
            specification = specification.and(specificationList.get(i));
        }

        return specification;
    }
}
