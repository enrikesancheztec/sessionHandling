package mx.tec.lab.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.tec.lab.entity.Role;
import mx.tec.lab.entity.RoleEnum;
import mx.tec.lab.entity.User;
import mx.tec.lab.repository.RoleRepository;
import mx.tec.lab.repository.UserRepository;
import mx.tec.lab.util.SecurityHelper;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;	

	@Autowired
	private SecurityHelper securityHelper;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(username);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), 
				user.getPassword(),
				grantedAuthorities);
	}

	public User save(final User user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(securityHelper.hash(user.getPassword()));
		newUser.setFirstname(user.getFirstname());
		newUser.setLastname(user.getLastname());
		
		Set<Role> roles = new HashSet<>();
		Optional<Role> userRole = roleRepository.findById(RoleEnum.USER.getRoleId());
		
		if (userRole.isPresent()) {
			roles.add(userRole.get());	
		}
		
		newUser.setRoles(roles);
		return userRepository.save(newUser);
	}
}
