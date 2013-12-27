/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.test;

import org.krohm.gameengine.api.order.OrderResult;
import org.krohm.gameengine.api.order.types.EncounterOrder;
import org.krohm.gameengine.api.order.types.FlavorTextOrder;

/**
 *
 * @author Arnaud
 */
@FlavorTextOrder.FlavorTextInfo(flavorTextSize = 1024)
public class AllMightyOrder implements EncounterOrder, FlavorTextOrder {

    private String flavorText;
    private long encounterTarget;

    @Override
    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    @Override
    public void setEncounterTarget(long encounterTarget) {
        this.encounterTarget = encounterTarget;
    }

    @Override
    public OrderResult execute() {
        OrderResult returnOrderResult = new OrderResult() {
            @Override
            public long getReturnCode() {
                return 0;
            }

            @Override
            public String getReturnFlavorText() {
                return "Vous avez parlé à " + encounterTarget + " : " + flavorText;
            }
        };
        return returnOrderResult;
    }
}
