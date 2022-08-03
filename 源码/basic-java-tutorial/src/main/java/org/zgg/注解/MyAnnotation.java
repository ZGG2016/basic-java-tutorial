package org.zgg.注解;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

/**
 * 自定义注解：
 *   1. 使用 @interface 关键字
 *   2. 自定义注解自动继承了java.lang.annotation.Annotation接口
 *   3. Annotation 的成员变量在 Annotation 定义中以无参数方法的形式来声明。
 *      【其方法名（value）和返回值（String）定义了该成员的名字和类型】。我们称为配置参数。
 *      类型只能是八种基本数据类型、String类型、Class类型、enum类型、Annotation类型、以上所有类型的数组。
 *   4. 在定义 Annotation 的成员变量时为其指定初始值, 指定成员变量的初始值可使用 default 关键字 （比如 default "hello"）
 *   5. 如果只有一个参数成员，建议使用参数名为value
 *   6. 如果定义的注解含有配置参数，那么使用时必须指定参数值，除非它有默认值。
 *      【格式是“参数名 = 参数值”】，如果只有一个参数成员，且名称为value，可以省略“value=”
 *   7. 没有成员定义的 Annotation 称为标记  （比如 @Override）
 *      包含成员变量的 Annotation 称为元数据 Annotatio
 *   ------------------------------------------------------------------------------------------------------------
 *   8. 元注解：用于修饰其他 Annotation 定义
 *      - Retention: 用于指定该 Annotation 的生命周期, 包含一个 RetentionPolicy 的枚举类，指明了都有哪些生命周期
 *      - Target: 用于指定被修饰的 Annotation 能用于修饰哪些程序元素，包含一个 ElementType 的枚举类，描述了都有哪些程序元素
 *      - Documented: 表示所修饰的注解在被javadoc解析时，保留下来。 定义为Documented的注解必须设置Retention值为RUNTIME。
 *      - Inherited: 如果某个类使用了被 @Inherited 修饰的 Annotation, 则其子类将自动具有该注解
 *
 *   9. 通过反射获取注解信息，执行相关的操作
 *   ------------------------------------------------------------------------------------------------------------
 *   10. 可重复的注解 （元注解）
 *       - 在 MyAnnotation 上声明 @Repeatable，成员值为 MyAnnotations.class
 *       - MyAnnotation 的 Target 和 Retention 等元注解与 MyAnnotations 相同
 *       - jdk 8之前的写法：@MyAnnotations({@MyAnnotation(value="hi"),@MyAnnotation(value="hi")})
 *   11. 用于类型的注解（元注解）
 *       - ElementType.TYPE_PARAMETER 表示该注解能写在类型变量的声明语句中（如：泛型声明）。
 *       - ElementType.TYPE_USE 表示该注解能写在使用类型的任何语句中。
 *
 * */
@Repeatable(MyAnnotations.class)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,TYPE_PARAMETER,TYPE_USE})
public @interface MyAnnotation {
    String value() default "hello";
}
