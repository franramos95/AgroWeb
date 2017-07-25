CREATE TABLE animal_vacina(
	id_animal BIGINT(20) NOT NULL,
	id_vacina BIGINT(20) NOT NULL,
	data_vacinacao DATETIME NOT NULL,
	FOREIGN KEY (id_vacina) REFERENCES vacina(id),
	FOREIGN KEY (id_animal) REFERENCES animal(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE animal_doenca(
	id_animal BIGINT(20) NOT NULL,
	id_doenca BIGINT(20) NOT NULL,
	data_vacinacao DATETIME NOT NULL,
	FOREIGN KEY (id_doenca) REFERENCES doenca(id),
	FOREIGN KEY (id_animal) REFERENCES animal(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;