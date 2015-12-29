package com.arifcebe.realmio;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by arifcebe on 28/12/15.
 */
public class Cat extends RealmObject {
    @Required
    private String name;
    private int age;
    private int no;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
