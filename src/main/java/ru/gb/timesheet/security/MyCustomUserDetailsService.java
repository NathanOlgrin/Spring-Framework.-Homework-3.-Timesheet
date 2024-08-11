package ru.gb.timesheet.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.timesheet.model.User;
import ru.gb.timesheet.repository.UserReposiroty;
import ru.gb.timesheet.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MyCustomUserDetailsService implements UserDetailsService {


//    В нашем случае юзеры хранятся в БД в таблице UserReposiroty
//    Строго говоря, в этой реализации UserDetailsService можно загружать данные о пользователе из любого источника:
//    внешний auth-service, ldap-service, ...

    private final UserReposiroty userReposiroty;
    private final UserRoleRepository userRoleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userReposiroty.findByLogin(username).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден"));

        List<SimpleGrantedAuthority> userRoles = userRoleRepository.findByUserId(user.getId()).stream().map(it -> new SimpleGrantedAuthority(it.getRoleName())).toList();

                //        TODO: использовать роли пользователя
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                userRoles);
    }
}
