package application.application.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class AppUserManageDTO {
    private String id;
    private String name;
    // private String password;
    private String email;
   private String appRoles ;
}


