
package com.cosmusti.cursomc.dto;

import com.cosmusti.cursomc.domain.Categoria;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;


public class CategoriaDTO implements Serializable{
    
    private Long id;
    
    @NotEmpty(message = "Preencimento Obrigatório!")
    @Length(min=5, max=80, message = "Nome entre 5 e 80 caracteres!")
    private String nome;

    public CategoriaDTO() {
    }
    
    
    public CategoriaDTO(Categoria obj){
        id = obj.getId();
        nome = obj.getNome();
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
           
}
