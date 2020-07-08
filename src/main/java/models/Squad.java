package models;

import com.sun.tools.javac.util.List;

import java.util.ArrayList;

public class Squad {
    private int id,size;
    private String name,cause;
    private List<Hero> mHeroes;
    private static ArrayList<Squad> instances = new ArrayList<>();
    public Squad(String name,String cause,int size){

        this.name=name;
        this.cause=cause;
        this.size=size;
        instances.add(this);
        this.id = instances.size();
    }

    public int getSize() {
        return size;
    }

    public String getCause() {
        return cause;
    }


    public String getName() {
        return name;

    }

    public int getId() {
        return id;
    }
    public static void clearAllSquads(){
        instances.clear();
    }
    public static Squad findById(int id) {
        return instances.get(id - 1);
    }
    public static ArrayList<Squad> getAll(){
        return instances;
    }
    public void addHero(Hero hero){
        mHeroes.add(hero);
    }
    public List<Hero> getHeroes() {
        return mHeroes;
    }
    public void update(String name,String cause,int size) {
        this.name =name;
        this.size=size;
        this.size=size;

    }
    public void deleteSquad() {
        instances.remove(id - 1); //same reason


    }
}
