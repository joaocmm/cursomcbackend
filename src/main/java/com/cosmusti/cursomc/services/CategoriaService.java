package com.cosmusti.cursomc.services;

import com.cosmusti.cursomc.domain.Categoria;
import com.cosmusti.cursomc.dto.CategoriaDTO;
import com.cosmusti.cursomc.repositories.CategoriaRepository;
import com.cosmusti.cursomc.services.exceptions.DataIntegrityException;
import com.cosmusti.cursomc.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria buscarPorId(Long id) {
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria inserir(Categoria obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Categoria atualizar(Categoria obj) {
        Categoria newObj = buscarPorId(obj.getId());
        atualizarDados(newObj, obj);
        return repository.save(obj);
    }

    public void remover(Long id) {
        buscarPorId(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }
    
    public List<Categoria> buscarTodos(){
        return repository.findAll();
    }
    
    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }
    
    public Categoria fromDTO(CategoriaDTO objDto){
        return new Categoria(objDto.getId(), objDto.getNome());
    }
    
    private void atualizarDados(Categoria newObj, Categoria obj){
        newObj.setNome(obj.getNome());
    }

}
