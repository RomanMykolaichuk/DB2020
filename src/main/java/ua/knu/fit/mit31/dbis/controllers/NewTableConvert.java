/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.knu.fit.mit31.dbis.controllers;

import ua.knu.fit.mit31.dbis.NewTable;

/**
 *
 * @author 38068
 */
public class NewTableConvert {

    private int id;
    private String name;
    private int age;
    private String child;

    public NewTableConvert(int id, String name, int age, String child) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.child = child;
    }
    
    public NewTableConvert(NewTable newTable) {

        this.id = newTable.getId();
        this.name = newTable.getName();
        this.age = newTable.getAge();
        this.child = newTable.getChildTable().toString();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the child
     */
    public String getChild() {
        return child;
    }

    /**
     * @param child the child to set
     */
    public void setChild(String child) {
        this.child = child;
    }

}
