# Senta Aí - Backend

API REST do projeto **Senta Aí**, desenvolvida para a Global Solution 2026.2 (CitizenTech) - FIAP, disciplina de Domain Driven Design - Java.

## Sobre o projeto

Quem espera ônibus não sabe se o próximo vai chegar lotado. O Senta Aí resolve isso com um sensor (Arduino) na porta do ônibus que conta quantas pessoas entram e saem em cada ponto. A API recebe essas leituras, calcula a ocupação em tempo real e o app mostra para quem está no ponto se o ônibus **tem lugar**, está **cheio** ou **lotado**.

## Regras de negócio

- Cada leitura do sensor atualiza a ocupação do ônibus: `ocupação = ocupação + entradas - saídas`
- Não podem sair mais pessoas do que estão a bordo
- A ocupação só muda por leituras do sensor, nunca editando o ônibus direto
- Status da lotação: abaixo de 60% **Tem lugar**, de 60% a 90% **Cheio**, acima de 90% **Lotado**
- Código da linha e placa do ônibus não podem repetir
- Um ponto não pode aparecer duas vezes no trajeto da mesma linha

## Integrantes

| Nome | RM |
|---|---|
| Levi de Jesus | RM563279 |
