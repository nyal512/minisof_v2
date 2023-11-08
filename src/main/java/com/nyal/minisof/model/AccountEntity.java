package com.nyal.minisof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class AccountEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    private String username;
    private String password;
    private int role;
    private Date created;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserEntity user;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TokenEntity> tokenList;

    public AccountEntity() {
    }

    public AccountEntity(int accountId, String username, String password, int role, Date created, UserEntity user, List<TokenEntity> tokenList) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.created = created;
        this.user = user;
        this.tokenList = tokenList;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<TokenEntity> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<TokenEntity> tokenList) {
        this.tokenList = tokenList;
    }
}
