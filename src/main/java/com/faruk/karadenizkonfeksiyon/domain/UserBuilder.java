/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.domain;

import com.faruk.karadenizkonfeksiyon.domain.User;
import com.faruk.karadenizkonfeksiyon.domain.User;

/**
 *
 * @author Faruk
 */
public class UserBuilder {

    Long id;

    String name;

    String lastName;

    String password;

    String userName;

    public UserBuilder id(Long id) {

        this.id = id;
        return this;
    }

    public UserBuilder name(String name) {

        this.name = name;
        return this;

    }

    public UserBuilder lastName(String lastName) {

        this.lastName = lastName;
        return this;

    }

    public UserBuilder password(String password) {

        this.password = password;
        return this;

    }
    
    public UserBuilder userName(String userName){
    
        this.userName = userName;
        return this;
    }
    
    public User build(){
    
        return new User(this);
        
    }

}
