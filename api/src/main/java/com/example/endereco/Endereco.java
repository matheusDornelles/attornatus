package com.example.endereco;

import com.example.pessoas.Pessoa;
import jakarta.persistence.*;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public void setPessoa(Pessoa pessoa) {
    }

    public Object getId() {
        return this.id;
    }


}
