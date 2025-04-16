package application.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private List<AppRole> appRole = new ArrayList<>();
    @OneToOne(mappedBy = "appUser")
    private Cart cart;
}

