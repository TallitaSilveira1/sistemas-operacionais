package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Controller.nBolas;

public class playControl implements Initializable {

    Semaphores quantidade;

    @FXML
    private TextField idChild;

    @FXML
    private HBox chldBox;

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
    private StringBuilder builder;

    @FXML
    private ImageView spaces;
    private String[] images = new String[11];
    private int counter;

    private int nBolas = Controller.nBolas;
    private Semaphores semaphores = new Semaphores(nBolas);

    private Path pathPlaying;
    private Path pathQuiet;
    private Path pathNoBall;
    private Path pathSpacesFull;

    public playControl() {
        quantidade = new Semaphores(nBolas);
    }

    private void cleanFields() {
        idChild.setText("");
        idTimeQuiet.setText("");
        idTimeBall.setText("");
        idCheck.setSelected(false);
    }

    //instancia uma nova crianca
    public void actionOk(javafx.event.ActionEvent actionEvent) {
        ChildCallBack childCallBack = new ChildCallBack() {

            //metodo callback
            @Override
            public void method() {
                System.out.println("OK");
            }

            //mostrar mensagem no log
            @Override
            public void writeInLog(String log) {
                Platform.runLater(
                        () -> {
                            builder.append(log);
                            txtLog.setText(builder.toString());
                        }
                );
            }

            @Override
            public void noBall(Child child) {

            }

            @Override
            public void ballGet() {

            }

            @Override
            public void spacesFull(Child child) {

            }

            @Override
            public void ballBack() {

            }

            @Override
            public void playing(Child child) {

            }

            @Override
            public void quiet(Child child) {

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
        builder = new StringBuilder();
    }
}


    // Pegar valores dos campos de texto e instanciar uma Child OK
    // Adicionar a crianca na lista quantidade.add(Child)
