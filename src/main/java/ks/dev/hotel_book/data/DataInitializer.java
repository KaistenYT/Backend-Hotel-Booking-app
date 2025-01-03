package ks.dev.hotel_book.data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ks.dev.hotel_book.model.Role;
import ks.dev.hotel_book.model.User;
import ks.dev.hotel_book.repository.RoleRepository;
import ks.dev.hotel_book.repository.UserRepository;

import java.util.Set;

@Transactional
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(@SuppressWarnings("null") ApplicationReadyEvent event) {
        Set<String> defaultRoles = Set.of("ROLE_ADMIN" , "ROLE_USER");
        createDefaultRoleIfNotExits(defaultRoles);
                createDefaultUserIfNotExist();
                                createDefaultAdminIfNotExist();
                                                           
                                                               
                                                            }
                                                
                                                
                                    
                                                            
                                                        
 private void createDefaultAdminIfNotExist() {
         Role adminRole = roleRepository.findByName("ROLE_ADMIN")
       .orElseThrow(() -> new RuntimeException("Role not found: ROLE_ADMIN"));
         for (int i = 1; i <= 2; i++) {
       String defaultEmail = "Admin" + i + "@gmail.com";
       if (userRepository.existsByEmail(defaultEmail)) {
         continue;
           }
        User user = new User();
         user.setFirstName("The admin" + i);
      user.setLastName("Last name Admin" + i);
         user.setEmail(defaultEmail);
         user.setPassword(passwordEncoder.encode("123456"));
         user.setRoles(Set.of(adminRole));
         userRepository.save(user);
         System.out.println("Default Admin User" + i + " created successfully");
     }
                                    }
                                
                                
                                
                                
                                
         private void createDefaultUserIfNotExist() {
         Role userRole = roleRepository.findByName("ROLE_USER")
          .orElseThrow(() -> new RuntimeException("Role not found: ROLE_USER"));
   for (int i = 1; i <= 5; i++) {
      String defaultEmail = "user" + i + "@gmail.com";
       if (userRepository.existsByEmail(defaultEmail)) {
           continue;
    }
       User user = new User();
       user.setFirstName("The User" + i);
       user.setLastName("Last name User" + i);
       user.setEmail(defaultEmail);
    user.setPassword(passwordEncoder.encode("123456"));
    user.setRoles(Set.of(userRole));
    userRepository.save(user);
     System.out.println("Default vet User " + i + " created successfully");
   } }
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                            private void createDefaultRoleIfNotExits(Set<String> roles) {
                roles.stream()
                .filter(role -> roleRepository.findByName(role).isEmpty())
                .map(Role:: new).forEach(roleRepository::save);
            }

}
