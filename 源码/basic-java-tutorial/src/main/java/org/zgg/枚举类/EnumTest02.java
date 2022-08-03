package org.zgg.枚举类;

/**
 * 1. 使用enum创建枚举类
 * 2. enum类中的常用方法
 * 3. 使用enum关键字定义的枚举类实现接口的情况:
 *      情况一：在enum类中实现接口中抽象方法
 *      情况二：让枚举类的对象分别实现接口中的抽象方法
 */
public class EnumTest02 {
    public static void main(String[] args) {
        Season1 spring = Season1.SPRING;
        // toString()：返回当前枚举类对象常量的名称
        System.out.println(spring.toString());
        System.out.println("-------------------------");

        // values()方法：返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值
        Season1[] values = Season1.values();
        for (Season1 val : values){
            System.out.println(val);
        }
        System.out.println("-------------------------");

        // valueOf(String str)：可以把一个字符串转为对应的枚举类对象。
        // 要求字符串必须是枚举类对象的“名字”。如不是，会有运行时异常：IllegalArgumentException。
        Season1 spring1 = Season1.valueOf("SPRING");
//        Season1 spring2 = Season1.valueOf("SPRINGG");
        System.out.println(spring1);
//        System.out.println(spring2);
        System.out.println("-------------------------");

        // 实现接口方法
        Season1.SPRING.printSeason();
        Season1.SUMMER.printSeason();

    }
}

interface Show{
    void printSeason();
}

//自定义枚举类 （方式二：jdk5.0，可以使用enum关键字定义枚举类）
enum Season1 implements Show{
    // 逗号隔开，最后一个是分号
    SPRING("春天", "春暖花开"){
        // 情况二：让枚举类的对象分别实现接口中的抽象方法
        @Override
        public void printSeason() {
            System.out.println("----- 春天 ------");
        }
    },
    SUMMER("夏天", "夏日炎炎"){
        @Override
        public void printSeason() {
            System.out.println("----- 夏天 ------");
        }
    },
    AUTUMN("秋天", "秋高气爽"){
        @Override
        public void printSeason() {
            System.out.println("----- 秋天 ------");
        }
    },
    WINTER("冬天", "冰天雪地"){
        @Override
        public void printSeason() {
            System.out.println("----- 冬天 ------");
        }
    };

    //2.声明Season对象的属性:private final修饰
    private final String seasonName;
    private final String seasonDesc;

    //3.私有化类的构造器,并给对象属性赋值
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //4.其他诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    // 情况一：在enum类中实现接口中抽象方法
//    @Override
//    public void printSeason() {
//        System.out.println("打印季节。。。。");
//    }
}
