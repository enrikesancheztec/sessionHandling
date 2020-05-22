package mx.tec.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.tec.lab.entity.User;
import mx.tec.lab.security.JsonWebTokenRequest;
import mx.tec.lab.security.JsonWebTokenResponse;
import mx.tec.lab.service.UserService;
import mx.tec.lab.service.UserService;
import mx.tec.lab.util.JsonWebTokenUtil;

@RestController
@RequestMapping("/api/user-management/*")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JsonWebTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JsonWebTokenRequest authenticationRequest)
			throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JsonWebTokenResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
		return ResponseEntity.ok(userService.save(user));
	}	
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (final DisabledException de) {
			throw new Exception("USER_DISABLED", de);
		} catch (final BadCredentialsException bce) {
			throw new Exception("INVALID_CREDENTIALS", bce);
		}
	}
}
