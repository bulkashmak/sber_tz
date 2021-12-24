package ru.bulkashmak;

import java.util.Arrays;
import java.util.List;

public class User {

    private String email;
    private String name;
    private int[] tasks;
    private int[] companies;
    private String hobby;
    private String phone;
    private String inn;

    public User(String email, String name, int[] tasks, int[] companies, String hobby, String phone, String inn) {
        this.email = email;
        this.name = name;
        this.tasks = tasks;
        this.companies = companies;
        this.hobby = hobby;
        this.phone = phone;
        this.inn = inn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getTasks() {
        return tasks;
    }

    public void setTasks(int[] tasks) {
        this.tasks = tasks;
    }

    public int[] getCompanies() {
        return companies;
    }

    public void setCompanies(int[] companies) {
        this.companies = companies;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", tasks=" + Arrays.toString(tasks) +
                ", companies=" + Arrays.toString(companies) +
                ", hobby='" + hobby + '\'' +
                ", phone='" + phone + '\'' +
                ", inn='" + inn + '\'' +
                '}';
    }
}
