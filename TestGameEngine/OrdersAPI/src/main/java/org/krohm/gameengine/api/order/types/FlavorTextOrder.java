/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.api.order.types;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.krohm.gameengine.api.order.Order;

/**
 *
 * @author Arnaud
 */
@FlavorTextOrder.FlavorTextInfo
public interface FlavorTextOrder extends Order {

    public static final int FLAVOR_TEXT_MAX_SIZE = 4096;

    public void setFlavorText(String flavorText);

    @Retention(RetentionPolicy.RUNTIME)
    public @interface FlavorTextInfo {

        int flavorTextSize() default FlavorTextOrder.FLAVOR_TEXT_MAX_SIZE;

        boolean hasFlavorText() default true;
    }/**/

}
