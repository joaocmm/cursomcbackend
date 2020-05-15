
package com.cosmusti.cursomc.services;

import com.cosmusti.cursomc.domain.Categoria;
import com.cosmusti.cursomc.domain.Produto;
import com.cosmusti.cursomc.repositories.CategoriaRepository;
import com.cosmusti.cursomc.repositories.ProdutoRepository;
import com.cosmusti.cursomc.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository repository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public Produto buscarPorId(Long id){
        Optional<Produto> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+ Produto.class.getName()));
    }
    
    public Page<Produto> buscar(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
        
    }
    
}
