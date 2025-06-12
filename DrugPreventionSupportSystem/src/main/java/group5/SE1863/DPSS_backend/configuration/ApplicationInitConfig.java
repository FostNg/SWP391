package group5.SE1863.DPSS_backend.configuration;

import group5.SE1863.DPSS_backend.entity.RoleDetail;
import group5.SE1863.DPSS_backend.entity.User;
import group5.SE1863.DPSS_backend.enums.Role;
import group5.SE1863.DPSS_backend.repository.RoleRepository;
import group5.SE1863.DPSS_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {

    private final PasswordEncoder passwordEncoder;

    // This class is used to initialize the application, such as creating an admin account.
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
//            if (userRepository.findByUserName("admin").isEmpty()) {
//                User user = new User();
//                var roles = new HashSet<RoleDetail>();
//
//                // Save userRoleType to UserRole Entity
//                RoleDetail userRole = new RoleDetail();
//                userRole.setRoleType(Role.ADMIN.name());
//                roleRepository.save(userRole);
//                roles.add(userRole);
//
//                // Create admin account
//                user.setUserName("admin");
//                user.setPassword(passwordEncoder.encode("admin"));
//                user.setRoles(roles);
//                user.setStatus(true);
//                userRepository.save(user);
//                log.warn("Admin user has been created!");
//            }
            if (roleRepository.findByRoleType(Role.ADMIN.name()).isEmpty()) {
                RoleDetail adminRole = new RoleDetail();
                adminRole.setRoleType(Role.ADMIN.name());
                roleRepository.save(adminRole);
                log.info("Admin role has been created");
            }
            if (roleRepository.findByRoleType(Role.USER.name()).isEmpty()) {
                RoleDetail userRole = new RoleDetail();
                userRole.setRoleType(Role.USER.name());
                roleRepository.save(userRole);
                log.info("User role has been created");
            }
            // Check and create default admin user if it doesn't exist
            if (userRepository.findByUserName("admin").isEmpty()) {
                var roles = new HashSet<RoleDetail>();
                roles.add(roleRepository.findByRoleType(Role.ADMIN.name()).orElseThrow(() -> new RuntimeException("Admin role not found")));

                User user = User.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin"))
                        .status(true)
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.info("Admin user has been created with default password: admin, please change it");
            }
        };
    }

}
