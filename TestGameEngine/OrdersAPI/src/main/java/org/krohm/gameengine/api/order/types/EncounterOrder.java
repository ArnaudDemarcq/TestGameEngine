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
@EncounterOrder.EncounterOrderInfo
public interface EncounterOrder extends Order {

    public void setEncounterTarget(long id);

    @Retention(RetentionPolicy.RUNTIME)
    public @interface EncounterOrderInfo {

        boolean hasEncounterTarget() default true;
    }/**/

}
