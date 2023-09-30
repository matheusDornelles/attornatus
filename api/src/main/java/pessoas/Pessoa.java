package pessoas;

import endereco.Endereco;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public List<Endereco> getEnderecos() {
        return this.enderecos;
    }

    public Object getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Object dataNascimento) {
    }

    public void setEnderecoPrincipal(Endereco endereco) {
    }

}