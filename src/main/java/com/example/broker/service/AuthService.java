package com.example.broker.service;

import com.example.broker._security.JwtTokenUtil;
import com.example.broker._security.UserDetailsImpl;
import com.example.broker._security.UserDetailsServiceImpl;
import com.example.broker.repository.PrivilegeRepository;
import com.example.broker.repository.RoleRepository;
import com.example.broker.repository.UserRepository;
import com.example.broker.repository.model.Privilege;
import com.example.broker.repository.model.Role;
import com.example.broker.repository.model.User;
import com.example.broker.repository.model.login.JwtRequest;
import com.example.broker.repository.model.login.JwtResponse;
import com.example.broker.repository.model.signup.MessageResponse;
import com.example.broker.repository.model.signup.SignUpRequest;
import com.example.broker.web.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private MessageSource messageSource;
    private HttpServletRequest request;
    private PrivilegeRepository privilegeRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public AuthService(
            AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, RoleRepository roleRepository,
            UserRepository userRepository, PasswordEncoder passwordEncoder, MessageSource messageSource,
            HttpServletRequest request, PrivilegeRepository privilegeRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
        this.request = request;
        this.privilegeRepository = privilegeRepository;
    }

    public ResponseEntity<?> authenticate(JwtRequest authenticationRequest) throws Exception {
        Authentication authentication = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @Override
    public ResponseEntity<?> register(SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse(messageSource.getMessage("error.username.taken", null, request.getLocale())));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in user!"));
        }
        // Create user account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()));
        Role role = roleRepository.findByName(signUpRequest.getRole());
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role);
        user.setRoles(userRoles);

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
