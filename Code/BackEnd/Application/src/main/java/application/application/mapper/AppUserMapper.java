package application.application.mapper;


import application.application.DTO.AppUserDTO;
import application.application.model.AppUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppUserMapper {
    public static AppUserDTO appUserToDTO(AppUser appUser) {
        return AppUserDTO.builder()
                .id(appUser.getId())
                .name(appUser.getName())
                .email(appUser.getEmail())
//                Test purpose, deleter it later
                .password(appUser.getPassword())
                .appRole(appUser.getAppRole())
                .build();
    }

    public static List<AppUserDTO> appUserListToAppUserDTOList(List<AppUser> appUserList) {
        return appUserList.stream().map(AppUserMapper::appUserToDTO).collect(Collectors.toList());
    }
}
