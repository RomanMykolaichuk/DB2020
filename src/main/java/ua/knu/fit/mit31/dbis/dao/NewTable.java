/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.knu.fit.mit31.dbis.dao;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Roman
 */
@Entity
public class NewTable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    private String name;
    private int age;
    
     @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ChildTable> childTable ;
    
   public NewTable(){
   childTable = new HashSet<ChildTable>();
   }
   
   public NewTable(String name, int age){
   this.name=name;
   this.age=age;
   childTable = new HashSet<ChildTable>();
   }
   
   public NewTable(String name, int age, Set<ChildTable> childTable){
   this.name=name;
   this.age=age;
   this.childTable = childTable;
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
     * @return the childTable
     */
    public Set<ChildTable> getChildTable() {
        return childTable;
    }

    /**
     * @param childTable the childTable to set
     */
    public void setChildTable(Set<ChildTable> childTable) {
        this.childTable = childTable;
    }
    
     @Override
    public String toString(){
    return name+", "+age+ ", "+childTable;
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

}
