package org.zgg.注解;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;

/**
 * 注解的使用
 *
 * 1. 理解Annotation:
 *    - jdk 5.0 新增的功能
 *    - 就是代码里的特殊标记, 这些标记可以【在编译、类加载、运行时被读取】, 并执行相应的处理。
 *      通过使用 Annotation, 程序员可以在不改变原有逻辑的情况下, 在源文件中【嵌入一些补充信息】。
 *    - 在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。
 *      在JavaEE/Android中注解占据了更重要的角色，例如用来配置应用程序的任何切面，代替JavaEE旧版中所遗留的繁冗代码和XML配置等。
 *
 * 2. Annocation的使用示例
 *    - 示例一：生成文档相关的注解   @author
 *    - 示例二：在编译时进行格式检查(JDK内置的三个基本注解)
 *             @Override: 限定重写父类方法, 该注解只能用于方法
 *             @Deprecated: 用于表示所修饰的元素(类, 方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
 *             @SuppressWarnings: 抑制编译器警告
 *    - 示例三：跟踪代码依赖性，实现替代配置文件功能
 *             Servlet3.0提供了注解(annotation),使得不再需要在web.xml文件中进行Servlet的部署
 *             @WebServlet("/login")
 *             public class LoginServlet extends HttpServlet {}
 *  3. 自定义注解必须配上注解的信息处理流程(使用反射)才有意义。
 *     自定义注解通过都会指明两个元注解：Retention、Target
 */
public class AnnotationTest {

    public static void main(String[] args) {
        Person p = new Student();
        p.walk();

        // @Deprecated: 用于表示所修饰的元素(类, 方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
        Date date = new Date(2020, 10, 11);
        System.out.println(date);

        // @SuppressWarnings: 抑制编译器警告
        @SuppressWarnings("unused")
        int num = 10;
        System.out.println(num);
        @SuppressWarnings({ "unused", "rawtypes" })
        ArrayList list = new ArrayList();
    }

    @Test
    public void testGetAnnotation(){
        Class clazz = Student.class;
        Annotation[] annotations = clazz.getAnnotations();
        for(int i = 0;i < annotations.length;i++){
            System.out.println(annotations[i]);
        }
    }
}

class Student extends Person implements Info{

    // @Override: 限定重写父类方法, 该注解只能用于方法
    @Override
    public void walk() {
        System.out.println("学生走路");
    }

    public void show() {

    }
}

// 自定义注解
@MyAnnotation(value = "hi")
@MyAnnotation(value = "aaa")  // 可重复的注解
class Person{
    private String name;
    private int age;

    public Person() {
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void walk(){
        System.out.println("人走路");
    }
    public void eat(){
        System.out.println("人吃饭");
    }
}

interface Info{
    void show();
}

// 用于类型的注解
class Generic<@MyAnnotation T>{

    public void show() throws @MyAnnotation RuntimeException{
        ArrayList<@MyAnnotation String> list = new ArrayList<>();
        int num = (@MyAnnotation int) 10L;
    }

}