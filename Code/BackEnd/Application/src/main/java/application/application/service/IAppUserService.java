package application.application.service;

import application.application.DTO.AppUserDTO;
import application.application.model.AppUser;

import java.util.List;

public interface IAppUserService {
    AppUser getAppUserByName(String appUserName);
    AppUser getAppUserById(String appUserId);
    AppUserDTO getAppUserDTOById(String appUserId);
    AppUserDTO getAppUserDTOByName(String appUserName);
    List<AppUser> getAllAppUsers();
    List<AppUserDTO> getAppAppUsersDTO();
    AppUser addAppUser(String name, String password, String email, List<String> roleList);
}
