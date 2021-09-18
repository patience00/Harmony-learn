package com.linchtech.myapplication.domain;

public class Girl {

    private int photoId;

    private String name;

    private int age;

    private String address;

    public Girl() {
    }

    public Girl(int photoId, String name, int age, String address) {
        this.photoId = photoId;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "photoId=" + photoId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
