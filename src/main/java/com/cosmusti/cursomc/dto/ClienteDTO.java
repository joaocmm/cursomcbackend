
package com.cosmusti.cursomc.dto;

import com.cosmusti.cursomc.domain.Cliente;
import com.cosmusti.cursomc.services.validation.ClienteUpdate;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;


@ClienteUpdate
public class ClienteDTO implements Serializable {
    
    private Long id;
    
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min=5, max=120, message = "Nome deve possui entre 5 e 120 caracteres!")
    private String nome;
    
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "E-mail inválido!")
    private String email;

    public ClienteDTO() {
    }
    
    public ClienteDTO(Cliente obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();        
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
}
