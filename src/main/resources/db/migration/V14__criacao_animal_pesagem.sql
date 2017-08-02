CREATE TABLE animal_pesagem(
	id_animal BIGINT(20) NOT NULL,
	id_pesagem BIGINT(20) NOT NULL,
	data DATE NOT NULL,
	FOREIGN KEY (id_animal) REFERENCES animal(id),
	FOREIGN KEY (id_pesagem) REFERENCES pesagem(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE pesagem DROP COLUMN data