package cat.tecnocampus.tinder2425.security.authentication;

import cat.tecnocampus.tinder2425.domain.UserLab;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserLabDetails implements UserDetails {
    private final UserLab userLab;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserLabDetails(UserLab userLab) {
        this.userLab = userLab;

        this.authorities = userLab.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userLab.getPassword();
    }

    @Override
    public String getUsername() {
        return userLab.getUsername();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserLab{" +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", roles=" + authorities.toString() +
                '}';
    }

}
