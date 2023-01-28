package com.spring.kafka.vo;

public class Farewell {
    private String name;

    private int age;

    private String message;

    protected Farewell() {}

    public Farewell(String name, int age, String message) {
        this.name = name;
        this.age = age;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Farewell{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", message='" + message + '\'' +
                '}';
    }
}
