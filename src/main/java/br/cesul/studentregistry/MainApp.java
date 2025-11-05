package br.cesul.studentregistry;
//import br.cesul.studentregistry.repository.StudentRepository; // Repositório concreto em memória.
import br.cesul.studentregistry.config.MongoConfig;
//import br.cesul.studentregistry.viewmodel.StudentListViewModel; // ViewModel principal da tela.
import javafx.application.Application; // Classe base para apps JavaFX.
import javafx.fxml.FXMLLoader; // Carregador de arquivos FXML.
import javafx.scene.Parent; // Nó raiz da cena.
import javafx.scene.Scene; // Contêiner visual que segura a árvore de nós.
import javafx.stage.Stage; // Janela principal.

public class MainApp extends Application {

    @Override // Método chamado pelo JavaFX para iniciar a UI.
    public void start(Stage primaryStage) throws Exception {
//        var repository = new StudentRepository(
//                MongoConfig.database(),
//                "students"
//        );

//        var viewModel = new StudentListViewModel(repository);

        var loader = new FXMLLoader(getClass().getResource("/br/cesul/studentregistry/StudentView.fxml"));
        Parent root = loader.load();

        var controller = loader.getController();
//        controller.getClass().getMethod("setViewModel", StudentListViewModel.class).invoke(controller, viewModel);

        primaryStage.setTitle("Student Registry — MVVM + JavaFX + TDD");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

//        viewModel.loadAll();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
