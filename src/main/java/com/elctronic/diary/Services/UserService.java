package com.elctronic.diary.Services;

import com.elctronic.diary.UserData;
import com.elctronic.diary.UserDetailImplementation;
import com.elctronic.diary.repo.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserDataRepository userDataRepository;

    @Autowired
    public void setUserDataRepository(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserData userData = userDataRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User '%s' not found", username)
                ));

        return UserDetailImplementation.build(userData);

    }
}
