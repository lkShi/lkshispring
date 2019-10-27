package org.litespring.bean.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * @auther shilikun
 * @Date 2019/10/27 6:27 上午
 * @Description
 */
public class GenericBeanDefinition implements BeanDefinition {
    private String id;
    private String beanClassName;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    public String getBeanClassName() {
        return this.beanClassName;
    }
}
