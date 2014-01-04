/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.site.main;

import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.krohm.gameengine.site.util.GetStringModel;

/**
 *
 * @author Arnaud
 */
public class HomePage extends BasePage {

    public HomePage(PageParameters params) {
        super(params);
    }

    
    
     @Override
    protected IModel<String> getTitle() {
        return new GetStringModel(this,"daa.basepage.label.home");
    }
    
}
