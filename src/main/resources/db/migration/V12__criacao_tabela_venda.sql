CREATE TABLE venda (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_criacao DATETIME NOT NULL,
    valor_frete DECIMAL (10,2),
    valor_desconto DECIMAL (10,2),
    status VARCHAR(30) NOT NULL,
    data_hora_entrega DATETIME,
    id_comprador BIGINT(20) NOT NULL,
    codigo_usuario BIGINT(20) NOT NULL,
    valor_arroba DECIMAL(10,2) NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_comprador) REFERENCES comprador(id),
    FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE dieta (
	id BIGINT(20 ) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(20) NOT NULL,
	tipo VARCHAR(20) NOT NULL,
	quantidade INTEGER(3) NOT NULL,
	id_ingrediente BIGINT(20 ) NOT NULL,
	FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE animal(
	id BIGINT(20 ) PRIMARY KEY AUTO_INCREMENT,
	id_brinco BIGINT(20) NOT NULL,
	nome VARCHAR(20) NOT NULL,
	lote VARCHAR(20) NOT NULL,
	sexo VARCHAR(20) NOT NULL,
	idade INTEGER(3) NOT NULL,
	data_nascimento DATETIME NOT NULL,
	data_aquisicao DATETIME,
	situacao VARCHAR(20) NOT NULL,
	id_especie BIGINT(20) NOT NULL,
	id_dieta BIGINT(20) NOT NULL,
	id_venda BIGINT(20),
	FOREIGN KEY (id_especie) REFERENCES especie(id),
	FOREIGN KEY (id_dieta) REFERENCES dieta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item_venda(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	valor_unitario DECIMAL(10,2) NOT NULL,
	id_animal BIGINT(20) NOT NULL,
	id_venda BIGINT(20) NOT NULL,
	FOREIGN KEY (id_animal) REFERENCES animal(id),
    FOREIGN KEY (id_venda) REFERENCES venda(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;