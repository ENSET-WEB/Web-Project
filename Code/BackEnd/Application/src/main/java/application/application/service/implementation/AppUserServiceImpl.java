package application.application.service.implementation;

import application.application.DTO.AppUserDTO;
import application.application.mapper.AppUserMapper;
import application.application.model.AppRole;
import application.application.model.AppUser;
import application.application.model.Cart;
import application.application.repository.AppRoleRepository;
import application.application.repository.AppUserRepository;
import application.application.repository.CartRepository;
import application.application.service.IAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements IAppUserService {

    private BCryptPasswordEncoder passwordEncoder;
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private CartRepository cartRepository;

    //    Add Exception later
    @Override
    public AppUser getAppUserByName(String appUserName) {
        AppUser appUser = appUserRepository.findByName(appUserName);
        if (appUser == null) throw new RuntimeException("User not found");
        return appUser;
    }

    @Override
    public AppUser getAppUserById(String appUserId) {
        return appUserRepository.findById(appUserId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public AppUserDTO getAppUserDTOById(String appUserId) {
        return AppUserMapper.appUserToDTO(getAppUserById(appUserId));
    }

    @Override
    public AppUserDTO getAppUserDTOByName(String appUserName) {
        return AppUserMapper.appUserToDTO(getAppUserByName(appUserName));
    }

    @Override
    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public List<AppUserDTO> getAppAppUsersDTO() {
        return AppUserMapper.appUserListToAppUserDTOList(getAllAppUsers());
    }

    @Override
    public AppUserDTO addNewAppUserByCredentials(String name, String password, String email) {
        if (appUserRepository.findByName(name) != null) throw new RuntimeException("User already exist");

//        Important: Add Password encryption
        AppUser appUser = AppUser.builder().name(name).password(passwordEncoder.encode(password)).email(email).build();

//        Warning: Later check the existence of the roles before adding them to User
        AppRole userRole = appRoleRepository.findByRoleName("USER");
        appUser.getAppRole().add(userRole);

        AppUser savedAppUser = appUserRepository.save(appUser);

        Cart cart = Cart.builder().appUser(savedAppUser).build();
        cartRepository.save(cart);

        return AppUserMapper.appUserToDTO(savedAppUser);
    }

    @Override
    public AppUserDTO addNewAppUserByDTO(AppUserDTO appUserDTO){
     return addNewAppUserByCredentials(appUserDTO.getName(), appUserDTO.getPassword(), appUserDTO.getEmail());
    }
}
