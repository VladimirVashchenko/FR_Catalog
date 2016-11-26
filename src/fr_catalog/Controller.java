package fr_catalog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {
	@FXML
	TextField edit_number, edit_series, edit_novel, edit_title, edit_author, edit_published, edit_begins, edit_ends, edit_year;
	@FXML
	Button btn_addNew, btn_newBookPanel;
	@FXML
	AnchorPane pane_left, pane_right, pane_main;
	@FXML
	TableView<Novel> table;
	@FXML
	TableColumn<Novel, Integer> column_number;
	@FXML
	TableColumn<Novel, String> column_series, column_novel, column_title, column_author, column_published, column_begins, column_ends, column_year;
	@FXML
	TableColumn<Novel, Boolean> column_haveRead;
	@FXML
	ChoiceBox<String> choice_series;
	@FXML
	CheckBox check_showSeries;

	private ObservableList<Novel> booksData = FXCollections.observableArrayList();
	private Stage primaryStage;
	private Main main;

	public Controller() {
		booksData.add(new Novel());
		booksData.add(new Novel());
		booksData.add(new Novel());
		booksData.add(new Novel());
		Novel novel = new Novel();
		novel.setSeries("Some Series").setNumber(1).setAuthor("Some Author").
				setTitle("Some Title").setPublished("1995").setBegins("600").
				setEnds("610").setYear("600-610").setHaveRead(true);
		booksData.add(novel);
	}

	@FXML
	private void initialize() {
		column_number.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());
		column_novel.setCellValueFactory(cellData -> cellData.getValue().novelTitleProperty());
		column_title.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		column_author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
		column_series.setCellValueFactory(cellData -> cellData.getValue().seriesProperty());
		column_published.setCellValueFactory(cellData -> cellData.getValue().publishedProperty());
		column_begins.setCellValueFactory(cellData -> cellData.getValue().beginsProperty());
		column_ends.setCellValueFactory(cellData -> cellData.getValue().endsProperty());
		column_year.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
		column_haveRead.setCellValueFactory(cellData -> cellData.getValue().haveReadProperty());

		table.setItems(booksData);

        /*column_title.setCellFactory(param -> new TableCell<Novel, String>() {
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
        });*/
		column_published.setCellFactory(param -> makeTableCell());
		column_begins.setCellFactory(param -> makeTableCell("DR"));
		column_ends.setCellFactory(param -> makeTableCell("DR"));
		column_year.setCellFactory(param -> makeTableCell("DR"));
		column_haveRead.setCellFactory(param -> new TableCell<Novel, Boolean>() {
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
	}

	public ObservableList<Novel> getBooksData() {
		return booksData;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void setButtons() {
		btn_newBookPanel.setOnAction(event -> {
			if (!pane_left.isVisible()) {
				pane_left.setVisible(true);
				AnchorPane.setLeftAnchor(pane_right, 180d);
				primaryStage.setWidth(primaryStage.getWidth() + 180);
				primaryStage.setX(primaryStage.getX() - 180);
				AnchorPane.setLeftAnchor(btn_newBookPanel, (AnchorPane.getLeftAnchor(btn_newBookPanel) + 180));
			} else {
				pane_left.setVisible(false);
				AnchorPane.setLeftAnchor(pane_right, 0d);
				primaryStage.setWidth(primaryStage.getWidth() - 180);
				primaryStage.setX(primaryStage.getX() + 180);
				AnchorPane.setLeftAnchor(btn_newBookPanel, (AnchorPane.getLeftAnchor(btn_newBookPanel) - 180));
			}
		});
	}

	public TableCell makeTableCell() {
		return makeTableCell("");
	}

	public TableCell makeTableCell(String suffix) {
		return new TableCell<Novel, String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				setStyle("-fx-alignment: baseline-right");
				if (empty) {
					setText("");
					setGraphic(null);
				} else {
					if (!item.isEmpty()) {
						setText(!suffix.isEmpty() ? item + " " + suffix : item);
					} else {
						setText(item);
					}
				}
			}
		};
	}
}
