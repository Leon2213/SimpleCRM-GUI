package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    static Case selected;
    private static final Database db = new Database();
    //private final ObservableList<Case> casesObservable = FXCollections.observableArrayList(db.getCases());
    //private final FilteredList<Case> filteredCases = new FilteredList<>(casesObservable, predicate -> true);

    List<String> Categories = new ArrayList<>(List.of("Application_error","Application_support", "hardware_error", "order"));
    private final ListView<String> categoriesListView = new ListView<>(FXCollections.observableList(Categories));



    //@FXML
    //private TextArea descriptionTextArea;

   //@FXML
    //private TextArea commentTextArea;

    @FXML
    private MenuItem saveFile;

    @FXML
    private TableView<Case> table;

    @FXML
    private TableColumn<Case, Integer> caseNumber;

    @FXML
    private TableColumn<Case, String> dateCreated;

    @FXML
    private TableColumn<Case, Category> category;

    @FXML
    private TableColumn<Case, String> description;

    @FXML
    private TableColumn<Case, String> status;

    @FXML
    private static ObservableList<Case> casesObservable = FXCollections.observableArrayList(db.getCases());


    public void openButton(ActionEvent event) throws IOException {
        System.out.println(casesObservable);
        selected = table.getSelectionModel().getSelectedItem();
        System.out.println(selected);

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


    public static Case getSelectedCase(){
        return selected;
    }

    /*
    public void readDataFromFile(){
        int n = 0;
        int j = 0;

        try {
            FileReader reader = new FileReader("cases.data");
            BufferedReader in = new BufferedReader(reader);
            in.readLine();
            String line = in.readLine();
            String[] tokensNode = line.split(";");
            while (j < tokensNode.length - 1) {
                j = 2 + n;
                String city = tokensNode[n];
                double x = Double.parseDouble(tokensNode[1 + n]);
                double y = Double.parseDouble(tokensNode[j]);
                n += 3;
                Node node = new Node(x, y, city);
                node.setId(node.getCityName());
                graph.add(node);
                node.getText().setDisable(true);
                pane.getChildren().addAll(node, node.getText());
                node.setOnMouseClicked(new ClickHandler());
            }
        } catch (FileNotFoundException e) {
            System.err.print(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    } */

    public void readDataToFile() {
        try {
            FileWriter writer = new FileWriter("cases.txt");
            PrintWriter pw = new PrintWriter(writer);
            for (Case c : Controller.getCases()) {
                System.out.println(c);
                pw.print(c.getNr() + ";" + c.getStatus() + ";" + c.getCategory() + ";" + c.getDateCreated() + ";" + c.getDescription() + "\n");
                for (Comment com : c.getComments()) {
                    pw.print(com.getDateCreated() + ";" + com.getUser() + ";" + com.getCommentInfo());
                } //TESTETEST
            }
            
            pw.close();
            writer.close();
        }catch(IOException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "IO:fel " + e.getMessage());
            }
    }

    public static ObservableList<Case> getCases(){
        return casesObservable;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        caseNumber.setCellValueFactory(new PropertyValueFactory<>("nr"));
        dateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        table.setItems(casesObservable);

    }
    /*
    public class selectedCaseHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {

        }
    } */



}
