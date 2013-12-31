/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ClassUtils;
import org.krohm.gameengine.api.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arnaud
 */
public class OrderMetaUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderMetaUtil.class);

    public static Map<String, Object> getMetaData(Class<? extends Order> orderClass) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        // TODO : replace this library usage with some terminal recustion
        // (this will handle inheritance of default parameters better in case of 
        List<Class> tmpList = ClassUtils.getAllInterfaces(orderClass);
        for (Class currentInterface : tmpList) {
            returnMap.putAll(getMetaDataTerminal(currentInterface));
        }
        returnMap.putAll(getMetaDataTerminal(orderClass));
        returnMap.put("name", orderClass.getSimpleName());
        return returnMap;
    }

    private static Map<String, Object> getMetaDataTerminal(Class<? extends Order> currentClass) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        for (Annotation currentAnnotation : currentClass.getAnnotations()) {
            for (Method currentMethod : currentAnnotation.annotationType().getMethods()) {
                if (currentMethod.getDeclaringClass().equals(currentAnnotation.annotationType())) {
                    if (currentMethod.getParameterTypes().length == 0) {
                        try {
                            String key = currentMethod.getName();
                            Object value = currentMethod.invoke(currentAnnotation);
                            returnMap.put(key, value);
                        } catch (Exception ex) {
                            LOGGER.warn("Cannot read Metadata. Skipping (" + ex.getMessage() + ")");
                        }
                    }
                }
            }
        }
        return returnMap;
    }

    public static Map<Long, Map<String, Object>> getOrdersParameters(Map<Long, Class<? extends Order>> orders) {

        Map<Long, Map<String, Object>> returnMap = new HashMap<Long, Map<String, Object>>();
        for (Long currentKey : orders.keySet()) {
            Class<? extends Order> currentOrderClass = orders.get(currentKey);
            Map<String, Object> currentParameters = getMetaData(currentOrderClass);
            returnMap.put(currentKey, currentParameters);
        }
        return returnMap;
    }
}
