package com.example.broker.repository;

import com.example.broker.repository.model.Portfolio;
import com.example.broker.repository.model.Privilege;
import com.example.broker.repository.model.Role;
import com.example.broker.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private PortfolioRepository portfolioRepository;
    private RoleRepository roleRepository;
    private PrivilegeRepository privilegeRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, PortfolioRepository portfolioRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.userRepository = userRepository;
        this.portfolioRepository = portfolioRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    private void loadData() {

        Privilege readPrivilege =
                createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_GUEST", Arrays.asList(readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Role userRole = roleRepository.findByName("ROLE_USER");
        Role guestRole = roleRepository.findByName("ROLE_GUEST");

        List<User> listOfUsers = new ArrayList<>();
        User user;
        user = createUserPortfolio("user1");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = createUserPortfolio("user2");
        user.setRoles(Arrays.asList(guestRole));
        userRepository.save(user);

        user = createUserPortfolio("admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadData();
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

    private User createUserPortfolio(String firstName) {
        User user = new User(firstName, firstName, firstName+"last", firstName + "@email.com", new BCryptPasswordEncoder().encode("pass123"));
        Portfolio portfolio = new Portfolio();
        portfolio.setUser(user);
        user.setPortfolio(portfolio);
        return user;
    }
}
