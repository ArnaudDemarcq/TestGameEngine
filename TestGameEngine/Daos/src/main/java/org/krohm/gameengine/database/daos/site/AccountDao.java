/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.database.daos.site;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.krohm.gameengine.database.entities.site.Account;
import org.krohm.generic.dao.GenericEmfDao;

/**
 *
 * @author Arnaud
 */
public class AccountDao extends GenericEmfDao<Long, Account> {

    public AccountDao() {
        super(Long.class, Account.class);
    }

    public Account authenticate(String login, String password) {
        EntityManager em = getEm();
        Query query = em.createNamedQuery("Account.findByLoginPassword");
        query.setParameter("login", login);
        query.setParameter("password", password);
        try {
            List<Account> results = query.getResultList();
            if (results.isEmpty()) {
                return null;
            } else {
                return results.get(0);
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
