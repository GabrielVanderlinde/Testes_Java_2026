# Reflexão Final - Aula 05 - Reforço

## Perguntas e Respostas

### Situação A: testar 20 números diferentes no mesmo método
**Fonte:** `@ValueSource`  
**Por que:** Mais simples para valores de um único tipo. Código limpo e direto.

### Situação B: testar combinações de idade + acompanhado + resultadoEsperado
**Fonte:** `@CsvSource`  
**Por que:** Perfeito para múltiplos parâmetros relacionados. Formato tabular facilita leitura.

### Situação C: testar objetos ou combinações mais complexas
**Fonte:** `@MethodSource`  
**Por que:** Suporta objetos complexos e lógica programática. Maior flexibilidade.

## Resumo

| Situação | Fonte | Uso |
|----------|-------|-----|
| Valores simples | `@ValueSource` | Um parâmetro, valores primitivos |
| Combinações | `@CsvSource` | Múltiplos parâmetros relacionados |
| Complexos | `@MethodSource` | Objetos e lógica programática |
