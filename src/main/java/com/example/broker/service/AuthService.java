package com.example.broker.service;

import com.example.broker._security.JwtTokenUtil;
import com.example.broker._security.UserDetailsImpl;
import com.example.broker._security.UserDetailsServiceImpl;
import com.example.broker.repository.RoleRepository;
import com.example.broker.repository.UserRepository;
import com.example.broker.repository.model.Role;
import com.example.broker.repository.model.User;
import com.example.broker.repository.model.login.JwtRequest;
import com.example.broker.repository.model.login.JwtResponse;
import com.example.broker.repository.model.signup.MessageResponse;
import com.example.broker.repository.model.signup.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in user!"));
        }

        // Create user account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()));
        List<Role> roles = (List<Role>) signUpRequest.getRoles();
        List<Role> userRoles = new ArrayList<>();
        roles.forEach(role -> {
            Role userRole = roleRepository.findByName(role.getName());
            userRoles.add(userRole);
        });

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
