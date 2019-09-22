package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static int nBolas = 0;

    Semaphores quantidade = new Semaphores(nBolas);

    @FXML
    private Button btnDown;

    @FXML
    private Button btnUp;

    @FXML
    private Button btnOk;

    @FXML
    private TextField lblId;

    @FXML
    private void clickDown(ActionEvent event) {
        if (nBolas>=1) {
            nBolas -= 1;
        }
        lblId.setText(String.valueOf(nBolas));
    }

    @FXML
    private void clickOk(ActionEvent event) {
        if (nBolas!=0) {
            System.out.printf("Iniciando com %d bolas", nBolas);
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("play.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 922, 568);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void clickUp(ActionEvent event) {
        nBolas += 1;
        lblId.setText(String.valueOf(nBolas));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
