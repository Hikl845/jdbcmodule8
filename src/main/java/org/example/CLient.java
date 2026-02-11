package org.example;

public class CLient {
    private long id;
    private String name;

    public CLient(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
