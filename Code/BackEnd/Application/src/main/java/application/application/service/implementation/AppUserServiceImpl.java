package application.application.service.implementation;

import application.application.DTO.AppUserDTO;
import application.application.DTO.AppUserManageDTO;
import application.application.DTO.ChangePasswordDTO;
import application.application.mapper.AppUserMapper;
import application.application.model.AppRole;
import application.application.model.AppUser;
import application.application.model.Cart;
import application.application.model.CartItem;
import application.application.repository.AppRoleRepository;
import application.application.repository.AppUserRepository;
import application.application.repository.CartItemRepository;
import application.application.repository.CartRepository;
import application.application.service.IAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements IAppUserService {

    private BCryptPasswordEncoder passwordEncoder;
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;

    @Override
    public AppUser getAppUserByName(String appUserName) {
        AppUser appUser = appUserRepository.findByName(appUserName);
        if (appUser == null)
            throw new RuntimeException("User not found");
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
        if (appUserRepository.findByName(name) != null)
            throw new RuntimeException("User already exist");

        AppUser appUser = AppUser.builder().name(name).password(passwordEncoder.encode(password)).email(email).build();

        // Warning: Later check the existence of the roles before adding them to User
        AppRole userRole = appRoleRepository.findByRoleName("USER");
        appUser.getAppRole().add(userRole);

        AppUser savedAppUser = appUserRepository.save(appUser);

        Cart cart = Cart.builder().appUser(savedAppUser).build();
        cartRepository.save(cart);

        return AppUserMapper.appUserToDTO(savedAppUser);
    }

    @Override
    public AppUserDTO addNewAppUserByDTO(AppUserDTO appUserDTO) {
        return addNewAppUserByCredentials(appUserDTO.getName(), appUserDTO.getPassword(), appUserDTO.getEmail());
    }

    @Override
    public AppUserDTO updateAppUserPassword(ChangePasswordDTO changePasswordDTO) {
        AppUser appUserToUpdate = appUserRepository.findById(changePasswordDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), appUserToUpdate.getPassword())) {
            throw new RuntimeException("Password Update failed, Old password incorrect");
        }

        appUserToUpdate.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));

        return AppUserMapper.appUserToDTO(appUserRepository.save(appUserToUpdate));
    }

    String appRoleToString(List<AppRole> appRoles) {
        return appRoles.stream().map(AppRole::getRoleName).collect(Collectors.joining(" "));
    }

    @Override
    public List<AppUserManageDTO> getAppUserManageDTOs() {
        List<AppUser> appUsers = appUserRepository.findAll();

        // List<AppUserManageDTO> appUserManageDTOs = new ArrayList<>();
        List<AppUserManageDTO> appUserManageDTOs = appUsers.stream()
                .map(au -> AppUserManageDTO.builder()
                        .email(au.getEmail())
                        .name(au.getName())
                        .id(au.getId())
                        .appRoles(appRoleToString(au.getAppRole()))
                        .build())
                .collect(Collectors.toList());

        return appUserManageDTOs;

    }

    @Override
    public boolean deleteAppUser(String appUserID) {
        AppUser appUser = appUserRepository.findById(appUserID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartList = cartItemRepository.getCartItemsByCart_AppUser_Id(appUserID);

        if (cartList != null) {
            cartItemRepository.deleteAll(cartList);
        }

        Cart userCart = cartRepository.findByAppUser_Id(appUserID);

        if (userCart != null) {
            cartRepository.delete(userCart);
        }

        appUser.getAppRole().clear();
        appUserRepository.save(appUser);

        appUserRepository.delete(appUser);

        return appUserRepository.existsById(appUserID);
    }
}
