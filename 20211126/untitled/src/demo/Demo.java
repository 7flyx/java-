package demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-11-26
 * Time: 14:17
 * Description:
 */



public class Demo {

    class Cat{
        public String name;
        private int age;
        private Cat(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return name + " " + age;
        }
    }

    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("demo.Cat");

            Constructor<?> constructor = cls.getDeclaredConstructor(String.class, int.class);
            constructor.setAccessible(true);
            Cat tom = (Cat)constructor.newInstance("Tom", 20);

            Field field = cls.getDeclaredField("age");
            field.setAccessible(true);
            field.set(tom,30);
            System.out.println(tom);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
