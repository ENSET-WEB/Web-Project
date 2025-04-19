package application.application.controller;

import application.application.DTO.AppUserDTO;
import application.application.service.IAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appUser")
@AllArgsConstructor
public class AppUserController {
    private IAppUserService appUserService;

    @GetMapping
    List<AppUserDTO> getAllAppUser() {
        return appUserService.getAppAppUsersDTO();
    }

    @GetMapping("/{id}")
    AppUserDTO getAppUserById(@PathVariable String id) {
        return appUserService.getAppUserDTOById(id);
    }


    @PostMapping("/add")
    ResponseEntity<AppUserDTO> createAppUser(@RequestBody AppUserDTO appUserDTO) {
        AppUserDTO createdAppUserDTO = appUserService.addNewAppUserByDTO(appUserDTO);
        return new ResponseEntity<>(createdAppUserDTO, HttpStatus.CREATED);
    }
}
