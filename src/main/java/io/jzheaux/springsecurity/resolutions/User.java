package io.jzheaux.springsecurity.resolutions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity(name="users")
public class User implements Serializable {

    @Id
    UUID id;

    @Column
    String username;

    @Column
    String password;

    @Column(name="subscription")
    String subscription;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Collection<User> friends = new ArrayList<>();

    @Column
    boolean enabled = true;
    @Column(name="full_name")
    String fullName;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    Collection<UserAuthority> userAuthorities = new ArrayList<>();

    User(){}

    public User(String username, String password){
        this.id = UUID.randomUUID();
        this.username = username;
        this.password =password;
    }
    public User(User user){
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.enabled = user.enabled;
        this.userAuthorities = user.userAuthorities;
        this.fullName = user.fullName;
        this.subscription = user.subscription;
        this.friends = user.friends;
    }
    public  Collection<UserAuthority> getUserAuthorities(){
        return  Collections.unmodifiableCollection(this.userAuthorities);
    }

    public  void grantAuthority(String authority){
        UserAuthority userAuthority = new UserAuthority(this, authority);
        this.userAuthorities.add(userAuthority);
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public Collection<User> getFriends() {
        return friends;
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public String getUsername() {
        return username;
    }
}
