package fr_catalog;

import javafx.beans.property.*;

/**
 * Created by Administrator on 26.10.2016.
 */
public class Novel {
	private final IntegerProperty novelId;
	private final IntegerProperty bookId;
	private final IntegerProperty seriesId;
	private final IntegerProperty number;
	private final StringProperty title;
	private final StringProperty novelTitle;
	private final StringProperty series;
	private final StringProperty author;
	private final SimpleStringProperty published;
	private final SimpleStringProperty begins;
	private final SimpleStringProperty ends;
	private final SimpleStringProperty year;
	private final BooleanProperty haveRead;

	public Novel() {
		this("No Title", "No Author");
	}

	public Novel(String title, String author) {
		this.novelId = new SimpleIntegerProperty();
		this.bookId = new SimpleIntegerProperty();
		this.seriesId = new SimpleIntegerProperty();
		this.number = new SimpleIntegerProperty();
		this.title = new SimpleStringProperty(title);
		this.novelTitle = new SimpleStringProperty();
		this.series = new SimpleStringProperty();
		this.author = new SimpleStringProperty(author);
		this.published = new SimpleStringProperty("");
		this.begins = new SimpleStringProperty("");
		this.ends = new SimpleStringProperty("");
		this.year = new SimpleStringProperty("");
		this.haveRead = new SimpleBooleanProperty(false);
	}


	public Novel setNovelId(int novelId) {
		this.novelId.set(novelId);
		return this;
	}

	public int getNovelId() {
		return novelId.get();
	}

	public IntegerProperty novelIdProperty() {
		return novelId;
	}

	public Novel setBookId(int bookId) {
		this.bookId.set(bookId);
		return this;
	}

	public int getBookId() {
		return bookId.get();
	}

	public IntegerProperty bookIdProperty() {
		return bookId;
	}

	public Novel setSeriesId(int seriesId) {
		this.seriesId.set(seriesId);
		return this;
	}

	public int getSeriesId() {
		return seriesId.get();
	}

	public IntegerProperty seriesIdProperty() {
		return seriesId;
	}

	public Novel setNumber(int number) {
		this.number.set(number);
		return this;
	}

	public int getNumber() {
		return number.get();
	}

	public IntegerProperty numberProperty() {
		return number;
	}


	public Novel setTitle(String title) {
		this.title.set(title);
		return this;
	}

	public String getTitle() {
		return title.get();
	}

	public StringProperty titleProperty() {
		return title;
	}


	public Novel setNovelTitle(String title) {
		this.novelTitle.set(title);
		return this;
	}

	public String getNovelTitle() {
		return novelTitle.get();
	}

	public StringProperty novelTitleProperty() {
		return novelTitle;
	}


	public Novel setSeries(String series) {
		this.series.set(series);
		return this;
	}

	public String getSeries() {
		return series.get();
	}

	public StringProperty seriesProperty() {
		return series;
	}


	public Novel setAuthor(String author) {
		this.author.set(author);
		return this;
	}

	public String getAuthor() {
		return author.get();
	}

	public StringProperty authorProperty() {
		return author;
	}


	public Novel setPublished(String published) {
		this.published.set(published);
		return this;
	}

	public String getPublished() {
		return published.get();
	}

	public StringProperty publishedProperty() {
		return published;
	}


	public Novel setBegins(String begins) {
		this.begins.set(begins);
		return this;
	}

	public String getBegins() {
		return begins.get();
	}

	public StringProperty beginsProperty() {
		return begins;
	}


	public Novel setEnds(String ends) {
		this.ends.set(ends);
		return this;
	}

	public String getEnds() {
		return ends.get();
	}

	public StringProperty endsProperty() {
		return ends;
	}


	public Novel setYear(String year) {
		this.year.set(year);
		return this;
	}

	public String getYear() {
		return year.get();
	}

	public StringProperty yearProperty() {
		return year;
	}


	public Novel setHaveRead(boolean haveRead) {
		this.haveRead.set(haveRead);
		return this;
	}

	public boolean getHaveRead() {
		return haveRead.get();
	}

	public BooleanProperty haveReadProperty() {
		return haveRead;
	}
}
