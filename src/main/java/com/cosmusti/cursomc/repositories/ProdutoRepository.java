
package com.cosmusti.cursomc.repositories;

import com.cosmusti.cursomc.domain.Categoria;
import com.cosmusti.cursomc.domain.Produto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    //@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    //Page<Produto> buscar(@Param("nome")String nome, @Param("categorias") List<Categoria>categorias, Pageable pageRequest);
    
    @Transactional(readOnly = true)
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
    
}
