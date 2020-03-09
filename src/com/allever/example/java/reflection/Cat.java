package com.allever.example.java.reflection;

import java.util.List;

public class Cat extends Animal{

    private int age;
    private float weight;

    public String id;

    public String publicName = "publicName";

    private List<String> cats;

    public List<String> getCats() throws NullPointerException {
        return cats;
    }

    public void setCats(List<String> cats) throws NullPointerException {
        this.cats = cats;
    }

    private Cat(int age) {

    }


    public Cat() {}


    public Cat(int age, float weight) {
        this.age = age;
    }

    private void sleep(int time) {

    }

    public void run() {

    }

    static class CatInner{

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    private void testNormal() {
        System.out.println("hello testNormal");
    }

    public static void testStatic(int age) {
        System.out.println("age = " + age);
    }

    private void updateAge(int age) {
        System.out.println(this.age + " update age to " + age);
        this.age = age;
    }

    private void testThrowAble() {
        throw new RuntimeException("throw a exception");
    }
}
