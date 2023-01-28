package com.spring.kafka.vo;

public class Greeting {
    private String name;
    private String message;

    protected Greeting() {}

    public Greeting(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
