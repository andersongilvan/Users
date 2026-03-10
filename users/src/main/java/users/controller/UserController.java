package users.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import users.business.UserService;
import users.controller.dtos.CreateUserDTO;
import users.controller.dtos.CreateUserResponseDTO;
import users.controller.dtos.UserAuthDTO;
import users.infra.mapper.UserMapper;
import users.infra.entity.User;
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
    public ResponseEntity<CreateUserResponseDTO> create(@RequestBody CreateUserDTO createUserDTO) {

        User user = userMapper.toEntity(createUserDTO);

        User result = userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toCreateUserDtoResponse(result));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserAuthDTO userAuthDTO) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthDTO.email(), userAuthDTO.password())
        );

        String token = jwtUtil.generateToken(authenticate.getName());

        return ResponseEntity.ok(token);
    }

}
