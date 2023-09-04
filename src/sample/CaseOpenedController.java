package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CaseOpenedController {
    @FXML
    private TableView<Case> table;



   public void createNewCommentButton(ActionEvent event) {
       Case selected = Controller.getSelectedCase();
       Scene currentScene = (Scene)((Node)event.getSource()).getScene();

       TextInputDialog td = new TextInputDialog();
       td.setHeaderText("Enter your comment");
       td.showAndWait();
       String newComment =  td.getEditor().getText();
       selected.addComment(newComment, "user X");

       TextArea commentsTextArea = (TextArea)currentScene.lookup("#commentsTextArea");

       // hjälpmetod
       commentsTextArea.setText(selected.getCommentsAsString());


       //this line gets the stage information
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(currentScene);
       window.show();
       System.out.println(selected.getCommentsAsString());


   }


    public void openButton(ActionEvent event) throws IOException {
        Case selected = Controller.getSelectedCase();
        selected.openCase();
        selected.addComment("--Case was re-opened--","user X");
        sceneReload(event);
    }

    public void closeButton(ActionEvent event) throws IOException {
        Case selected = Controller.getSelectedCase();
        selected.closeCase();
        selected.addComment("--Case was closed--","user X");
        sceneReload(event);

    }

    public void sceneReload(ActionEvent event) throws IOException {
        Case selected = Controller.getSelectedCase();


        Parent openCaseParent = FXMLLoader.load(getClass().getResource("CaseOpened.fxml"));
        Scene openCaseScene = new Scene(openCaseParent); // typ innehåler borderpane
        Label casePresenterField = (Label)openCaseScene.lookup("#caseNrPresenter");
        Label statusPresenterField = (Label)openCaseScene.lookup("#statusPresenter");
        casePresenterField.setText(String.valueOf(selected.getNr()));
        statusPresenterField.setText(selected.getStatus());
        TextArea descriptionTextArea = (TextArea)openCaseScene.lookup("#descriptionTextArea");
        descriptionTextArea.setText(selected.getDescription());
        TextArea commentsTextArea = (TextArea)openCaseScene.lookup("#commentsTextArea");

        // hjälpmetod
        commentsTextArea.setText(selected.getCommentsAsString());




        //this line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(openCaseScene);
        window.show();
    }


    public void exitButton(ActionEvent event) throws IOException {

        Parent openCaseParent = FXMLLoader.load(getClass().getResource("sample.fxml"));


        Scene openCaseScene = new Scene(openCaseParent);

        //this line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(openCaseScene);
        window.show();

        //Case selected = table.getSelectionModel().getSelectedItem();
        //System.out.println(selected);
    }
}
