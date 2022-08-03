package org.zgg.jdk8.方法引用;

import org.zgg.jdk8.data.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用的使用：
 * 1. 使用情境：当要传递给lambda体的操作，已经有实现的方法了，可以使用方法引用
 * <p>
 * 2. 方法引用：本质就是lambda表达式，而lambda表达式作为函数式接口的实例，
 * 所以，方法引用，也是函数式接口的实例。
 * <p>
 * 3. 使用格式：类（对象）::方法名
 * - 对象 :: 非静态方法
 * - 类 :: 静态方法
 * - 类 :: 非静态方法
 * <p>
 * 4.使用要求：接口中的抽象方法的形参列表和返回值类型与方法引用的方法的形参列表和返回值类型相同（仅针对 `对象 :: 非静态方法` `类 :: 静态方法` 情况）
 */
public class MethodRefTest {

    // 情况一：对象 :: 实例方法
    //Consumer中的void accept(T t)
    //PrintStream中的void println(T t)
    @Test
    public void test1() {
        Consumer<String> c1 = str -> System.out.println(str);
        c1.accept("beijing1");
        System.out.println("----------------------------");
        // 当要传递给lambda体的操作【System.out.println(str)】，已经有实现的方法了【PrintStream类中的void println(T t)】，可以使用方法引用
        // 【lambda表达式起到的功能可以使用已有的实现方法替换，且它们的形参和返回值一样】
        PrintStream ps = System.out;
        Consumer<String> c2 = ps::println;
        c2.accept("beijing2");
    }

    //Supplier中的T get()
    //Employee中的String getName()
    @Test
    public void test2() {
        Employee emp = new Employee(1, "zhangsan", 30, 7000);

        Supplier<String> s1 = () -> emp.getName();
        System.out.println(s1.get());
        System.out.println("----------------------------");

        Supplier<String> s2 = emp::getName;
        System.out.println(s2.get());
    }

    // 情况二：类 :: 静态方法
    //Comparator中的int compare(T t1,T t2)
    //Integer中的int compare(T t1,T t2)
    @Test
    public void test3() {
        Comparator<Integer> c1 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(c1.compare(1, 2));
        System.out.println("----------------------------");
        // 【lambda表达式起到的功能可以使用已有的实现方法替换，且它们的形参和返回值一样】
        Comparator<Integer> c2 = Integer::compare;
        System.out.println(c2.compare(2, 1));
    }

    //Function中的R apply(T t)
    //Math中的Long round(Double d)
    @Test
    public void test4() {
        Function<Double, Long> f1 = d -> Math.round(d);
        System.out.println(f1.apply(2.333));
        System.out.println("----------------------------");

        Function<Double, Long> f2 = Math::round;
        System.out.println(f2.apply(2.333));
    }

    // 情况三：类 :: 实例方法  （第一个参数当成了调用者）
    // Comparator中的int comapre(T t1,T t2)
    // String中的int t1.compareTo(t2)
    @Test
    public void test5() {
        Comparator<String> c1 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(c1.compare("a", "b"));
        System.out.println("----------------------------");
        // 【lambda表达式起到的功能可以使用已有的实现方法替换。 String是o1的类型】
        Comparator<String> c2 = String::compareTo;
        System.out.println(c2.compare("b", "a"));
    }

    //BiPredicate中的boolean test(T t1, T t2);
    //String中的boolean t1.equals(t2)
    @Test
    public void test6() {
        BiPredicate<String, String> bp1 = (t1, t2) -> t1.equals(t2);
        System.out.println(bp1.test("a", "b"));
        System.out.println("----------------------------");

        BiPredicate<String, String> bp2 = String::equals;
        System.out.println(bp2.test("a", "a"));
    }

    // Function中的R apply(T t)
    // Employee中的String getName();
    @Test
    public void test7() {
        Employee emp = new Employee(1, "zhangsan", 30, 7000);

        Function<Employee, String> f1 = e -> e.getName();
        System.out.println(f1.apply(emp));
        System.out.println("----------------------------");
        // Employee 是 apply() 方法里的参数
        Function<Employee, String> f2 = Employee::getName;
        System.out.println(f2.apply(emp));
    }

}
