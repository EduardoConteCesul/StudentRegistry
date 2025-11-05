package br.cesul.studentregistry.viewmodel;

import br.cesul.studentregistry.model.Student;
import br.cesul.studentregistry.repository.StudentRepository;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class StudentListViewModel {

    private final ObservableList<Student> items = FXCollections.observableArrayList();
    // A view vai bindar o 'progress.visibleProperty().bind(vm.loadingProperty());
    private final BooleanProperty loading = new SimpleBooleanProperty(false);

    private final StudentRepository repository;

    public StudentListViewModel(StudentRepository repository) {
        this.repository = repository;
    }

    public ObservableList<Student> getItems() {
        return items;
    }

    public boolean isLoading() {
        return loading.get();
    }

    public BooleanProperty loadingProperty() {
        return loading;
    }

    public void loadAll() {
        loading.set(true);
        try {
            List<Student> result = repository.findAll();
            items.setAll(result);
        } catch (Throwable ex) {

        } finally {
            loading.set(false);
        }

    }
}
