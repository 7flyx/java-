package demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Student {
    private String name = "su";
    private int age = 19;

    private Student() {
		
    }

    private Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void func1() {
        System.out.println("����һ��˽�з���");
    }

    public void func2() {
        System.out.println("����һ�����з���");
    }

    public String toString() {
        return name + " " + age;
    }
}

public class L12 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        test1();
        test2();
        test3();
        test4();
    }

    //��ȡ�޲ι���
    public static void test1() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> cl = Class.forName("L1.Student");
        Constructor<?> constructor = cl.getDeclaredConstructor(); //�õ��޲ι���
        constructor.setAccessible(true); //���÷���Ȩ��
        Student student = (Student) constructor.newInstance(); //��ȡ����
        System.out.println(student);
    }

    //��ȡ���������Ĺ��췽��
    public static void test2() {
        Class<?> cl = Student.class;
        try {
            Constructor<?> constructor = cl.getDeclaredConstructor(String.class, int.class);
            constructor.setAccessible(true); //���÷���Ȩ��
            Student student = (Student) constructor.newInstance("haha", 18);
            System.out.println(student);

        } catch (NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //��ȡ˽�е���ͨ����
    public static void test3() {
        Class<?> cl = null;
        try {
            cl = Class.forName("L1.Student");
            Method method = cl.getDeclaredMethod("func1");
            Constructor<?> constructor = cl.getDeclaredConstructor(String.class, int.class); //�޲ι���
            method.setAccessible(true); //����Ȩ��
            constructor.setAccessible(true);
            Student student = (Student) constructor.newInstance("hhff", 10);
            method.invoke(student); //����method�������
            System.out.println(student);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //��ȡ˽���ֶ�
    public static void test4() {
        Class<?> cl = null;
        try {
            cl = Class.forName("L1.Student");
            Field field = cl.getDeclaredField("name");
            field.setAccessible(true);
            Constructor<?> constructor = cl.getDeclaredConstructor();
            constructor.setAccessible(true);

            Student student = (Student) constructor.newInstance();
            field.set(student, "����");
            System.out.println(student);
            String str = (String) field.get(student);
            System.out.println(str);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
