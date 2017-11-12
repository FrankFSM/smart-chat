package com.ralap.chat.config;

import com.ralap.chat.domain.UserModel;
import com.ralap.chat.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by ralap on 2017/11/12.
 */
@Service
public class ChatUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserModel user = userService.getUserByName(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserDetails userDetails = new User(user.getName(), user.getPassword(),
                createAuthority(user.getRoles()));
        return userDetails;

    }

    private List<SimpleGrantedAuthority> createAuthority(String roles) {
        String[] roleArray = roles.split(",");
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        SimpleGrantedAuthority antrority;
        for (String role : roleArray) {
            antrority = new SimpleGrantedAuthority(role);
            authorityList.add(antrority);
        }
        return authorityList;
    }
}
