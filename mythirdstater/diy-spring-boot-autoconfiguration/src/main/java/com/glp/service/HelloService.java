package com.glp.service;

import com.glp.properties.Properties;

public class HelloService {

    Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    public String sayHello(){
        return properties.getPre()+"offer"+properties.getSuf();
    }
}
