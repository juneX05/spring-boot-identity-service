package com.mwambacodes.identityservice.modules.Core.User.Actions.UserList;

import com.mwambacodes.identityservice.modules.Core.User.UserRepository;
import com.mwambacodes.identityservice.modules.System.Domain.Actions.DomainList.DomainListDTO;
import com.mwambacodes.identityservice.modules.System.Domain.Actions.DomainList.DomainListDTOMapper;
import com.mwambacodes.identityservice.modules.System.Domain.Actions.DomainList.DomainListSearch;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserListService {
    private final UserRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(UserListService.class);
    private final EntityManager entityManager;
    private final UserListDTOMapper mapper;

    public ServiceResult init(Map<String, String> params) {
        int page = params.get("page") != null ? Integer.parseInt(params.get("page")) : 1;
        int size = params.get("size") != null ? Integer.parseInt(params.get("size")) : 10;
        String direction = params.get("direction") != null ? params.get("direction") : "desc";
        String sortColumn = params.get("sort") != null ? params.get("sort") : "id";

        Sort sort = Sort.by(sortColumn);

        if (Objects.equals(direction, "desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        Pageable pageable = PageRequest.of(page-1, size, sort );

        Page<UserListDTO> page_data = repository
                .findAll(UserListSearch.search(params), pageable)
                .map(mapper);

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
