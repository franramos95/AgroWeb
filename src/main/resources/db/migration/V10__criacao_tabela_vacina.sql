CREATE TABLE vacina (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    lote VARCHAR(50) NOT NULL,
    data DATE,
    vencimento DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;