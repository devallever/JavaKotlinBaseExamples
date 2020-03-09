package com.allever.example.java.reflection;

public class ReflectionTest {
    public static void main(String[] args) {
        new ReflectionTest().doMain();
    }

    public void doMain() {
        getClassObject();
    }


    /***
     * 获取Class 类的方式有三种
     */
    private void getClassObject() {
        Cat cat = new Cat();
        Class class1 = cat.getClass();

        Class class2 = Cat.class;

        try {
            Class class3 = Class.forName("com.allever.example.java.reflection.Cat");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
