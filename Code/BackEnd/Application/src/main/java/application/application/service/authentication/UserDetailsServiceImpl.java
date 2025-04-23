package application.application.service.authentication;

import application.application.model.AppRole;
import application.application.model.AppUser;
import application.application.service.IAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private IAppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService.getAppUserByName(username);
        if (appUser == null) throw new UsernameNotFoundException(username);

        String[] appUserRoles = appUser.getAppRole().stream().map(AppRole::getRoleName).toArray(String []::new);

        return User.builder()
                .username(username)
                .password(appUser.getPassword())
                .roles(appUserRoles)
                .build();
    }
}
