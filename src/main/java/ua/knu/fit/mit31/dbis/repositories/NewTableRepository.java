/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.knu.fit.mit31.dbis.repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.knu.fit.mit31.dbis.dao.NewTable;

/**
 *
 * @author Roman
 */
public interface NewTableRepository extends JpaRepository<NewTable, Integer> {

    @Query(
            value = "SELECT * FROM NEW_TABLE t WHERE t.age > 20",
            nativeQuery = true)
    Collection<NewTable> findAllOlder20();
    
    @Query(
            value = "SELECT * FROM NEW_TABLE t WHERE t.age > ?1",
            nativeQuery = true)
    Collection<NewTable> findAllOlder(Integer age);
    
    @Query(
            value = "SELECT p.* FROM new_table p\n" +
"left join child_table c\n" +
"on p.id = c.parent_id\n" +
"group by p.id\n" +
"having count(c.parent_id) <> 0\n" +
"order by count(c.parent_id) desc;",
            nativeQuery = true)
    Collection<NewTable> findParents();
    
    @Query(
            value = "SELECT p.* FROM new_table p\n" +
"left join child_table c\n" +
"on p.id = c.parent_id\n" +
"group by p.id\n" +
"having count(c.parent_id) = ?1\n" +
"order by count(c.parent_id) desc;",
            nativeQuery = true)
    Collection<NewTable> findByChildrenAmount(Integer parent);
    
    
}
