package fr_catalog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {
    @FXML
    TextField edit_number, edit_series, edit_title, edit_author, edit_published, edit_begins, edit_ends, edit_year;
    @FXML
    Button btn_addNew, btn_newBookPanel;
    @FXML
    AnchorPane pane_left, pane_right, pane_main;
    @FXML
    TableView<Book> table;
    @FXML
    TableColumn<Book, Integer> column_number, column_published, column_begins, column_ends, column_year;
    @FXML
    TableColumn<Book, String> column_series, column_title, column_author;
    @FXML
    TableColumn<Book, Boolean> column_haveRead;
    @FXML
    ChoiceBox<String> choice_series;
    @FXML
    CheckBox check_showSeries;

    private ObservableList<Book> booksData = FXCollections.observableArrayList();
    private Stage primaryStage;
    private Main main;

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

        column_title.setCellFactory(param -> new TableCell<Book, String>() {
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
        column_published.setCellFactory(param -> new TableCell<Book, Integer>() {
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
        column_begins.setCellFactory(param -> new TableCell<Book, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("-fx-alignment: baseline-right");
                if (empty || item == 0) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString() + " DR");

                }
            }
        });
        column_ends.setCellFactory(param -> new TableCell<Book, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("-fx-alignment: baseline-right");
                if (empty || item == 0) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString() + " DR");
                }
            }
        });
        column_year.setCellFactory(param -> new TableCell<Book, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("-fx-alignment: baseline-right");
                if (empty || item == 0) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString() + " DR");
                }
            }
        });
        column_haveRead.setCellFactory(param -> new TableCell<Book, Boolean>() {
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

        pane_left.setVisible(false);

        System.out.println("initialize:");
        System.out.println("main - " + this.main);
        System.out.println("primaryStage - " + this.primaryStage + "\n");
    }

    public ObservableList<Book> getBooksData() {
        return booksData;
    }

    public void setMain(Main main) {
        this.main = main;
        System.out.println("setMain:");
        System.out.println("main - " + this.main);
        System.out.println("primaryStage - " + this.primaryStage + "\n");
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        System.out.println("setprimaryStage:");
        System.out.println("main - " + main);
        System.out.println("primaryStage - " + this.primaryStage + "\n");
    }

    public void setButtons() {
        btn_newBookPanel.setOnAction(event -> {
            if (!pane_left.isVisible()) {
                pane_left.setVisible(true);
                System.out.println(pane_left.getWidth());
                AnchorPane.setLeftAnchor(pane_right, 180d);
                primaryStage.setWidth(primaryStage.getWidth() + 180);
                System.out.println(pane_left);
                System.out.println(primaryStage);
                System.out.println(main);
            } else {
                pane_left.setVisible(false);
                System.out.println(pane_left.getWidth());
                AnchorPane.setLeftAnchor(pane_right, 0d);
                primaryStage.setWidth(primaryStage.getWidth() - 180);
                System.out.println(pane_left);
                System.out.println(primaryStage);
                System.out.println(main);
            }
        });
    }

}
