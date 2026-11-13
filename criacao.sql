-- 0) Limpa as tabelas (ordem inversa da criação), para poder rodar várias vezes
DROP TABLE IF EXISTS emprestimo;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS item;

-- 1) Tabelas independentes primeiro
CREATE TABLE item (
    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo     VARCHAR(20)  NOT NULL UNIQUE,
    titulo     VARCHAR(150) NOT NULL,
    tipo       VARCHAR(20)  NOT NULL CHECK (tipo IN ('livro', 'revista')),
    autor      VARCHAR(100),            -- revista pode não ter autor
    edicao     INT,
    disponivel BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE usuario (
    id           INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome         VARCHAR(100) NOT NULL,
    tipo         VARCHAR(20)  NOT NULL CHECK (tipo IN ('aluno', 'professor')),
    limite_itens INT          NOT NULL
);

-- 2) Por último, a tabela que depende das outras duas
CREATE TABLE emprestimo (
    id                      INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id                 INT          NOT NULL REFERENCES item(id),
    usuario_id              INT          NOT NULL REFERENCES usuario(id),
    data_retirada           DATE         NOT NULL,
    data_devolucao_prevista DATE         NOT NULL,
    data_devolucao          DATE,        -- sem NOT NULL: fica vazia até devolver
    valor_multa             NUMERIC(8,2) NOT NULL DEFAULT 0
);

-- 3) Dados de teste
INSERT INTO item (codigo, titulo, tipo, autor, edicao, disponivel) VALUES
('L1', 'Dom Casmurro',      'livro',   'Machado de Assis', 1,   TRUE),
('L2', 'O Cortiço',         'livro',   'Aluísio Azevedo',  1,   TRUE),
('R1', 'Superinteressante', 'revista', NULL,               450, FALSE),
('L3', 'Memórias Póstumas', 'livro',   'Machado de Assis', 2,   TRUE);

INSERT INTO usuario (nome, tipo, limite_itens) VALUES
('Ana',    'aluno',     3),
('Carlos', 'professor', 5);

INSERT INTO emprestimo
    (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa) VALUES
-- já devolvido, com 3 dias de atraso (3 x R$ 0,50)
(1, 1, '2026-10-01', '2026-10-15', '2026-10-18', 1.50),
-- em aberto (data_devolucao fica NULL)
(3, 2, '2026-11-05', '2026-11-12', NULL,         0);