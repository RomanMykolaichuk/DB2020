/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.knu.fit.mit31.dbis;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Roman
 */
@Entity
public class ChildTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private NewTable parent;

    ChildTable() {
    }

    ChildTable(String name, int age, NewTable parent) {
        this.name = name;
        this.age = age;
        this.parent = parent;

    }

    /**
     * @return the parent
     */
    public NewTable getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(NewTable parent) {
        this.parent = parent;
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
    
     @Override
    public String toString(){
    return parent.getName()+"( "+getName()+", "+getAge()+ ")";
    }
}
