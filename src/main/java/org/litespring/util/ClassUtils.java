package org.litespring.util;

/**
 * @auther shilikun
 * @Date 2019/10/27 6:30 上午
 * @Description
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoder(){
        ClassLoader  cl = Thread.currentThread().getContextClassLoader();
        if(cl == null ){
            cl = ClassUtils.class.getClassLoader();
            if(cl == null){
                 cl = ClassLoader.getSystemClassLoader();
            }
        }
        return cl;
    }
}
