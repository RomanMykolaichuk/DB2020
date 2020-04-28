/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.knu.fit.mit31.dbis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knu.fit.mit31.dbis.dao.ChildTable;

/**
 *
 * @author Roman
 */
public interface ChildTableRepository extends JpaRepository<ChildTable,Integer> {
    
}
