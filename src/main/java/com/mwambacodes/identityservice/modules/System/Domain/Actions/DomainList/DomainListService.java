package com.mwambacodes.identityservice.modules.System.Domain.Actions.DomainList;

import com.mwambacodes.identityservice.modules.System.Domain.DomainRepository;
import com.mwambacodes.identityservice.modules.System.Domain._Modules.DomainStatus.DomainStatusRepository;
import com.mwambacodes.identityservice.utils.Helpers;
import com.mwambacodes.identityservice.utils.ServiceResult;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DomainListService {
    private final DomainRepository domainRepository;
    private final DomainStatusRepository domainStatusRepository;
    private static final Logger logger = LoggerFactory.getLogger(DomainListService.class);
    private final EntityManager entityManager;
    private final DomainListDTOMapper domainListDTOMapper;

    public ServiceResult init(Map<String, String> params) {
        int page = params.get("page") != null ? Integer.parseInt(params.get("page")) : 1;
        int size = params.get("size") != null ? Integer.parseInt(params.get("size")) : 10;
        var direction = params.get("direction") != null ? params.get("direction") : "asc";
        var sortColumn = params.get("sort") != null ? params.get("sort") : "id";

        Sort sort = Sort.by(sortColumn);

        if (Objects.equals(direction, "asc")) {
            sort.descending();
        } else {
            sort.ascending();
        }

        Pageable pageable = PageRequest.of(page-1, size, sort );

        Page<DomainListDTO> page_data = domainRepository
                .findAll(DomainListSearch.search(params), pageable)
                .map(domainListDTOMapper);

        params.put("page", String.valueOf(page));
        params.put("size", String.valueOf(size));
        params.put("sort", sortColumn);
        params.put("direction", direction);

        Map<String, Object> data = new HashMap<>();
        data.put("content", page_data.getContent());
        data.put("currentPage", page_data.getNumber() + 1);
        data.put("totalRecords", page_data.getTotalElements());
        data.put("totalPages", page_data.getTotalPages());
        data.put("params", params);

        return Helpers.success(data);
    }

}
