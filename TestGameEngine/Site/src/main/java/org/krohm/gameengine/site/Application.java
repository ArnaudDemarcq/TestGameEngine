/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.site;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.settings.IRequestCycleSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.krohm.gameengine.database.daos.site.AccountDao;
import org.krohm.gameengine.site.main.ErrorPage;
import org.krohm.gameengine.site.main.HomePage;
import org.krohm.gameengine.site.security.pages.LoginPage;
import org.krohm.gameengine.site.wicket.security.WicketWebSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Xavier
 */
public class Application extends AuthenticatedWebApplication {

    private AccountDao accountDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Class getHomePage() {
        // return Index.class;
        return HomePage.class;
        //return BasePage.class;
    }

    @Override
    protected void init() {
        LOGGER.info("@@@@@@@@@@@@@@@@" + accountDao);
        WicketWebSession.setAccountDao(accountDao);
        // A bit uggly, but needs to be injected statically
        // error pages management
        getApplicationSettings().setPageExpiredErrorPage(ErrorPage.class);
        //getApplicationSettings().setInternalErrorPage(BasePage.class);
        getApplicationSettings().setInternalErrorPage(ErrorPage.class);
        getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
        LOGGER.info("" + getRequestCycleSettings().getRenderStrategy());
        LOGGER.info("" + IRequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
        LOGGER.info("" + IRequestCycleSettings.RenderStrategy.REDIRECT_TO_BUFFER);
        LOGGER.info("" + IRequestCycleSettings.RenderStrategy.REDIRECT_TO_RENDER);
        getRequestCycleSettings().setRenderStrategy(IRequestCycleSettings.RenderStrategy.REDIRECT_TO_BUFFER);

        // For Wicket
        super.init();
        // For Spring
        springInjection();
        // For debug
        getDebugSettings().setDevelopmentUtilitiesEnabled(true);


    }

    protected void springInjection() {
        super.getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    } /**/


    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return WicketWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }
}
