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
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository){
        return args -> {
            if(userRepository.findByUserName("admin").isEmpty()) {
                User user = new User();
                var roles = new HashSet<RoleDetail>();

                //Save userRoleType to UserRole Entity
                RoleDetail userRole = new RoleDetail();
                userRole.setRoleType(Role.ADMIN.name());
                roleRepository.save(userRole);
                roles.add(userRole);

                //create admin acc
                user.setUserName("admin");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setRoles(roles);
                user.setStatus(true);
                userRepository.save(user);
                log.warn("admin user has been created!");
            }

        };
    }

}
