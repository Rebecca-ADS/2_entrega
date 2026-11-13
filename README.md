# Sistema de Biblioteca (Java + PostgreSQL)

Lista de exercícios da Formação Desenvolvedor Full Stack Júnior (Codifica).
Sistema de biblioteca de bairro com Programação Orientada a Objetos em Java
e modelagem de banco de dados em PostgreSQL.

## Regras do negócio

| Item | Prazo | Multa por dia |
|---|---|---|
| Livro | 14 dias | R$ 0,50 |
| Revista | 7 dias | R$ 1,00 |

| Usuário | Limite de itens simultâneos |
|---|---|
| Aluno | 3 |
| Professor | 5 |

## Exercício 1: Java (POO)

- `ItemBiblioteca` (abstrata): código, título e disponibilidade, com `prazoDias()` e `multaPorDia()` abstratos, implementados em `Livro` e `Revista`.
- `Usuario` (abstrata): nome e quantidade emprestada, com `limiteItens()` abstrato, implementado em `Aluno` e `Professor`.
- `Biblioteca`: `emprestar()`, `devolver()` e `listarAcervo()`, usando array de tamanho fixo.
- `Main`: cenário de teste com cadastro, empréstimos aceitos e um empréstimo recusado por limite atingido.
- `DVD`: categoria extra criada sem alterar nenhuma classe existente, para demonstrar o polimorfismo.

Decisões de projeto:

- Prazo e multa são métodos abstratos, e não atributos, porque variam por tipo.
- Não existe `setDisponivel()` público: a disponibilidade muda só como consequência de `emprestar()` e `devolver()`.
- `listarAcervo()` percorre o array em um único laço, sem `if` de tipo.

### Como executar

Com o JDK instalado, dentro da pasta do projeto:

```
javac *.java
java Main
```

## Exercício 2: Modelagem SQL

O arquivo `criacao.sql` contém:

- as tabelas `item`, `usuario` e `emprestimo`, criadas na ordem correta;
- chaves primárias com `GENERATED ALWAYS AS IDENTITY`;
- chaves estrangeiras de `emprestimo` para `item` e `usuario`;
- `CHECK` para restringir o campo `tipo`;
- dados de teste: 4 itens, 2 usuários e 2 empréstimos (um em aberto e um devolvido).

## Exercício 3: Consultas SQL

O arquivo `consultas.sql` contém 4 consultas:

1. Todo o acervo, com código, título, tipo e disponibilidade.
2. Empréstimos em aberto, com o nome do usuário e o título do item.
3. Total de multas acumuladas por usuário.
4. Itens que nunca foram emprestados.

### Como executar

No pgAdmin: crie o banco com `CREATE DATABASE biblioteca;`, abra o Query Tool
nesse banco e execute `criacao.sql` e depois `consultas.sql`.

## Estrutura

```
2_entrega/
├── ItemBiblioteca.java   Livro.java   Revista.java   DVD.java
├── Usuario.java          Aluno.java   Professor.java
├── Biblioteca.java       Main.java
├── criacao.sql
├── consultas.sql
└── README.md
```

## Autora

Rebecca Corrêa
