package com.glp;

import com.glp.model.Duck;
import com.glp.model.DuckShop;
import com.glp.model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("applications.xml");

//        Duck duck = (Duck) context.getBean("duck1");
//        System.out.println(duck);  //Duck{name='唐老鸭', age=25}
//        Duck duck1 = (Duck) context.getBean("duck1");
//        System.out.println(duck1==duck);//true
//        Duck duck2 = (Duck) context.getBean("duck2");
//        Duck duck3 = (Duck) context.getBean("duck2");
//        System.out.println(duck2==duck3);//false
//        System.out.println(duck2);//Duck{name='兔八哥', age=56}
//
//        DuckShop shop = (DuckShop) context.getBean("duckShop");
//        System.out.println(shop);//DuckShop{ducks=[Duck{name='唐老鸭', age=25}, Duck{name='兔八哥', age=56}]}
//
//        //通过id或类对象取
//        Person p1 = (Person) context.getBean(Person.class);
//        System.out.println(p1);//Person{duck=Duck{name='唐老鸭', age=25}}
//        //通过类型在容器中获取bean：该类型只能有一个对象在容器中，否则会报错
//      // context.getBean(Duck.class);
//        // NoUniqueBeanDefinitionException：
//        // No qualifying bean of type 'com.glp.model.Duck' available: expected single
//        // matching bean but found 4: duck1,duck2,duck3,duck4
//        Duck duck4 = (Duck) context.getBean("duck3");
//        System.out.println(duck3);//Duck{name='兔八哥', age=56}
    }
}

