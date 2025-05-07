DELETE FROM users;
DELETE FROM empleados;
INSERT INTO users (nombre_usuario,contraseña) VALUES
    ('admin','contraseñasegura',true),
    ('emilis246','emiliscontraseña',false);