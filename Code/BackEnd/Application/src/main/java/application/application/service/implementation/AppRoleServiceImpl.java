package application.application.service.implementation;

import application.application.model.AppRole;
import application.application.repository.AppRoleRepository;
import application.application.service.IAppRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppRoleServiceImpl implements IAppRoleService {
    private AppRoleRepository appRoleRepository;

    @Override
    public AppRole getAppRole(String appRoleName) {
        return null;
    }

    @Override
    public AppRole createAppRole(String appRoleName) {
        AppRole appRole = AppRole.builder().roleName(appRoleName).build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public AppRole updateAppRole(AppRole appRole) {
        return null;
    }

    @Override
    public AppRole deleteAppRole(String appRoleName) {
        return null;
    }

    @Override
    public List<AppRole> getAllAppRoles() {
        return appRoleRepository.findAll();
    }
}
