INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 1);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 2);


INSERT INTO usuario (nome, email, senha, ativo) VALUES ('Admin', 'admin@agroweb.com', '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG', 1);


INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES (
(SELECT codigo FROM usuario WHERE email = 'admin@agroweb.com'), 1);