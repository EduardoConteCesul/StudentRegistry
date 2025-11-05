package br.cesul.studentregistry.viewmodel;

/*
    1 - loadAll -> ligar o load durante a operacao e desligar no final.
        E preencher os itens com o que veio no repo

    2 - addNew -> validar os campos (se errados, nao salva)
        (se corretos, salvar no repo e atualizar a lista)

    3 - toggleFlag -> solicitar o banco alterar o active e atualizar a lista

    4 - updateField -> validar o range da nota, se valida atualiza no repo e atualiza na lista

 */

import br.cesul.studentregistry.config.MongoConfig;
import br.cesul.studentregistry.model.Student;
import br.cesul.studentregistry.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentListViewModelTest {

    private StudentRepository repository; // fonte de dados
    private StudentListViewModel vm; // SUT

    @BeforeEach
    void setUp(){
        repository = new StudentRepository(MongoConfig.database(), "students_vm_test");
        repository.deleteAll();
        vm = new StudentListViewModel(repository);
    }

    @Test
    @DisplayName("loadAll preenche itens que desliga loadind no final")
    void loadAll_changesLoadingAndItems(){
        repository.save(new Student("Conte", "conte@gmail.com", 7.5, true));
        repository.save(new Student("Joao", "joao@gmail.com", 7.0, true));

        vm.loadAll();

        assertFalse(vm.loadingProperty().get(), "Loading deve ser falso no final");
        assertEquals(2, vm.getItems().size(), "Items deve conter exatamente dois alunos");
    }
}
