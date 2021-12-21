package axiata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import axiata.model.User;
import axiata.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import axiata.model.Role;

@SpringBootApplication
public class JwtAuthServiceApp implements CommandLineRunner {
    
    

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(JwtAuthServiceApp.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... params) throws Exception {
        
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@email.com");
        admin.setBirthDate(new Date());
        admin.setBirthPlace("Cirebon");
        admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

        userService.signup(admin);

        User client = new User();
        client.setUsername("client");
        client.setPassword("client");
        client.setEmail("client@email.com");
        client.setBirthDate(new Date());
        client.setBirthPlace("Padang Sidimpuan");
        client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

        userService.signup(client);

        User admin1 = new User();
        admin1.setUsername("admin1");
        admin1.setPassword("admin1");
        admin1.setEmail("admin1@email.com");
        admin1.setIsActive(Boolean.FALSE);
        admin1.setBirthDate(new Date());
        admin1.setBirthPlace("Cirebon");
        admin1.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

        userService.signup(admin1);
    }

}
