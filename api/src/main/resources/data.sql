CREATE TABLE Pessoa (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    data_nascimento DATE
);
INSERT INTO Pessoa (nome, data_nascimento) VALUES ('Matheus', '1991-02-01');
INSERT INTO Endereco (logradouro, cidade, pessoa_id) VALUES ('Rua Exemplo 123', 'Cidade Exemplo', @pessoaId);
