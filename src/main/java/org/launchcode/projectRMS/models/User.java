package org.launchcode.projectRMS.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User{

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @NotEmpty
    @Size(min = 1, max =25)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 1, max =25)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, message = "Password must be at-least 6 characters")
    private String password;

    @NotNull(message = "Password don't match")
    private String verify;

//    private Set<Recipe> recipes = new HashSet<>();

    public User(String firstName, String lastName, String email, String password, String verify) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.verify = verify;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

//    public Set<Recipe> getRecipes() {
//        return recipes;
//    }
//
//    public void setRecipes(Set<Recipe> recipes) {
//        this.recipes = recipes;
//    }
}
