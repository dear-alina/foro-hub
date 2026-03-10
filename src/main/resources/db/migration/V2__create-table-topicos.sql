CREATE TABLE topicos (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         titulo VARCHAR(400) NOT NULL,
                         mensaje TEXT NOT NULL,
                         fechaCreacion DATETIME NOT NULL,
                         estado VARCHAR(20) NOT NULL,
                         usuario BIGINT NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (usuario) REFERENCES usuarios(id)
);
