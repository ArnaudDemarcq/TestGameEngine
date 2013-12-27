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

    public static final int FLAVOR_TEXT_MAX_SIZE_LARGE = 4096;
    public static final int FLAVOR_TEXT_MAX_SIZE_MEDIUM = 1024;
    public static final int FLAVOR_TEXT_MAX_SIZE_SMALL = 256;

    public void setFlavorText(String flavorText);

    @Retention(RetentionPolicy.RUNTIME)
    public @interface FlavorTextInfo {

        int flavorTextSize() default FlavorTextOrder.FLAVOR_TEXT_MAX_SIZE_LARGE;

        boolean hasFlavorText() default true;
    }/**/

}
