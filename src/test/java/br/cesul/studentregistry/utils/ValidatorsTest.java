package br.cesul.studentregistry.utils;

/*
    Testar se as validações de UI funcionam corretamente

    1 - O que a viewModel vai precisar decidir?
        - Texto nao pode ser vazio -> notBlank
        - Email tem que ser um formato plauzivel
        - A nota tem que estar entre 0 e 10

    Ao invés de reescrever 10 vezes o mesmo teste com valores diferentes,
    vamos usar o Teste Parametrizado.
     - É um teste que roda varias vezes, uma para cada conjunto (entrada - saida)

     Esse teste recebe 'Parametros' que vem de alguma fonte de dados, costuma-se utilizar a @CsvResource

     Cada linha do CSV armazena valores de teste, separado por virgula e o ultimo deles
     representa se este conjunto de dados deve passar ou não

 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorsTest {

    // notBlank(string) -> só é true se NÃO for null e tem algo além de vazio

    @ParameterizedTest // Teste executado varias vezes (uma por linha do CSV)
    @DisplayName(" notBlank deve detectar strings validas/invalidas")

    @CsvSource(value = {
            // Formato de cada linha: "Entrada, resultado esperado"
            // Casos validos (esperado = true):
            "'abc', true",
            "' x', true",

            // Casos invalidos (esperado = false):
            "'', false",
            "'null', false"
    },
            nullValues = {"null"})
    void notBlank_tests(String input, boolean expected) {

        boolean resultado = Validators.notBlank(input);

        assertEquals(expected, resultado, "NotBlank errado");
    }

    /*
        Validacao de email faremos simples, regras:
        - Tem que ter @
        - Tem que ter '.' depois do '@'
        - o '.' não é o ultimo caracter

     */
    @ParameterizedTest
    @DisplayName("mail simples deve validar formato basico")
    @CsvSource(
            value = {
                    // Validos:
                    "fagas@exemplo.com, true",
                    "tadala@uol.org, true",

                    // Invalidos
                    "sem-arroba.com, false",
                    "arroba@semponto, false",
                    "@vazio.com, false",
                    "null, false"
            },
            nullValues = {"null"}
    )
    void email_testes(String input, boolean expected){
        boolean resultado = Validators.email(input);
        assertEquals(expected, resultado, "Email errado");
    }

    // inRange: true se valor for <= ao max (10) ou >= ao min
    // checar se tal coisa esta entre 0 e 10
    @ParameterizedTest
    @DisplayName("inRange deve checar intervalo de 0..10")
    @CsvSource({
            "0, true", // "limite inferior incluso"
            "10, true", // limite superior incluso
            "5, true", // validar o valor do meio
            "-1, false",
            "10.1, false"
    })
    void inRange_tests(double valor, boolean expected){
        boolean result = Validators.inRange(valor, 0, 10);

        assertEquals(expected, result, "inRange incorreto");
    }
}
