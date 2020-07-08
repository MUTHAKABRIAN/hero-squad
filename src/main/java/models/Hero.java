package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Hero {

    private String name;
    private int age;
    private String specialPower;
    private String weakness;
    private static ArrayList<Hero> instances = new ArrayList<>();
    private boolean completed;
    private LocalDateTime createdAt;
    private int id;


    public Hero(String name,int age,String specialPower,String weakness){
        this.name= name;
        this.age=age;
        this.specialPower=specialPower;
        this.weakness=weakness;
        this.completed = false;

        this.createdAt = LocalDateTime.now();
        instances.add(this);
        this.id = instances.size();
    }


    public String getWeakness() {
        return weakness;
    }

    public String getSpecialPower() {
        return specialPower;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public static ArrayList<Hero> getAll(){
        return instances;
    }

    public static void clearAllHeroes(){
        instances.clear();
    }

    public boolean getCompleted(){
        return this.completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public static Hero findById(int id){
        return instances.get(id-1); //why minus 1? See if you can figure it out.
    }

    public void update(String name,int age,String specialPower,String weakness) {
        this.name =name;
        this.age=age;
        this.specialPower=specialPower;
        this.weakness=weakness;
    }

    public void deleteHero(){
        instances.remove(id-1); //same reason
    }
}

