CREATE TABLE ingrediente_dieta(
	id_dieta BIGINT(20) NOT NULL,
	id_ingrediente BIGINT(20) NOT NULL,
	FOREIGN KEY (id_dieta) REFERENCES dieta(id),
	FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;