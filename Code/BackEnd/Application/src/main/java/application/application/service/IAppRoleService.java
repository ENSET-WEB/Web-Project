package application.application.service;


import application.application.model.AppRole;

import java.util.List;

public interface IAppRoleService {
    AppRole getAppRole(String appRoleName);
    AppRole createAppRole(String appRoleName);
    AppRole updateAppRole(AppRole appRole);
    AppRole deleteAppRole(String appRoleName);

    List<AppRole> getAllAppRoles();
}
