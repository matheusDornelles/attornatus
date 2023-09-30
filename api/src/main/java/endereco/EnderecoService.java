package endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pessoas.Pessoa;
import pessoas.PessoaRepository;

import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Endereco criarEndereco(Long pessoaId, Endereco endereco) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        endereco.setPessoa(pessoa);
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarEnderecos(Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        return pessoa.getEnderecos();
    }

    public void definirEnderecoPrincipal(Long pessoaId, Long enderecoId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        Endereco endereco = enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        pessoa.setEnderecoPrincipal(endereco);
        pessoaRepository.save(pessoa);
    }
}

