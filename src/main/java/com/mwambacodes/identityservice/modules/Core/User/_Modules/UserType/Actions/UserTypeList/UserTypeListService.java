package com.mwambacodes.identityservice.modules.Core.User._Modules.UserType.Actions.UserTypeList;

import com.mwambacodes.identityservice.modules.Core.User._Modules.UserType.UserTypeRepository;
import com.mwambacodes.identityservice.utils.Helpers;
import com.mwambacodes.identityservice.utils.ServiceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTypeListService {

    private final UserTypeListDTOMapper userTypeListDTOMapper;
    private final UserTypeRepository userTypeRepository;

    public ServiceResult init() {
        List<UserTypeListDTO> userTypes = userTypeRepository.findAll().stream()
                .map(userTypeListDTOMapper)
                .toList();
        return Helpers.success(userTypes);
    }

}
