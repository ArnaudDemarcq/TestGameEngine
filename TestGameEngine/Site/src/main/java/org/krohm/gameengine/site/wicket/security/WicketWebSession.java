/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.site.wicket.security;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.krohm.gameengine.database.daos.site.AccountDao;
import org.krohm.gameengine.database.entities.site.Account;
import org.krohm.gameengine.site.main.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arnaud
 */
public class WicketWebSession extends AbstractAuthenticatedWebSession {

    private String userId;
    private String password;
    private Boolean isLogged = null;
    private final List<String> userGroups = new ArrayList<String>();
    private static final Logger LOGGER = LoggerFactory.getLogger(WicketWebSession.class);
    private static AccountDao accountDao;
    private Account loggedAccount;

    public static void setAccountDao(AccountDao accountDao) {
        WicketWebSession.accountDao = accountDao;
    }

    public WicketWebSession(Request request) {
        super(request);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        this.isLogged = null;
    }

    public void setPassword(String password) {
        this.password = password;
        this.isLogged = null;
    }

    @Override
    public Roles getRoles() {
        // TODO : manage roles
        Roles currentRoles = new Roles();
        currentRoles.add(BasePage.ROLE_AUTH);
        return currentRoles;
    }

    @Override
    public boolean isSignedIn() {
        // Do not check again if user has been verified already
        if (this.isLogged != null && this.isLogged != false) {
            return true;
        }
        // Then try to log in
        this.isLogged = null;
        try {
            //this.isLogged = (userId != null);
            this.isLogged = performDbAuth();
        } catch (Exception ex) {
            LOGGER.warn("Error validating credentials :" + ex.getMessage());
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("", ex);
            }
            return false;
        }
        return (this.isLogged == true);
    }

    public static WicketWebSession get() {
        return (WicketWebSession) Session.get();
    }

    private boolean performDbAuth() {
        try {
            loggedAccount = accountDao.authenticate(userId, password);
            return (loggedAccount != null);
        } catch (Exception ex) {
            LOGGER.warn("cannot get credentials for userId : <" + userId + ">" + ex);
            if (userId == null) {
                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("cannot connect to LDAP" + ex, ex);
                }
            }
        }
        return false;
    }
}