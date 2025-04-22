package application.application.controller;

import application.application.model.AppRole;
import application.application.service.IAppRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appRole")
@AllArgsConstructor
public class AppRoleController {

    private IAppRoleService appRoleService;

    @PostMapping("/addAppRole")
    public ResponseEntity<AppRole> addAppRole(@RequestBody AppRole appRole) {
        AppRole createdAppRole = appRoleService.createAppRole(appRole.getRoleName());
        return new ResponseEntity<>(createdAppRole, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AppRole>> getAppRoles() {
        return new ResponseEntity<>(appRoleService.getAllAppRoles(), HttpStatus.OK);
    }

}
