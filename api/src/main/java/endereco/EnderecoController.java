package endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pessoas.PessoaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas/enderecos")
public class EnderecoController {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<List<Endereco>> listarEnderecos(@PathVariable Long pessoaId) {
        return pessoaRepository.findById(pessoaId)
                .map(pessoa -> ResponseEntity.ok().body(pessoa.getEnderecos()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@PathVariable Long pessoaId, @RequestBody Endereco endereco) {
        return pessoaRepository.findById(pessoaId)
                .map(pessoa -> {
                    endereco.setPessoa(pessoa);
                    Endereco novoEndereco = enderecoRepository.save(endereco);
                    return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{enderecoId}/principal")
    public ResponseEntity<Void> marcarEnderecoPrincipal(
            @PathVariable Long pessoaId, @PathVariable Long enderecoId) {
        return pessoaRepository.findById(pessoaId)
                .map(pessoa -> {
                    List<Endereco> enderecos = pessoa.getEnderecos();
                    for (Endereco endereco : enderecos) {
                        if (endereco.getId().equals(enderecoId)) {
                            endereco.setPrincipal(true);
                        } else {
                            endereco.setPrincipal(false);
                        }
                    }
                    enderecoRepository.saveAll(enderecos);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
