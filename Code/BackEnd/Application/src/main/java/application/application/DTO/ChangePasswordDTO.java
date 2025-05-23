package application.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordDTO {
    String id;
    // String name;
    String oldPassword;
    String newPassword;
}
