CREATE TABLE usuarios (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          nombre VARCHAR(250) NOT NULL,
                          correoElectronico VARCHAR(100) NOT NULL,
                          contrasena VARCHAR(250) NOT NULL,
                          PRIMARY KEY (id)
);
