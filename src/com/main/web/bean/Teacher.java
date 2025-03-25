package com.main.web.bean;

public class Teacher {
    private String id;
    private String teano;
    private String name;
    private String age;
    private String sex;
    private String phone;
    private String address;


    public Teacher() {
    }

    public Teacher(String id, String teano, String name, String age, String sex, String phone, String address) {
        this.id = id;
        this.teano = teano;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     * @return teano
     */
    public String getTeano() {
        return teano;
    }

    /**
     * 设置
     * @param teano
     */
    public void setTeano(String teano) {
        this.teano = teano;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public String getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * 获取
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "Teacher{id = " + id + ", teano = " + teano + ", name = " + name + ", age = " + age + ", sex = " + sex + ", phone = " + phone + ", address = " + address + "}";
    }
}
