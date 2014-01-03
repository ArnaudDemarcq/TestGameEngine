/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.database.entities.site;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Arnaud
 */
@Entity
@Table(name = "ACCOUNTS")
@NamedQueries({
    @NamedQuery(name = "Account.findByLoginPassword", query = "SELECT a FROM Account a WHERE a.login=:login AND a.password==:password")})
public class Account {

    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Column(name = "ACCOUNT_NAME")
    private String accountName;
    @Column(name = "IS_ENABLED", nullable = false)
    private boolean enabled;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;

    /* GETTERS ANS SETTERS FOR EVERYONE */
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
