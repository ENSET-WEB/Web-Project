package application.application.service;

import application.application.DTO.AppUserDTO;
import application.application.DTO.AppUserManageDTO;
import application.application.DTO.ChangePasswordDTO;
import application.application.model.AppUser;

import java.util.List;

public interface IAppUserService {
    AppUser getAppUserByName(String appUserName);

    AppUser getAppUserById(String appUserId);

    AppUserDTO getAppUserDTOById(String appUserId);

    AppUserDTO getAppUserDTOByName(String appUserName);

    List<AppUser> getAllAppUsers();

    List<AppUserDTO> getAppAppUsersDTO();

    List<AppUserManageDTO> getAppUserManageDTOs();

    AppUserDTO addNewAppUserByCredentials(String name, String password, String email);

    AppUserDTO addNewAppUserByDTO(AppUserDTO appUserDTO);

    AppUserDTO updateAppUserPassword(ChangePasswordDTO changePasswordDTO);

    boolean deleteAppUser(String appUserId);
}
