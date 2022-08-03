package org.zgg.jdk8.optional类;

import org.zgg.jdk8.data.Employee;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Optional类：为了在程序中避免出现空指针异常而创建的。
 * 是一个容器类，它可以保存类型T的值，代表这个值存在。或者仅仅保存null，表示这个值不存在。
 * <p>
 * 常用的方法：ofNullable(T t)
 * orElse(T t)
 */
public class OptionalTest {

    // 创建Optional类对象的方法
    @Test
    public void test1() {
        Employee e1 = new Employee(1001, "马化腾", 34, 6000.38);
        Employee e2 = null;

        // Optional.of(T t) : 创建一个 Optional 实例，t必须非空
        Optional<Employee> o1 = Optional.of(e1);
//        Optional<Employee> o2 = Optional.of(e2);   // NullPointerException
        System.out.println(o1);
//        System.out.println(o2);

        // Optional.empty() : 创建一个空的 Optional 实例
        Optional<Object> o3 = Optional.empty();
        System.out.println(o3);

        // Optional.ofNullable(T t)：t可以为null
        Optional<Employee> o41 = Optional.ofNullable(e1);
        Optional<Employee> o42 = Optional.ofNullable(e2);
        System.out.println(o41);
        System.out.println(o42);
    }

    // 判断Optional容器中是否包含对象
    @Test
    public void test2() {
        Employee e1 = new Employee(1001, "马化腾", 34, 6000.38);
        Employee e2 = null;
        Optional<Employee> o1 = Optional.ofNullable(e1);
        Optional<Employee> o2 = Optional.ofNullable(e2);

        // boolean isPresent() : 判断是否包含对象
//        System.out.println(o1.isPresent());
//        System.out.println(o2.isPresent());

        // void ifPresent(Consumer<? super T> consumer) ：如果有值，就执行Consumer 接口的实现代码，并且该值会作为参数传给它。
        o1.ifPresent(System.out::println);
        o2.ifPresent(System.out::println);
    }

    // 获取Optional容器的对象
    @Test
    public void test3() {
        Employee e1 = new Employee(1001, "马化腾", 34, 6000.38);
        Employee e2 = null;
        Optional<Employee> o1 = Optional.ofNullable(e1);
        Optional<Employee> o2 = Optional.ofNullable(e2);
        Employee e3 = new Employee(1002, "马云", 12, 9876.12);

        // T get(): 如果调用对象包含值，返回该值，否则抛异常
//        System.out.println(o1.get());
//        System.out.println(o2.get()); // java.util.NoSuchElementException: No value present

        // T orElse(T other) ：如果有值则将其返回，否则返回指定的other对象
//        System.out.println(o1.orElse(e3));
//        System.out.println(o2.orElse(e3));

        // T orElseGet(Supplier<? extends T> other) ：如果有值则将其返回，否则返回由Supplier接口实现提供的对象
//        System.out.println(o1.orElseGet(()->e3));
//        System.out.println(o2.orElseGet(()->e3));

        // T orElseThrow(Supplier<? extends X> exceptionSupplier) ：如果有值则将其返回，否则抛出由Supplier接口实现提供的异常。
        System.out.println(o1.orElseThrow(() -> new NoSuchElementException("员工不存在")));
        System.out.println(o2.orElseThrow(() -> new NoSuchElementException("员工不存在"))); // java.util.NoSuchElementException: 员工不存在
    }
}
