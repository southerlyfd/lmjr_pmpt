package com.pmpt.common;

import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Util {
    public static <T> boolean isNotNull(T t) throws IllegalAccessException, NullValueException {
        Class<?> tClass = t.getClass();
        Field[] declaredFields = tClass.getDeclaredFields();
        for (Field field :
                declaredFields) {
            Annotation annotation = field.getAnnotation(NotNull.class);
            if (annotation != null) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                if (String.class.equals(type)) {
                    if (StringUtils.isEmpty(field.get(t))) {
                        throw new NullValueException("参数" + field.getName() + "值不允许为空");
                    }
                } else {
                    if (null == field.get(t)) {
                        throw new NullValueException("参数" + field.getName() + "值不允许为空");
                    }
                }
            }
        }
        return true;
    }

}
