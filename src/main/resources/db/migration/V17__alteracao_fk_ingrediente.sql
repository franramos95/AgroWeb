ALTER TABLE ingrediente ADD CONSTRAINT id_despesa
FOREIGN KEY (id_despesa) REFERENCES despesa(id);