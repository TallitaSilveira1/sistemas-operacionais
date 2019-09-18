package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.awt.event.ActionEvent;

import static sample.Controller.nBolas;

public class playControl {

    Semaphores quantidade = new Semaphores(nBolas);

    @FXML
    private TextField idChild;

    @FXML
    private TextField idTimeBall;

    @FXML
    private TextField idTimeQuiet;

    @FXML
    private Button idBtnOk;

    @FXML
    private CheckBox idCheck;


    public void actionOk(javafx.event.ActionEvent actionEvent) {
        Child crianca = new Child(Integer.valueOf(idChild.getText()), Boolean.valueOf(idCheck.getText()),Integer.valueOf(idTimeBall.getText()),Integer.valueOf(idTimeQuiet.getText()));
        quantidade.addChild(crianca);
        crianca.start();
    }
}


    // Pegar valores dos campos de texto e instanciar uma Child OK
    // Adicionar a crianca na lista quantidade.add(Child)
