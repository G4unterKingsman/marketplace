package ru.gaunter.userService.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gaunter.userService.dto.JwtRequest;
import ru.gaunter.userService.dto.JwtResponse;
import ru.gaunter.userService.dto.UserCreateDto;
import ru.gaunter.userService.service.UserDetailsServiceImpl;
import ru.gaunter.userService.service.UserServiceImpl;
import ru.gaunter.userService.util.JwtTokenUtils;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthJwtToken(@RequestBody JwtRequest jwtRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsername(),
                    jwtRequest.getPassword()
            ));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>("Пользователь не найден",HttpStatus.UNAUTHORIZED) ;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = jwtTokenUtils.createToken(
                userDetails,
                jwtRequest.getEmail(),
                jwtRequest.getPhone(),
                roles);

        return ResponseEntity.ok(new JwtResponse(token));
    }




    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDto userCreateDto){
        if(userService.getByUsername(userCreateDto.getUsername()).isPresent()){
            return new ResponseEntity<>("Пользователь с username " + userCreateDto.getUsername() + " уже существует", HttpStatus.BAD_REQUEST);
        }
       userService.create(userCreateDto);
       return ResponseEntity.ok("Успешная регистрация");
    }
}
