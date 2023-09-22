package cat.tecnocampus.tinder2324.application.dto.domain;

import cat.tecnocampus.tinder2324.domain.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//DTO from user lab
public class UserLabDTO {
    private String username;

    private String name;

    private String surname;
    private Set<String> roles = new HashSet<>();

    public UserLabDTO() {
    }

    public UserLabDTO(String username, String name, String surname, Set<Role> roles) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.roles = roles.stream().map(Role::getName).map(Enum::name).collect(Collectors.toSet());
    }

    //Getters

    public String getUsername() {
        return username;
    }
    //Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
