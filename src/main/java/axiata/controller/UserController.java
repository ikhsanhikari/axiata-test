package axiata.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import axiata.dto.UserDTO;
import axiata.dto.UserDataDTO;
import axiata.exception.CustomException;
import axiata.model.User;
import axiata.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {//

        @ApiResponse(code = 400, message = "Something went wrong")
        , //
      @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public ResponseEntity login(//
            @ApiParam("Username") @RequestParam String username, //
            @ApiParam("Password") @RequestParam String password) {
        return ResponseEntity.ok(userService.signin(username, password));
    }

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//

        @ApiResponse(code = 400, message = "Something went wrong")
        , //
      @ApiResponse(code = 403, message = "Access denied")
        , //
      @ApiResponse(code = 422, message = "Username is already in use")
        , //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
        return userService.signup(modelMapper.map(user, User.class));
    }

    @GetMapping("/all-user")
    public Page<User> allUser(@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        return userService.allUser(pageNo, pageSize, sortBy, direction);
    }

    @GetMapping("/find-user")
    public User findUser(@RequestParam(defaultValue = "") String key) {
        return userService.findByEmailOrEmpId(key);
    }

    @PostMapping("/edit")
    public ResponseEntity edit(@RequestBody UserDTO user) {
        return userService.editUser(user);
    }

    @GetMapping("/active")
    public ResponseEntity active(@RequestParam("email") String email) {
        return userService.active(email);
    }

    @GetMapping("/deactive")
    public ResponseEntity deactive(@RequestParam("email") String email) {
        return userService.deactive(email);
    }

    @GetMapping("/change-password")
    public ResponseEntity changePasword(@RequestParam("password") String password,
            @RequestParam("oldpassword") String oldPassword) {
        User user = userService.search(
                SecurityContextHolder.getContext().getAuthentication().getName());
        if (!userService.checkIfValidOldPassword(user, oldPassword)) {
            throw new CustomException("invalid old password",HttpStatus.BAD_REQUEST);
        }
        return userService.changePassword(user, password);
    }

}
