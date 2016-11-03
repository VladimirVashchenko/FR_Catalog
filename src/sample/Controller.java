package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

public class Controller {
    @FXML
    TableView<Book> table;
    @FXML
    TableColumn<Book, Integer> numberColumn;
    @FXML
    TableColumn<Book, String> seriesColumn;
    @FXML
    TableColumn<Book, String> titleColumn;
    @FXML
    TableColumn<Book, String> authorColumn;
    @FXML
    TableColumn<Book, Integer> publishedColumn;
    @FXML
    TableColumn<Book, Integer> beginsColumn;
    @FXML
    TableColumn<Book, Integer> endsColumn;
    @FXML
    TableColumn<Book, Integer> yearColumn;
    @FXML
    TableColumn<Book, Boolean> haveReadColumn;
    @FXML
    ChoiceBox<String> seriesChoice;
    @FXML
    CheckBox showSeriesCheckBox;
    @FXML
    Button addNewBtn;

    private ObservableList<Book> booksData = FXCollections.observableArrayList();

    public Controller() {
        booksData.add(new Book());
        booksData.add(new Book());
        booksData.add(new Book());
        booksData.add(new Book());
    }

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        seriesColumn.setCellValueFactory(cellData -> cellData.getValue().seriesProperty());
        publishedColumn.setCellValueFactory(cellData -> cellData.getValue().publishedProperty().asObject());
        beginsColumn.setCellValueFactory(cellData -> cellData.getValue().beginsProperty().asObject());
        endsColumn.setCellValueFactory(cellData -> cellData.getValue().endsProperty().asObject());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        haveReadColumn.setCellValueFactory(cellData -> cellData.getValue().haveReadProperty());

        table.setItems(booksData);

        titleColumn.setCellFactory(param -> new TableCell<Book, String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString());
                }
            }
        });

        publishedColumn.setCellFactory(param -> new TableCell<Book, Integer>(){
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("-fx-alignment: baseline-right");
                if (empty || item == 0) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString());
                }
            }
        });
        beginsColumn.setCellFactory(param -> new TableCell<Book, Integer>(){
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("-fx-alignment: baseline-right");
                if (empty || item == 0) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString()+" DR");

                }
            }
        });
        endsColumn.setCellFactory(param -> new TableCell<Book, Integer>(){
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("-fx-alignment: baseline-right");
                if (empty || item == 0) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString()+" DR");
                }
            }
        });
        yearColumn.setCellFactory(param -> new TableCell<Book, Integer>(){
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("-fx-alignment: baseline-right");
                if (empty || item == 0) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString()+" DR");
                }
            }
        });

        haveReadColumn.setCellFactory(param -> new TableCell<Book, Boolean>(){
            CheckBox check = new CheckBox();
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("-fx-alignment: baseline-center");
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(null);
                    setGraphic(check);
                    check.setSelected(item);
                }
            }
        });
    }

    public ObservableList<Book> getBooksData() {
        return booksData;
    }


}
