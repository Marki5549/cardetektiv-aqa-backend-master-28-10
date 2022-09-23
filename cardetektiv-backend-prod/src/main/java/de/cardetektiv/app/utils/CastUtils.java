package de.cardetektiv.app.utils;

import java.util.ArrayList;
import java.util.Iterator;

public class CastUtils {

    public static <T> ArrayList<T> cast(Object collection){
        return (ArrayList<T>)collection;
    }

    public static <T> Iterator<T> cast(Iterator<?> collection, Class<T> clazz){
        return (Iterator<T>)collection;
    }

}
