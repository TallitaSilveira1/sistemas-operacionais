package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.Controller.nBolas;

public class playControl implements Initializable {

    Semaphores quantidade = new Semaphores(nBolas);

    @FXML
    private TextField idChild;

    @FXML
    private HBox chldBox;

    private StringBuilder builder;

    @FXML
    private TextField idTimeBall;

    @FXML
    private TextField idTimeQuiet;

    @FXML
    private Button idBtnOk;

    @FXML
    private CheckBox idCheck;

    @FXML
    private TextArea txtLog;

    private void cleanFields() {
        idChild.setText("");
        idTimeQuiet.setText("");
        idTimeBall.setText("");
        idCheck.setSelected(false);
    }

    public void actionOk(javafx.event.ActionEvent actionEvent) {
        ChildCallBack childCallBack = new ChildCallBack() {
            @Override
            public void method() {
                System.out.println("OK");
            }

            @Override
            public void writeInLog(String log) {
                Platform.runLater(
                        () -> {
                            builder.append(log);
                            txtLog.setText(builder.toString());
                        }
                );
            }
        };
        Child crianca = new Child(Integer.valueOf(idChild.getText()), idCheck.isSelected(),Integer.valueOf(idTimeBall.getText()),Integer.valueOf(idTimeQuiet.getText()), quantidade.getSpaces(), quantidade.getItems(), quantidade.getMutex());
        crianca.setCallBack(childCallBack);
        quantidade.addChild(crianca);
        //add imagem da criança
        //fazer animação com callback p saber quando ela ta quieta
        Circle circle = new Circle(19);
        chldBox.getChildren().add(circle);
        cleanFields();
        crianca.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        builder = new StringBuilder("");
    }
}


    // Pegar valores dos campos de texto e instanciar uma Child OK
    // Adicionar a crianca na lista quantidade.add(Child)
