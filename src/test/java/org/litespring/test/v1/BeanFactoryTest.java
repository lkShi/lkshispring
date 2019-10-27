package org.litespring.test.v1;

import org.junit.Test;
import org.litespring.bean.factory.BeanFactory;
import org.litespring.bean.factory.support.DefaultBeanFactory;
import org.litespring.beans.BeanDefinition;
import org.litespring.service.v1.PetStoreService;

import  static org.junit.Assert.*;

/**
 * @auther shilikun
 * @Date 2019/10/27 6:03 上午
 * @Description
 */
public class BeanFactoryTest {
    @Test
    public void testGetBean(){
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        assertEquals("org.litespring.service.v1.PetStoreService",bd.getBeanClassName());
        PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
        assertNotNull(petStore);
    }
}
