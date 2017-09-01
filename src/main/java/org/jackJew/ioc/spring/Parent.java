package org.jackJew.ioc.spring;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Jack on 2017/3/18.
 */
@Getter
public class Parent {

    @Value("123")
    private String parentField;
}
