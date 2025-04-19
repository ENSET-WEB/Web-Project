package application.application.service;

import application.application.model.AppUser;

import java.util.List;

public interface IAppUserService {
    AppUser getAppUserByName(String appUserName);
    AppUser getAppUserById(String appUserId);
    List<AppUser> getAllAppUsers();
    AppUser addAppUser(String name, String password, String email, List<String> roleList);
}
