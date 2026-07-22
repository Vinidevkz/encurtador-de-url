package service;


import config.TokenProvider;
import dtos.LoginDTO;
import dtos.TokenResponseDTO;
import dtos.UserDTO;
import entities.RolesEntity;
import entities.User;
import enums.RoleTypes;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import repositories.RolesRepository;
import repositories.UserRepository;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    @Value("${jwt.expiration}")
    private long expiration;

    private final AuthenticationManager authenticationManager;

    public ResponseEntity<HttpStatus> registerUser(@RequestBody @Validated UserDTO user) throws BadRequestException {
        User userExist = (User) userRepository.findByEmailIgnoreCase(user.email()).orElse(null);

        if(userExist != null){
            throw new BadRequestException("O usuário já existe no sistema.");
        }

        try{
            RolesEntity role = rolesRepository.findByNome(RoleTypes.ROLE_USUARIO.name()).orElseGet(()
                    -> rolesRepository.save(RolesEntity.builder()
                    .name(RoleTypes.ROLE_USUARIO.name())
                    .build()
            ));
            //conversao json -> dto
            User newUser = new User();
            newUser.setName(user.name());
            newUser.setEmail(user.email());
            newUser.setRoles(Set.of(role));
            //criptografando a senha:
            newUser.setPassword(passwordEncoder.encode(user.password()));


            userRepository.save(newUser);

            return ResponseEntity.ok().body(HttpStatus.CREATED);

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }



    }

    public TokenResponseDTO login(LoginDTO loginDTO) throws Exception{
        try{

            Optional<User> opUser = userRepository.findByEmailIgnoreCase(loginDTO.email());

            if(opUser.isPresent()){
                User user = opUser.get();

                //manager -> provider - userDetailsService -> passwordEncoderMatches
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha()));
                String token = tokenProvider.generateToken(authentication);

                return new TokenResponseDTO(user.getName(), user.getEmail(), token, expiration);
            }else{
                throw new UsernameNotFoundException("Usuário não encontrado.");
            }


        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Credenciais inválidas.");
        }catch(Exception e){
            throw e;
        }
    }


}