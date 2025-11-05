package br.cesul.studentregistry.repository;

/*
    1 - O que o repositorio precisa oferecer para a viewmodel funcionar?
        - save()
        - deleteAll()
        - findAll()
        - updateField()
        - toggleField()

    2 - Quais sao os comportamentos minimos que deve ser aprovados no teste?
        - ex: chamei save() e findAll() >>>> deve aumentar a lista
        - chamei toggleFlag, deve alterar o booleano no doc

*/

import br.cesul.studentregistry.config.MongoConfig;
import br.cesul.studentregistry.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentRepositoryTest {

    // SUT - subject under test: O repositorio concreto o qual faremos os testes.

    private StudentRepository repository;


    // Garante que o teste vai iniciar sempre limpo
    @BeforeEach
    void setUp(){
        // conecta-se a colecao de teste
        repository = new StudentRepository(MongoConfig.database(), "student_test");

        // Zera a coleção antes de cada caso
        repository.deleteAll();
    }

    @Test
    @DisplayName("save + findAll: a lista deve crescer")
    void saveAndFindAll_growsList(){
        // Arrange
        Student student = new Student("Eduardo", "ana@x.com", 7.5, true);

        // Act
        repository.save(student);
        List<Student> all = repository.findAll();

        // Assert
        assertEquals(1, all.size(), "Apos salvar um aluno, findAll deve retornar 1");
        assertEquals(student.getName(), all.get(0).getName(), "O nome deve ser Edauardo");
    }

    @Test
    @DisplayName("toggleFlag deve alterar campo 'active'")
    void toggleFlag_changesActive(){
        // Arrange
        Student student = new Student("Conte", "conte@gmail.com", 6.0, true);
        repository.save(student);

        // Sanitize Check: Garante que esta 'active' ANTES de alterar (evitando falso positivo)
        // Act
        repository.toggleFlag(student.getId());
        // Assert
        assertFalse(repository.findAll().get(0).isActive(), "Apos toggle, deve ser falso.");
    }

    @Test
    @DisplayName("UpdateField deve atualizar nota")
    void updateField_updateGrade(){
        Student student = new Student("Dimiso", "dimiso@gmail.com", 4.0, true);
        repository.save(student);

        // Sanitize Check: Garante que esta 'active' ANTES de alterar (evitando falso positivo)
        // Act
        repository.updateField(student.getId(), 9.0);

        // Assert
        assertEquals(9.0, repository.findAll().get(0).getGrade(), 0.0001,
                "A nota deve ter sido atualizada com 9.0"
        );
    }
}
