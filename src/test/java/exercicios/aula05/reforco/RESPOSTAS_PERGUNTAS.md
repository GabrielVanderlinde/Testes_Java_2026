# Respostas - Aula 05 - Reforco

## DESAFIO 1: Por que nao usar 12 metodos @Test?
Repetitivo, dificil manutencao, menos legivel, viola DRY. @ParameterizedTest testa tudo em um metodo.

## DESAFIO 2: @NullAndEmptySource testa espacos?
Nao. Testa apenas null e "". Use @ValueSource(strings = {" ", "  "}) para espacos.

## DESAFIO 3: Por que @CsvSource melhor que @ValueSource?
Relaciona entrada-saida, formato tabular, multiplos parametros, melhor visualizacao.

## DESAFIO 5: Tabela resultados esperados
15,false,false | 15,true,false | 16,true,true | 16,false,false | 17,true,true | 17,false,false | 18,true,true | 18,false,true | 25,false,true

## DESAFIO 6: Casos teste escolhidos
1:5.0 | 2:10.0 | 3:10.0 | 4:15.0 | 5:15.0 | 6:15.0 | 7:25.0 | 8:25.0 | 10:25.0 | 12:25.0

## DESAFIO 7: Tres casos adicionais
MinhaSenha123:true | XyZ12345:true | Abcdefg1:true

## DESAFIO 8: Resultados esperados
admin,Senai123,true:true | admin,senhaErrada,true:false | usuario,Senai123,true:false | admin,Senai123,false:false | null,Senai123,true:false | admin,null,true:false | null,null,true:false | user,pass,true:false

## DESAFIO 9: Implementacao
0-4.9:REPROVADO | 5.0-6.9:RECUPERACAO | 7.0-10.0:APROVADO | <0 ou >10:IllegalArgumentException. Testes: reprovados, recuperacao, aprovados, limites, negativas, acima de 10, excecao tipo e mensagem.

## DESAFIO FINAL: Escolha @CsvSource
Dois parametros, entrada-saida, fronteiras, legibilidade.

Casos: -1,true,NO_PRAZO | -1,false,NO_PRAZO | 0,true,NO_PRAZO | 0,false,NO_PRAZO | 1,true,ATRASO_TOLERADO | 1,false,ATRASO_TOLERADO | 5,true,ATRASO_TOLERADO | 5,false,ATRASO_TOLERADO | 10,true,ATRASO_TOLERADO | 10,false,ATRASO_TOLERADO | 11,true,ANALISE_PROFESSOR | 11,false,ATRASADA | 15,true,ANALISE_PROFESSOR | 15,false,ATRASADA | 30,true,ANALISE_PROFESSOR | 30,false,ATRASADA | 60,true,ANALISE_PROFESSOR | 60,false,ATRASADA

## Reflexao
A: @ValueSource - valores simples
B: @CsvSource - combinacoes
C: @MethodSource - objetos complexos
