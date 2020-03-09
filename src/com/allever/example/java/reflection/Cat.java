package com.allever.example.java.reflection;

public class Cat extends Animal{

    private int age;
    private float weight;

    public String id;

    public String publicName = "publicName";

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
}
