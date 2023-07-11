package com.sugiartha.juniorandroid.model;

public class Data {
    private String id, name, address, umur , hoby;

    public Data() {
    }

    public Data(String id, String name, String address , String umur ,String hoby) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.umur = umur;
        this.hoby = hoby;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUmur(){return umur;}

    public void  setUmur(String umur){this.umur = umur;}

    public String getHoby(){return hoby;}

    public void  setHoby(String hoby){this.hoby = hoby;}
}
