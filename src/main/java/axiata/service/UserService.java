package axiata.service;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;

import axiata.dto.TokenResponseDTO;
import axiata.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import axiata.exception.CustomException;
import axiata.model.User;
import axiata.repository.UserRepository;
import axiata.security.JwtTokenProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenResponseDTO signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenProvider.createToken(username, userRepository.findByEmail(username).getRoles());
            return new TokenResponseDTO(token, "200", "success create token");
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private String lastRowUser() {
        User last = userRepository.findTopByOrderByIdDesc();
        if (last == null) {
            return "001";
        } else {
            String newKey = "00000" + (last.getId() + 1);
            newKey = newKey.substring(newKey.length() - 3, newKey.length());
            return newKey;
        }
    }

    public String signup(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setEmployeeNumber(Calendar.getInstance().get(Calendar.YEAR) + lastRowUser());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public User search(String username) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByEmail(username).getRoles());
    }

    public Page<User> allUser(int pageNo, int pageSize, String sortBy, String direction) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (direction.equalsIgnoreCase("DESC")) {
            dir = Sort.Direction.DESC;
        }
        Pageable p = new PageRequest(pageNo, pageSize, new Sort(new Sort.Order(dir, sortBy)));
        return userRepository.findAll(p);
    }

    public User findByEmailOrEmpId(String key) {
        User data = userRepository.findByEmailOrEmpId(key);
        if (data == null) {
            throw new CustomException("user not found", HttpStatus.NOT_FOUND);
        }
        return data;
    }

    public ResponseEntity editUser(UserDTO user) {
        if (user.getEmail() == null) {
            throw new CustomException("email cannot null", HttpStatus.BAD_REQUEST);
        }
        User check = userRepository.findByEmail(user.getEmail());
        if (check == null) {
            throw new CustomException("email not found", HttpStatus.NOT_FOUND);
        }
        check.setUsername(user.getUsername());
        check.setBirthPlace(user.getBirthPlace());
        check.setBirthDate(user.getBirthDate());
        userRepository.save(check);
        return ResponseEntity.ok("Success Update Data");
    }

    public ResponseEntity active(String email) {
        User check = userRepository.findByEmail(email);
        if (check == null) {
            throw new CustomException("email not found", HttpStatus.NOT_FOUND);
        }
        check.setIsActive(Boolean.TRUE);
        userRepository.save(check);
        return ResponseEntity.ok("Email " + email + " Acitve");
    }

    public ResponseEntity deactive(String email) {
        User check = userRepository.findByEmail(email);
        if (check == null) {
            throw new CustomException("email not found", HttpStatus.NOT_FOUND);
        }
        check.setIsActive(Boolean.FALSE);
        userRepository.save(check);
        return ResponseEntity.ok("Email " + email + " InAcitve");
    }

    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        boolean result = passwordEncoder.matches(oldPassword, user.getPassword());
        if (result) {
            return true;
        }
        return false;
    }

    public ResponseEntity changePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return ResponseEntity.ok("Success change password!");
    }

}
