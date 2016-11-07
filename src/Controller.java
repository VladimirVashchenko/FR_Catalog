import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class Controller {
    @FXML
    TextField edit_number, edit_series, edit_title, edit_author, edit_published, edit_begins, edit_ends, edit_year;
    @FXML
    Button btn_addNew, btn_newBookPanel;
    @FXML
    AnchorPane panel_newBook;
    @FXML
    TableView<Book> table;
    @FXML
    TableColumn<Book, Integer> column_number;
    @FXML
    TableColumn<Book, String> column_series;
    @FXML
    TableColumn<Book, String> column_title;
    @FXML
    TableColumn<Book, String> column_author;
    @FXML
    TableColumn<Book, Integer> column_published;
    @FXML
    TableColumn<Book, Integer> column_begins;
    @FXML
    TableColumn<Book, Integer> column_ends;
    @FXML
    TableColumn<Book, Integer> column_year;
    @FXML
    TableColumn<Book, Boolean> column_haveRead;
    @FXML
    ChoiceBox<String> choice_series;
    @FXML
    CheckBox check_showSeries;

    private ObservableList<Book> booksData = FXCollections.observableArrayList();

    public Controller() {
        booksData.add(new Book());
        booksData.add(new Book());
        booksData.add(new Book());
        booksData.add(new Book());
    }

    @FXML
    private void initialize() {
        column_number.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());
        column_title.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        column_author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        column_series.setCellValueFactory(cellData -> cellData.getValue().seriesProperty());
        column_published.setCellValueFactory(cellData -> cellData.getValue().publishedProperty().asObject());
        column_begins.setCellValueFactory(cellData -> cellData.getValue().beginsProperty().asObject());
        column_ends.setCellValueFactory(cellData -> cellData.getValue().endsProperty().asObject());
        column_year.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        column_haveRead.setCellValueFactory(cellData -> cellData.getValue().haveReadProperty());

        table.setItems(booksData);

        column_title.setCellFactory(param -> new TableCell<Book, String>(){
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

        column_published.setCellFactory(param -> new TableCell<Book, Integer>(){
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
        column_begins.setCellFactory(param -> new TableCell<Book, Integer>(){
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
        column_ends.setCellFactory(param -> new TableCell<Book, Integer>(){
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
        column_year.setCellFactory(param -> new TableCell<Book, Integer>(){
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

        column_haveRead.setCellFactory(param -> new TableCell<Book, Boolean>(){
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
