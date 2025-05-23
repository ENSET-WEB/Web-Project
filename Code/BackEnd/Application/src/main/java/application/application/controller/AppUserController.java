package application.application.controller;

import application.application.DTO.AppUserDTO;
import application.application.DTO.AppUserManageDTO;
import application.application.DTO.ChangePasswordDTO;
import application.application.service.IAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appUser")
@AllArgsConstructor
public class AppUserController {
    private IAppUserService appUserService;

    // @GetMapping
    // @PreAuthorize("hasAuthority('ADMIN')")
    // public ResponseEntity<List<AppUserDTO>> getAllAppUser() {
    // List<AppUserDTO> appUsers = appUserService.getAppAppUsersDTO();
    // return new ResponseEntity<>(appUsers, HttpStatus.OK);
    // }

    @GetMapping("/managed")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AppUserManageDTO>> getAllManageAppUser() {
        List<AppUserManageDTO> appUsers = appUserService.getAppUserManageDTOs();
        return new ResponseEntity<>(appUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AppUserDTO> getAppUserById(@PathVariable String id) {
        AppUserDTO appUser = appUserService.getAppUserDTOById(id);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    // @PostMapping("/addAppUser")
    // ResponseEntity<AppUserDTO> createAppUser(@RequestBody AppUserDTO appUserDTO)
    // {
    // AppUserDTO createdAppUserDTO = appUserService.addNewAppUserByDTO(appUserDTO);
    // return new ResponseEntity<>(createdAppUserDTO, HttpStatus.CREATED);
    // }

    @PostMapping("/addAppUser")
    ResponseEntity<Map<String, String>> createAppUser(@RequestBody AppUserDTO appUserDTO) {
        AppUserDTO createdAppUserDTO = appUserService.addNewAppUserByDTO(appUserDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "AppUser created successfully");
        response.put("id", createdAppUserDTO.getId());
        response.put("username", createdAppUserDTO.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<String> deleteAppUser(@PathVariable String id) {
        if (appUserService.deleteAppUser(id)) {
            return new ResponseEntity<>("AppUser Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error Deleting AppUser", HttpStatus.NOT_FOUND);
    };

    @PostMapping("/changePassword")
    // Change password doesn't need to be authenticated
    // @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        AppUserDTO updatedPasswordAppUser = appUserService.updateAppUserPassword(changePasswordDTO);
        Map<String, String> changedInfoAppUser = new HashMap<>();

        changedInfoAppUser.put("message", "Password Changed Successfully");
        changedInfoAppUser.put("userId", updatedPasswordAppUser.getId());
        return new ResponseEntity<>(changedInfoAppUser, HttpStatus.OK);
    }

}
