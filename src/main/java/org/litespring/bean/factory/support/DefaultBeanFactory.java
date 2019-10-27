package org.litespring.bean.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.bean.factory.BeanFactory;
import org.litespring.beans.BeanDefinition;
import org.litespring.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther shilikun
 * @Date 2019/10/27 6:12 上午
 * @Description
 */
public class DefaultBeanFactory implements BeanFactory {
    private static final String ID_ATTRIBUTE = "id";
    private static final String CLASS_ATTRIBUTE = "class";
    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();


    public DefaultBeanFactory(String configFile){
        loadBeanDefinition(configFile);
    }

    private void loadBeanDefinition(String configFile) {
        InputStream is = null;
        try {
            ClassLoader cl = ClassUtils.getDefaultClassLoder();
            is = cl.getResourceAsStream(configFile);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            Element root = doc.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()){
                Element ele = (Element) iterator.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
                this.beanDefinitionMap.put(id,bd);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public BeanDefinition getBeanDefinition(String BeanId) {
        return beanDefinitionMap.get(BeanId);
    }

    @Override
    public Object getBean(String BeanId) {
        BeanDefinition bd  = this.getBeanDefinition(BeanId);
        if(bd == null){
            return null;
        }
        ClassLoader cl = ClassUtils.getDefaultClassLoder();
        String beanClassname = bd.getBeanClassName();
        try {
            Class<?> cls = cl.loadClass(beanClassname);
            return cls.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
