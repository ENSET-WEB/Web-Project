package application.application.samples;

import application.application.model.AppRole;
import application.application.repository.AppRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AppRoleSamples {
    private AppRoleRepository roleRepository;

    public List<AppRole> getRoles() {
        List<AppRole> roles = new ArrayList<>();
        roles.add(AppRole.builder().roleName("ADMIN").build());
        roles.add(AppRole.builder().roleName("USER").build());
        return roles;
    }

    public void addSamples() {
        List<AppRole> roles = getRoles();
        roleRepository.saveAll(roles);
    }
}
