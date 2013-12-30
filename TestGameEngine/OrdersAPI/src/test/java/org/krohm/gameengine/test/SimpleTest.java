/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.test;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;
import org.krohm.gameengine.api.order.Order;
import org.krohm.gameengine.util.OrderMetaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arnaud
 */
public class SimpleTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTest.class);

    @Test
    public void testOriginal() throws Exception {

        Class tmpClass = AllMightyOrder.class;

        Map parametersMap = OrderMetaUtil.getMetaData(tmpClass);
        LOGGER.info("@@@>>" + parametersMap);

        Map allOrders = OrderMetaUtil.getOrdersParameters(getTestOrders());
        String json = getOrdersAsJson(getTestOrders());

        LOGGER.info("@@@>>\n" + json);

    }

    private Map<Long, Class<? extends Order>> getTestOrders() {
        Map<Long, Class<? extends Order>> returnMap = new HashMap<>();
        returnMap.put(120254L, AllMightyOrder.class);
        returnMap.put(120255L, SpeakToCharacter.class);
        return returnMap;
    }

    public String getOrdersAsJson(Map<Long, Class<? extends Order>> orders) {
        Map tmpMap = new HashMap();
        tmpMap.put("orders", OrderMetaUtil.getOrdersParameters(orders));
        Gson gson = new Gson();
        String json = gson.toJson(tmpMap);
        return json;

    }
}
