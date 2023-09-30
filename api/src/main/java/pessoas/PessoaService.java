package pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa consultarPessoa(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa editarPessoa(Long id, Pessoa pessoa) {
        Pessoa pessoaExistente = consultarPessoa(id);
        pessoaExistente.setNome(pessoa.getNome());
        pessoaExistente.setDataNascimento(pessoa.getDataNascimento());
        return pessoaRepository.save(pessoaExistente);
    }
}
