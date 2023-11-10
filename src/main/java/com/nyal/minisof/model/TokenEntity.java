package com.nyal.minisof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TokenEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tokenId;
    private boolean accessToken;
    @Lob
    private String deviceToken;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "accountId")
    private AccountEntity account;

    public TokenEntity() {
    }

    public TokenEntity(int tokenId, boolean accessToken, String deviceToken, AccountEntity account) {
        this.tokenId = tokenId;
        this.accessToken = accessToken;
        this.deviceToken = deviceToken;
        this.account = account;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public boolean isAccessToken() {
        return accessToken;
    }

    public void setAccessToken(boolean accessToken) {
        this.accessToken = accessToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
