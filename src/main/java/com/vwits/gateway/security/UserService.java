package com.vwits.gateway.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vwits.gateway.model.DBUserDetails;
import com.vwits.gateway.model.Role;
import com.vwits.gateway.model.User;
import com.vwits.gateway.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
      /*  String [] authorities = new String[user.getRole().size()];
        int count=0;
        for (Role role : user.getRole()) {
            //NOTE: normally we dont need to add "ROLE_" prefix. Spring does automatically for us.
            //Since we are using custom token using JWT we should add ROLE_ prefix
            authorities[count] = "ROLE_"+role.getRole();
            count++;
        }*/
      //  return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),getAuthority());
    
        DBUserDetails userDetails = new DBUserDetails(user.getEmail(),user.getPassword(),user.getActive(),
                user.isLoacked(), user.isExpired(),user.isEnabled());
        return userDetails;
    }
    
    private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
}
