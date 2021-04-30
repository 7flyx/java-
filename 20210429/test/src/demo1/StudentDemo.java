package demo1;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentDemo {
    public static void main(String[] args) {
        //制作学生管理系统--增删查改的基本操作

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> array = new ArrayList<>();

        while(true){
            menu();

            System.out.printf("请输入选择:");
            int choice = sc.nextInt();

            switch(choice){
                case 1:  //添加
                    AddStudent(array);
                    break;
                case 2:  //删除
                    DelStudent(array);
                    break;
                case 3: //修改
                    UpdataStudent(array);
                    break;
                case 4:  //查看
                    LookStudent(array);
                    break;
                case 0:
                    System.out.println("谢谢使用");
                    System.exit(0);  //JVM直接关闭，即就是直接退出
                default:
                    System.out.printf("选择错误，请重新输入:");
                    break;
            }
        }
    }

    public static void menu() {
        System.out.println("--------学生管理系统--------");
        System.out.println("---- 1. 添加   2. 删除 ----");
        System.out.println("---- 3. 修改   4. 查看 ----");
        System.out.println("---- 0. 退出          ----");
        System.out.println("--------------------------");
    }

    public static void AddStudent(ArrayList<Student> array) {
        Scanner sc = new Scanner(System.in);
        String ID = "";
        boolean cond = true;
        while(cond){
            System.out.printf("请输入学生学号:");
            ID = sc.nextLine();
            if(array.size() == 0){
                cond = false;
            }
            for(int i = 0; i < array.size(); i++) {
                Student s = array.get(i);
                if(s.getID().equals(ID)) {
                    System.out.println("系统中已有该学号的数据，请重新输入:");
                } else {
                    cond = false;
                    break;
                }
            }
        }

        Student s = new Student();
        s.setID(ID);
        System.out.printf("请输入学生姓名:");
        s.setName(sc.nextLine());
        System.out.printf("请输入学生年龄:");
        s.setAge(sc.nextLine());
        System.out.printf("请输入学生地址:");
        s.setAddress(sc.nextLine());
        array.add(s);
        System.out.println("添加成功");
    }

    public static void DelStudent(ArrayList<Student> array) {
        if(array.size() == 0) {
            System.out.println("没有信息需要被删除，请先添加");
            return;
        }
        int index = -1;
        Student s = new Student();
        String ID = "";
        Scanner sc = new Scanner(System.in);
        boolean cond = true;
        while(cond){
            System.out.printf("请输入需要删除的学号:");
            ID = sc.nextLine();

            for(int i = 0; i < array.size(); i++) {
                s = array.get(i);
                if (s.getID().equals(ID)) {
                    array.remove(i);
                    System.out.println("删除成功");
                    return;
                }
            }
            System.out.println("系统里没有找到该学生信息!!!");
        }
    }

    public static void LookStudent(ArrayList<Student> array) {
        if (array.size() == 0) {
            System.out.println("系统还没有学生信息，请先添加");
        }
        Student s = new Student();
        System.out.println("学号\t\t\t" +"姓名\t\t" + "年龄\t\t" + "地址\t");
        for (int i = 0; i < array.size(); i++) {
            s = array.get(i);
            System.out.println(s.getID() + "\t\t"+ s.getName() + "\t\t" + s.getAge() + "\t\t" + s.getAddress());
        }
    }

    public static void UpdataStudent(ArrayList<Student> array) {
        if (array.size() == 0) {
            System.out.println("系统还没有学生信息，请先添加");
        }

        Scanner sc = new Scanner(System.in);
        Student s = new Student();
        String ID = "";
        int index = -1;

        while (true){
            System.out.printf("请输入被修改学生的学号:");
            ID = sc.nextLine();
            for (int i = 0; i < array.size(); i++) {
                s = array.get(i);
                if (s.getID().equals(ID)) {
                    array.remove(i); //先删除集合中的元素，再进行添加。暂时不知道修改的方法是啥
                    s.setID(ID);
                    System.out.printf("请输入学生姓名:");
                    s.setName(sc.nextLine());
                    System.out.printf("请输入学生年龄:");
                    s.setAge(sc.nextLine());
                    System.out.printf("请输入学生地址:");
                    s.setAddress(sc.nextLine());
                    array.add(s);
                    System.out.println("修改成功");
                    return;
                }
            }
            System.out.println("系统中没有找到该学生信息，请重试!!!");
        }
    }
}
