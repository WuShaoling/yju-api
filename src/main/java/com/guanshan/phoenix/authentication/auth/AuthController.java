package com.guanshan.phoenix.authentication.auth;

import com.guanshan.phoenix.authentication.security.JwtAuthenticationRequest;
import com.guanshan.phoenix.authentication.security.JwtAuthenticationResponse;
import com.guanshan.phoenix.dao.entity.User;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;

/**
 * Created by Justin on 2017/6/3.
 */

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthController {

    static Logger log = Logger.getLogger(AuthController.class.getName());

    @Value("${token.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录", notes = "登录接口")
    @PostMapping(value = "login")
    public ResponseMessage<ResponseEntity<?>> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        log.info(String.format("User '%s' tries to login.", authenticationRequest.getUsername()));

        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        User user = userService.getUserInfo(authenticationRequest.getUsername());

        log.info(String.format("User '%s' logs on successfully.", authenticationRequest.getUsername()));
        return new ResponseMessage.Success<>(ResponseEntity.ok(
                new JwtAuthenticationResponse(user.getId(), user.getRole(),
                                              authenticationRequest.getUsername(), token)));
    }

//    @RequestMapping(value = "refresh", method = RequestMethod.GET)
//    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
//        String token = request.getHeader(tokenHeader);
//        String refreshedToken = authService.refresh(token);
//        if (refreshedToken == null) {
//            return ResponseEntity.badRequest().body(null);
//        } else {
//            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
//        }
//        return null;
//    }
//
//    @RequestMapping(value = "register", method = RequestMethod.POST)
//    public ResponseEntity<?> register(@RequestBody AuthUserInfo addedUserInfo) throws AuthenticationException{
//        return ResponseEntity.ok(authService.register(addedUserInfo));
//    }

}
