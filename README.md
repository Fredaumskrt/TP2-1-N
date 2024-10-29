# TP2-1-N - CRUD com Índices e Relacionamentos


1. **O CRUD (com índice direto) de categorias foi implementado?**
   - **Sim**. O CRUD com índice direto de categorias foi implementado, incluindo operações de criação, leitura, atualização e exclusão.

2. **Há um índice indireto de nomes para as categorias?**
   - **Sim**. Um índice indireto foi implementado usando uma estrutura de árvore B+ que permite buscas por nomes de categorias.

3. **O atributo de ID de categoria, como chave estrangeira, foi criado na classe Tarefa?**
   - **Sim**. O atriubto de ID de categoria foi adicionado à classe `Tarefa` como chave estrangeira, possibilitando o relacionamento com categorias.

4. **Há uma árvore B+ que registre o relacionamento 1:N entre tarefas e categorias?**
   - **Sim**. Utilizou-se uma árvore B+ para registrar o relacionamento 1;N entre tarefas e categorias, permitindo listar as tarefas associadas a uma categoria específica.

5. **É possível listar as tarefas de uma categoria?**
   - **Sim**. O sistema permite listar as tarefas de uma categoria específica por meio do índice indireto implementado na árvore B+.

6. **A remoção de categorias checa se há alguma tarefa vinculada a ela?**
   - **Sim**. O sistema verifica se existem tarefas vinculadas a uma categoria antes de permitir sua remoção, garantindo a integridade do relacionamento.

7. **A inclusão da categoria em uma tarefa se limita às categorias existentes?**
   - **Sim**. Durante a inclusão de uma categoria em uma tarefa, o sistema restringe a escolha às categorias já existentes, evitando associações inválidas.

8. **O trabalho está funcionando corretamente?**
   - **Sim - Parcialmente**. A estrutura e as funcionalidades principais foram configuradas, porém, está com alguns problemas que não foram sanados, mas as funcionalidades principais estão rodando corretamente, está incluindo, excluindo, listando e imprimindo.

9. **O trabalho está completo?**
   - **Sim**. O trabalho contempla tdoas as partes solicitadas, incluindo CRUD com índice direto, índice indireto, relacionamento 1:N e funcionalidades adicionais para tarefas e categorias.

10. **O trabalho é original e não a cópia de um trabalho de outro grupo?**
    - **Sim**. O trabalho é original feito por mim e com ajuda do GPT.

---
