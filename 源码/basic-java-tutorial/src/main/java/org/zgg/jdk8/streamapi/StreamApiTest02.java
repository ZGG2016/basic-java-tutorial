package org.zgg.jdk8.streamapi;

import org.zgg.jdk8.data.Employee;
import org.zgg.jdk8.data.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream 的中间操作:
 * 多个中间操作可以连接起来形成一个流水线，
 * 除非流水线上触发终止操作，否则中间操作不会执行任何的处理！
 * 而在终止操作时一次性全部处理，称为“惰性求值”
 */
public class StreamApiTest02 {
    // 1 筛选与切片
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
        // 1.1 filter(Predicate p) 接收 Lambda ， 从流中排除某些元素   【只取满足条件的元素】
//        employees
//                .stream()
//                .filter(e -> e.getSalary() > 7000)
//                .forEach(System.out::println);
//        System.out.println("---------------------------------------------");

        // 1.2 distinct() 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
//        employees.add(new Employee(1002, "马云", 12, 9876.12));
//        employees
//                .stream()
//                .filter(e -> e.getSalary() > 7000)
//                .distinct()
//                .forEach(System.out::println);
//        System.out.println("---------------------------------------------");

        // 1.3 limit(long maxSize) 截断流，使其元素不超过给定数量  【取前n个】
//        employees
//                .stream()
//                .limit(3)
//                .forEach(System.out::println);

        // 1.4 skip(long n)  跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
        employees
                .stream()
                .skip(2)
                .forEach(System.out::println);
    }

    // 2 映射
    @Test
    public void test2() {
        List<Employee> employees = EmployeeData.getEmployees();

        // 2.1 map(Function f)  接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
//        employees
//                .stream()
//                .map(e -> e.getName() + " - map")
//                .forEach(System.out::println);

        // 2.2 flatMap(Function f)  接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
        String[][] arr = {{"a", "b"}, {"c", "d"}};
        Arrays
                .stream(arr)
                .flatMap(ss -> Arrays.stream(ss).map(String::toUpperCase)) // 把{"a", "b"} {"c", "d"} 转成流，执行map操作，再将它们连接起来
                .forEach(System.out::println);

        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        Stream<Character> characterStream = list.stream().flatMap(StreamApiTest02::fromStringToStream);
        characterStream.forEach(System.out::println);

    }

    //将字符串中的多个字符构成的集合转换为对应的Stream的实例
    public static Stream<Character> fromStringToStream(String str) {//aa
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    // 3 排序
    @Test
    public void test3() {
        // 3.1 sorted() 产生一个新流，其中按自然顺序排序
//        List<Integer> nums = Arrays.asList(3,5,1,6);
//        nums
//                .stream()
//                .sorted()
//                .forEach(System.out::println);
//        // 流的元素必须是实现Comparable，否则就会抛异常
//        // java.lang.ClassCastException: com.zgg.jdk8.data.Employee cannot be cast to java.lang.Comparable
//        List<Employee> employees = EmployeeData.getEmployees();
//        employees
//                .stream()
//                .sorted()
//                .forEach(System.out::println);

        // 3.2 sorted(Comparator com) 产生一个新流，其中按比较器Comparator顺序排序
        List<Employee> employees = EmployeeData.getEmployees();
        employees
                .stream()
                .sorted((o1,o2)->{
                    int ageDiff = Integer.compare(o1.getAge(), o2.getAge());
                    if (ageDiff != 0) {
                        return ageDiff;
                    } else {
                        return Double.compare(o1.getSalary(), o2.getSalary());
                    }
                })
//                .sorted(new Comparator<Employee>() {
//                    @Override
//                    public int compare(Employee o1, Employee o2) {
//                        int ageDiff = Integer.compare(o1.getAge(), o2.getAge());
//                        if (ageDiff != 0) {
//                            return ageDiff;
//                        } else {
//                            return Double.compare(o1.getSalary(), o2.getSalary());
//                        }
//
//                    }
//                })
                .forEach(System.out::println);
    }

}
