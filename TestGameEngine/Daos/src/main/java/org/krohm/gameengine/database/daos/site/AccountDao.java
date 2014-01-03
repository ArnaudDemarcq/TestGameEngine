/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.database.daos.site;

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
}
