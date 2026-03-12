package users.http.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import users.business.services.UserService;
import users.http.dtos.CreateUserDTO;
import users.http.dtos.CreateUserResponseDTO;
import users.http.dtos.UserAuthDTO;
import users.http.dtos.UserDetailsResponse;
import users.infra.entity.User;
import users.infra.mapper.UserMapper;
import users.infra.security.JwtUtil;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> create(
            @Valid @RequestBody CreateUserDTO createUserDTO) {

        User user = userMapper.toEntity(createUserDTO);

        User result = userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toCreateUserDtoResponse(result));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserAuthDTO userAuthDTO) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthDTO.email(), userAuthDTO.password())
        );

        String token = jwtUtil.generateToken(authenticate.getName());

        return ResponseEntity.ok(token);
    }

    @GetMapping("/my")
    public ResponseEntity<UserDetailsResponse> showDetails(
            @AuthenticationPrincipal(expression = "username") String email) {

        User result = userService.findBEmail(email);

        return ResponseEntity.ok(userMapper.toUserDetails(result));
    }

}
