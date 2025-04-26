package application.application.controller;

import application.application.DTO.AppUserDTO;
import application.application.DTO.CartDTO;
import application.application.service.IAppUserService;
import application.application.service.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appUser")
@AllArgsConstructor
public class AppUserController {
    private IAppUserService appUserService;
    private ICartService cartService;

    @GetMapping
    public ResponseEntity<List<AppUserDTO>> getAllAppUser() {
        List<AppUserDTO> appUsers = appUserService.getAppAppUsersDTO();
        return new ResponseEntity<>(appUsers, HttpStatus.OK);
    }

    @GetMapping("/{appUserId}/cart")
    public ResponseEntity<CartDTO> getCartByAppUserId(
            @PathVariable String appUserId) {
        return new ResponseEntity<>(cartService.getCartDTOByAppUserId(appUserId),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getAppUserById(@PathVariable String id) {
        AppUserDTO appUser = appUserService.getAppUserDTOById(id);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @PostMapping("/addAppUser")
    ResponseEntity<AppUserDTO> createAppUser(@RequestBody AppUserDTO appUserDTO) {
        AppUserDTO createdAppUserDTO = appUserService.addNewAppUserByDTO(appUserDTO);
        return new ResponseEntity<>(createdAppUserDTO, HttpStatus.CREATED);
    }

    // @PostMapping("/deleteAppUser")
    // ResponseEntity<AppUserDTO> deleteAppUser(@RequestBody AppUserDTO appUserDTO)
    // {
    // return null;
    // };

}
