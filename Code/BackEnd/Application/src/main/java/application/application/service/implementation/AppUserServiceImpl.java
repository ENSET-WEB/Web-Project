package application.application.service.implementation;

import application.application.model.AppRole;
import application.application.model.AppUser;
import application.application.model.Cart;
import application.application.repository.AppRoleRepository;
import application.application.repository.AppUserRepository;
import application.application.repository.CartRepository;
import application.application.service.IAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements IAppUserService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private CartRepository cartRepository;

//    Add Exception later
    @Override
    public AppUser getAppUserByName(String appUserName) {
        return appUserRepository.findByName(appUserName);
    }

    @Override
    public AppUser getAppUserById(String appUserId) {
        return appUserRepository.findById(appUserId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser addAppUser(String name, String password, String email, List<String> roleList) {
        if (appUserRepository.findByName(name) != null) throw new RuntimeException("The user is already signed");

//        Important: Add Password encryption
        AppUser appUser = AppUser.builder().name(name).password(password).email(email).build();

//        Warning: Later check the existence of the roles before adding them to User
        List<AppRole> appUserRoleList = appUser.getAppRole();
        roleList.forEach(roleName -> appUserRoleList.add(appRoleRepository.findByRoleName(roleName)));
        appUser.setAppRole(appUserRoleList);

        AppUser savedAppUser = appUserRepository.save(appUser);

        Cart cart = Cart.builder().appUser(savedAppUser).build();
        cartRepository.save(cart);

        return savedAppUser;
    }
}
