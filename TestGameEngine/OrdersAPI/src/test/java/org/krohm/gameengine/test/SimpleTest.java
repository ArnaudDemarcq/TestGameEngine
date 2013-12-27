/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.gameengine.test;

import org.krohm.gameengine.util.OrderMetaUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;
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
        // TODO : take the ones for the class itself with higher priority than the ones inherited from the interfaces
        // Using "putIfAbscent" works fine due to the actual implementation
        // But this solution does not rely on any specification. Thus, this is a "bad Idea" to keep it
        ConcurrentHashMap<String, Object> deduplicationMap = new ConcurrentHashMap<>();
        Class tmpClass = AllMightyOrder.class;
        for (Field currentField : tmpClass.getFields()) {
            int currentModifiers = currentField.getModifiers();
            LOGGER.info("@@@@" + currentModifiers);
            if (Modifier.isFinal(currentModifiers) && Modifier.isStatic(currentModifiers) && Modifier.isPublic(currentModifiers)) {
                deduplicationMap.putIfAbsent(currentField.getName(), currentField.get(null));
            }
        }
        for (String currentKey : deduplicationMap.keySet()) {
            LOGGER.info(">>>>" + currentKey + " : " + deduplicationMap.get(currentKey));
        }
        LOGGER.info("DAFOK ?");
        Map parametersMap = OrderMetaUtil.getMetaData(tmpClass);
        LOGGER.info("@@@>>" + parametersMap);
        /*
        List<Class> tmpList = ClassUtils.getAllInterfaces(tmpClass);
        for (Class currentInterface : tmpList) {
            //Object currentInterface = it.next();
            LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@>>>" + currentInterface);
            for (Annotation currentAnnotation : currentInterface.getAnnotations()) {
                for (Method currentMethod : currentAnnotation.annotationType().getMethods()) {
                    if (currentMethod.getDeclaringClass().equals(currentAnnotation.annotationType())) {

                        if (currentMethod.getParameterTypes().length == 0) {

                        LOGGER.info("GOTCHA !!!" + currentMethod.getName());
                            LOGGER.info("GOTCHA !!!" + currentMethod.getParameterTypes().length);
                        }
                    }
                }
                // currentMethod.getDeclaringClass();
            }
            for (Method currentMethod : currentInterface.getDeclaredMethods()) {
             if (currentMethod.isAnnotationPresent(OrderMeta.class)) {
             LOGGER.info("GOTCHA !!!");
             }
             }
        }/**/
    }
}
