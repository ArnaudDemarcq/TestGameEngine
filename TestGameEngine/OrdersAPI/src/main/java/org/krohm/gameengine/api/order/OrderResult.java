/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.api.order;

/**
 *
 * @author Arnaud
 */
public class OrderResult {

    private long returnCode;
    private String returnFlavorText;

    public long getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(long returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnFlavorText() {
        return returnFlavorText;
    }

    public void setReturnFlavorText(String returnFlavorText) {
        this.returnFlavorText = returnFlavorText;
    }
}
