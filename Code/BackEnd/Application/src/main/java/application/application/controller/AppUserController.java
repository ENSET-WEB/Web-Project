package application.application.controller;

import application.application.DTO.AppUserDTO;
import application.application.model.AppUser;
import application.application.service.IAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
