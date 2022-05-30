package cafeteria.main.controller;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cafeteria.main.dto.UserDTO;
import cafeteria.main.mapper.UserMapper;
import cafeteria.main.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(value = "Sign up")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SignUpController {
    
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping(value = "/signup")
    @ApiOperation(value = "Cadastrar user")
    public void signup(@RequestBody UserDTO userDTO) {
        userService.signUpUser(userMapper.convertFromUserDTO(userDTO));
    }
}
