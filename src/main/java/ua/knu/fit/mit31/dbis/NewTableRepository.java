/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.knu.fit.mit31.dbis;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Roman
 */
public interface NewTableRepository extends JpaRepository<NewTable, Integer> {

    @Query(
            value = "SELECT * FROM NEW_TABLE t WHERE t.age > 20",
            nativeQuery = true)
    Collection<NewTable> findAllolder20();
    
    @Query(
            value = "SELECT * FROM NEW_TABLE t WHERE t.age > ?1",
            nativeQuery = true)
    Collection<NewTable> findAllolder(Integer age);
}
