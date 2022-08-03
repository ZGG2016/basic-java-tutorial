package org.zgg.jdk8.方法引用;

import org.zgg.jdk8.data.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 1. 构造器引用
 * 和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致。
 * 抽象方法的返回值类型即为构造器所属的类的类型
 * <p>
 * 2. 数组引用
 * 将数组看成一个特殊的类，则写法和构造器引用一样
 */
public class ConstructorRefTest {
    //构造器引用
    //Supplier中的T get()
    // Employee类的无参构造
    @Test
    public void test1() {
        Supplier<Employee> e1 = () -> new Employee();
        System.out.println(e1.get());
        System.out.println("-----------------------");

        Supplier<Employee> e2 = Employee::new;
        System.out.println(e2.get());
    }

    //Function中的R apply(T t)
    // Employee类的参数是id的构造
    @Test
    public void test2() {
        Function<Integer, Employee> f1 = id -> new Employee(id);
        System.out.println(f1.apply(1));
        System.out.println("-----------------------");

        Function<Integer, Employee> f2 = Employee::new;
        System.out.println(f2.apply(2));
    }

    //BiFunction中的R apply(T t,U u)
    @Test
    public void test3() {
        BiFunction<Integer, String, Employee> f1 = (id,name)-> new Employee(id,name);
        System.out.println(f1.apply(1,"zhangsan"));
        System.out.println("-----------------------");

        BiFunction<Integer, String, Employee> f2 = Employee::new;
        System.out.println(f2.apply(2,"lisi"));
    }

    //数组引用
    //Function中的R apply(T t)
    @Test
    public void test4() {
        Function<Integer, String[]> f1 = length -> new String[length];
        System.out.println(Arrays.toString(f1.apply(2)));
        System.out.println("-----------------------");

        Function<Integer, String[]> f2 = String[]::new;
        System.out.println(Arrays.toString(f2.apply(2)));
    }
}
