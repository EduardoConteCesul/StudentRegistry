package br.cesul.studentregistry.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*  Aqui fica todos os testes do student model

    O passo a passo é o seguinte:

    1 - Pense primeiro o Contrato (o que a entidade deve garantir)

        - Ao 'nascer', o student tem um id gerado, strings vazias, nota 0.0, ativo = true

    2 - Escrever o primeiro test (RED)

        - Expressar esse contrato no teste
        "defaultConstructor_initializedFields"

    3 - Fazer o minimo para (opcional) passar (green)

        - Implementar o construtor padrão com getter e setter inicializando os atributos

    4 - Refatorar (Sem mudar comportamento)

        - Melhorar o código, deixando-o mais profundo, porem, mantendo os testes passando
*/
public class StudentTest {

    @Test
    @DisplayName("Construtor padrão inicia id, strings vazias, nota 0.0 e ativo = true")
    void defaultConstructor_initializesFields() {
        // Arrange
        // Act - Ação ÚNICA e clara: Criar o Student pelo construtor padrão
        Student s = new Student();

        // Assert
        assertNotNull(s.getId(), "ID deve ser gerado, nunca null");
        assertEquals("", s.getName(), "Nome deve iniciar com string vazia");
        assertEquals("", s.getEmail(), "Email deve iniciar com string vazia");
        assertEquals(0.0, s.getGrade(), "Nota deve inicar como 0.0");
        assertTrue(s.isActive(), "Aluno vem como ativo");
    }

    @Test
    @DisplayName("Construtor completo preenche todos os campos (exceto)")
    void fullConstructor_setsFields() {
        String name = "Conte";
        String email = "conte@email.com";
        double grade = 8.5;
        boolean active = false;

        Student s = new Student(name, email, grade, active);
        assertNotNull(s.getId(), "ID deve ser gerado no construtor");
        assertEquals(name, s.getName(), "Nome deve refletir o 'Name'");
        assertEquals(email, s.getEmail(), "Email deve refletir o 'email'");
        assertEquals(grade, 0.0001, s.getGrade(), "Grade deve refletir");
        assertEquals(active, s.isActive(), "Active deve refletir o 'active'");
    }
}
