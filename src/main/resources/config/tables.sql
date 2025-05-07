CREATE TABLE personal(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre VARCHAR NOT NULL,
    apellidos VARCHAR NOT NULL,
    fecha_nacimiento DATE NOT NULL ,
    fecha_incorporacion DATE NOT NULL ,
    salario DECIMAL(2-9,2) NOT NULL,
    pais VARCHAR NOT NULL,
    rol VARCHAR NOT NULL,
    especialidad VARCHAR DEFAULT NULL,
    posicion VARCHAR DEFAULT NULL,
    dorsal INT DEFAULT NULL,
    altura DOUBLE(1,2) DEFAULT NULL,
    peso DOUBLE(1,2) DEFAULT NULL,
    goles INT DEFAULT NULL,
    partidos_jugados INT DEFAULT NULL
);
CREATE TABLE users(
    nombre_usuario VARCHAR PRIMARY KEY NOT NULL,
    contraseña VARCHAR NOT NULL,
    admin BOOLEAN NOT NULL DEFAULT FALSE
);