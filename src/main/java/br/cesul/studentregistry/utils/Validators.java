package br.cesul.studentregistry.utils;

public class Validators {
    /*
        Para que o teste notBlank passe precisamos
        implementar o minimo (cada uma entrada, checar null e espaços)

     */

    // Construtor privado: Esta classe não deve ser instaciada
    // uso apenas estático
    private Validators(){};

    public static boolean notBlank(String input){
        return input != null && !input.trim().isEmpty();
    }

    public static boolean email(String input){
        /*
            O que o teste espera?

            - Não pode ser null
            - Tem que ter @
            - Tem que ter '.' depois do '@'
            - o '.' não é o ultimo caracter

         */

        if (!notBlank(input)) return false;

        int arroba = input.indexOf("@");
        int ponto = input.lastIndexOf('.');

        return arroba > 0 &&
                ponto > arroba + 1 &&
                ponto < input.length() - 1;
    }

    // inRange -> true se "valor"  pertencer ao intervalo [min,max]
    public static  boolean inRange(double nota, int min, int max){
        return nota <= max && nota >= min;
    }
}
