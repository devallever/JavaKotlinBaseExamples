package com.allever.example.java.reflection;

import com.allever.example.java.util.LogUtils;

import java.lang.reflect.*;

public class ReflectionTest {
    public static void main(String[] args) {
        new ReflectionTest().doMain();
    }

    public void doMain() {
        getClassObject();
        className();
        classModifier();
        classFiled();
        classMethod();
        classConstructor();
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

    private void classModifier() {
        print("\n");
        printClassModifier(Animal.class);
        printClassModifier(Cat.CatInner.class);
    }

    private void classFiled() {
        Class clz = Animal.class;
        try {
            //获取私有属性
            Field declareField = clz.getDeclaredField("priField");
            //获取非私有属性，包括父类
            Field field = clz.getField("head");
            //获取全部属性，不包括父类
            Field[] declaredFields = clz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                print("declareField name = " + declaredField.getName());
                print("declareField generic Type = " + declaredField.getGenericType());
                print("declareField type = " + declaredField.getType());
            }

            //获取全部public属性，包括父类
            Field[] fields = clz.getFields();
            for (Field fieldItem : fields) {
                print("field name = " + fieldItem.getName());
            }

            Cat catA = new Cat();
            catA.publicName = "xm";
            print("catA name = " + catA.publicName);

            Class catClz = Cat.class;
            Field nameField = catClz.getField("publicName");
            String catName = (String) nameField.get(catA);
            print("反射获取name = " + catName);
            nameField.set(catA, "winchen");
            print("反射修改后 catA name = " + catA.publicName);

            Field ageField = catClz.getDeclaredField("age");
            ageField.setAccessible(true);
            ageField.setInt(catA, 13);
            print("修改后， catA age = " + catA.getAge());

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void classMethod() {
        Class clz = Cat.class;
        try {
            //获取私有方法
            Method declareMethod = clz.getDeclaredMethod("sleep", int.class);
            //获取非私有方法
            Method method = clz.getMethod("setAge", int.class);

            //获取所有方法，不包括父类
            Method[] declareMethods = clz.getDeclaredMethods();
            for (Method declareMethodObj : declareMethods) {
                print("getDeclaredMethods 方法名： " + declareMethodObj.getName());
                Parameter[] parameters = declareMethodObj.getParameters();
                print("以下是" + declareMethodObj.getName() + "的参数列表");
                for (Parameter parameter : parameters) {
                    print("getParameters 参数名： " + parameter.getName());
                    print("getParameters 参数类型： " + parameter.getType().getName());
                }

                Class[] parameterClz = declareMethodObj.getParameterTypes();
                for (Class aClass : parameterClz) {
                    print("getParameterTypes 参数类型： " + aClass.getName());
                }

                for (Type genericParameterType : declareMethodObj.getGenericParameterTypes()) {
                    print("getGenericParameterTypes 参数类型： " + genericParameterType.getTypeName());
                }

                print("以下是" + declareMethodObj.getName() + "返回值类型");
                print(declareMethod.getName() + "的返回类型是： " +  declareMethodObj.getReturnType().getName());

                int modifierValue = declareMethodObj.getModifiers();
                print("以下是" + declareMethodObj.getName() + "修饰符");
                print(declareMethod.getName() + "的修饰符是： " +  Modifier.toString(modifierValue));

                print("以下是" + declareMethodObj.getName() + "异常类型");
                for (Class<?> exceptionType : declareMethodObj.getExceptionTypes()) {
                    print("getExceptionTypes 异常类型： " + exceptionType.getName());
                }

                for (Type genericExceptionType : declareMethodObj.getGenericExceptionTypes()) {
                    print("getGenericExceptionTypes 异常类型： " + genericExceptionType.getTypeName());
                }
            }

            print("");

            //获取所有public 方法　包括父类
            Method[] methods = clz.getMethods();
            for (Method methodObj : methods) {
                print("getMethods 方法名：" + methodObj.getName());
            }

            print("以下是方法的操作");
            Cat cat = new Cat(30, 10f);
            Method staticMethod = clz.getMethod("testStatic", int.class);
            try {
                staticMethod.invoke(null, 3);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            Method testNormalMethod = clz.getDeclaredMethod("testNormal");
            testNormalMethod.setAccessible(true);
            try {
                testNormalMethod.invoke(cat);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            Method normalMethod = clz.getDeclaredMethod("updateAge", int.class);
            normalMethod.setAccessible(true);

            try {
                normalMethod.invoke(cat, 12);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


//            Method throwMethod = clz.getDeclaredMethod("testThrowAble", null);
//            throwMethod.setAccessible(true);
//            try {
//                throwMethod.invoke(cat, null);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//                e.getCause().printStackTrace();
//            }


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void classConstructor() {
        Class clz = Cat.class;
        try {
            Constructor devlareContructor = clz.getDeclaredConstructor(int.class);
            Constructor constructor = clz.getConstructor(int.class, float.class);

            Constructor[] declareConstructors = clz.getDeclaredConstructors();
            for (Constructor declareConstructor : declareConstructors) {
                print("getDeclaredConstructors 名字： " + declareConstructor.toString());
            }

            Constructor[] constructors = clz.getConstructors();
            for (Constructor constructorObj : constructors) {
                print("getConstructors 名字： " + constructorObj.toString());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void printClassModifier(Class clz) {
        int value = clz.getModifiers();
        String modifers = Modifier.toString(value);
        print("类修饰符： " + modifers);
        print("isPublic = " + Modifier.isPublic(value));
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
