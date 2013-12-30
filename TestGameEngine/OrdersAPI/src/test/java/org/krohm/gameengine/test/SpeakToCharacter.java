/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.test;

import org.krohm.gameengine.api.order.Order;
import org.krohm.gameengine.api.order.OrderResult;
import org.krohm.gameengine.api.order.types.EncounterOrder;
import org.krohm.gameengine.api.order.types.FlavorTextOrder;

/**
 *
 * @author Arnaud
 */
public class SpeakToCharacter implements Order, EncounterOrder, FlavorTextOrder {

    private long encounterTarget;
    private String flavorText;

    @Override
    public OrderResult execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEncounterTarget(long encounterTarget) {
        this.encounterTarget = encounterTarget;
    }

    @Override
    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }
}
