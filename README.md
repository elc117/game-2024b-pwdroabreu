# Quizzossauro 🦖

## Sobre o Jogo
**Quizzossauro** é um jogo interativo no qual o jogador deve responder perguntas relacionadas a paleontologia, geografia e cultura local. O objetivo é explorar o cenário enquanto aprende e testa seus conhecimentos, avançando por meio de interações com objetos no ambiente.

---

## Autoria
**Nome:** Pedro Henrique G. Abreu  
**Curso:** Sistemas de Informação - Universidade Federal de Santa Maria (UFSM)

---

## Comentários sobre o Processo de Desenvolvimento
### Planejamento e Ideia
O desenvolvimento do jogo começou com a ideia de criar um quiz associado com as demandas do trabalho. A escolha do tema paleontologia foi inspirada no Geoparque Quarta Colônia.

### Tecnologias Utilizadas
- **Linguagem:** Java
- **Framework:** LibGDX
- **IDE:** IntelliJ IDEA / VSCode
- **Controle de Versão:** Git e GitHub

### Desafios Encontrados
1. **Centralização e Renderização de Textos:** 
   Trabalhar com a renderização dinâmica de perguntas e opções foi um desafio. Soluções como o uso de `GlyphLayout` para medir o texto foram implementadas.
2. **Interação com Objetos:**
   Foi necessário criar um sistema de colisão eficiente usando `Rectangle` para detectar interações.
3. **Gerenciamento de Estados do Jogo:**
   A transição entre o estado de exploração e o modo de quiz exigiu uma boa organização do código.
