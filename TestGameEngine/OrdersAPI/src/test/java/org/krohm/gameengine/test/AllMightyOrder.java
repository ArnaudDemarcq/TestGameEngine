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
@FlavorTextOrder.FlavorTextInfo(flavorTextSize = FlavorTextOrder.FLAVOR_TEXT_MAX_SIZE_SMALL)
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
        OrderResult returnOrderResult = new OrderResult();
        returnOrderResult.setReturnCode(0L);
        return returnOrderResult;
    }
}
