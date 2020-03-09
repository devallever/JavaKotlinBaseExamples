package com.allever.example.java.reflection;

import com.allever.example.java.util.LogUtils;

public class ReflectionTest {
    public static void main(String[] args) {
        new ReflectionTest().doMain();
    }

    public void doMain() {
        getClassObject();
        className();
    }


    /***
     * 获取Class 类的方式有三种
     */
    private void getClassObject() {
        Cat cat = new Cat();
        Class classObj = cat.getClass();

        classObj = Cat.class;
        classObj = int.class;
        classObj = String.class;

        try {
            classObj = Class.forName("com.allever.example.java.reflection.Cat");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /***
     * 获取类名
     */
    private void className() {
        print("\n引用类型");
        printClzName(Cat.class);

        print("\n内部类");
        printClzName(Cat.CatInner.class);

        print("\n基本类型");
        printClzName(int.class);

        print("\n基本类型数组");
        printClzName(new int[2][2].getClass());

        print("\n引用类型数组");
        printClzName(new Cat[2][2].getClass());

        print("\n匿名内部类");
        printClzName(new Runnable() {
            @Override
            public void run() {

            }
        }.getClass());

        class Test{ }
        print("\n局部内部类");
        printClzName(Test.class);

    }

    private void printClzName(Class clz) {
        print("getName = " + clz.getName());
        print("getSimpleName = " + clz.getSimpleName());
        print("getCanonicalName = " + clz.getCanonicalName());
    }

    private void print(String msg) {
        LogUtils.d(msg);
    }

}
