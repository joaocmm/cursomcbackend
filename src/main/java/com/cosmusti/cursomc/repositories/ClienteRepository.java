
package com.cosmusti.cursomc.repositories;

import com.cosmusti.cursomc.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
    
}
