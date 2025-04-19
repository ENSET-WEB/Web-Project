package application.application.DTO;

import application.application.model.AppRole;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class AppUserDTO {
    private String id;
    private String name;
    private String password;
    private String email;
//    private List<AppRole> appRole = new ArrayList<>();
}


