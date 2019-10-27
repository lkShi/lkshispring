package org.litespring.bean.factory;

import org.litespring.beans.BeanDefinition;

/**
 * @auther shilikun
 * @Date 2019/10/27 6:10 上午
 * @Description
 */
public interface BeanFactory {
    BeanDefinition getBeanDefinition(String BeanId);

    Object getBean(String BeanId);
}
