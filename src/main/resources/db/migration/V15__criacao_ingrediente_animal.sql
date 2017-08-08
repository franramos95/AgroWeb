CREATE TABLE item_dieta(
	id BIGINT(20) PRIMARY KEY NOT NULL,
	id_dieta BIGINT(20) NOT NULL,
	id_ingrediente BIGINT(20) NOT NULL,
	valor_unitario DECIMAL(15,2) NOT NULL,
	quantidade BIGINT(20) NOT NULL,
	FOREIGN KEY (id_dieta) REFERENCES dieta(id),
	FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;