/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
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

    public static Map<String, Object> getMetaData(Class<Order> orderClass) {
        Map<String, Object> returnMap = new HashMap<>();
        List<Class> tmpList = ClassUtils.getAllInterfaces(orderClass);
        for (Class currentInterface : tmpList) {
            returnMap.putAll(getMetaDataTerminal(currentInterface));
        }
        returnMap.putAll(getMetaDataTerminal(orderClass));
        returnMap.put("name", orderClass.getSimpleName());
        return returnMap;
    }

    private static Map<String, Object> getMetaDataTerminal(Class<Order> currentClass) {
        Map<String, Object> returnMap = new HashMap<>();
        for (Annotation currentAnnotation : currentClass.getAnnotations()) {
            for (Method currentMethod : currentAnnotation.annotationType().getMethods()) {
                if (currentMethod.getDeclaringClass().equals(currentAnnotation.annotationType())) {
                    if (currentMethod.getParameterTypes().length == 0) {
                        try {
                            String key = currentMethod.getName();
                            Object value = currentMethod.invoke(currentAnnotation);
                            returnMap.put(key, value);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            LOGGER.warn("Cannot read Metadata. Skipping (" + ex.getMessage() + ")");
                        }
                    }
                }
            }
        }
        return returnMap;
    }
}
