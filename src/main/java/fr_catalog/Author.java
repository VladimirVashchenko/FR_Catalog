package fr_catalog;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Administrator on 07.12.2016.
 */
public class Author {
	private final IntegerProperty author_id;
	private final StringProperty first_name;
	private final StringProperty second_name;
	private final StringProperty surname;
	StringBinding fullName;

	public Author(){
		this.author_id = new SimpleIntegerProperty();
		this.first_name = new SimpleStringProperty();
		this.second_name = new SimpleStringProperty();
		this.surname = new SimpleStringProperty();
	}

	public int getAuthor_id() {
		return author_id.get();
	}

	public IntegerProperty author_idProperty() {
		return author_id;
	}


	public String getFirst_name() {
		return first_name.get();
	}

	public StringProperty first_nameProperty() {
		return first_name;
	}

	public String getSecond_name() {
		return second_name.get();
	}

	public StringProperty second_nameProperty() {
		return second_name;
	}

	public String getSurname() {
		return surname.get();
	}

	public StringProperty surnameProperty() {
		return surname;
	}
}
