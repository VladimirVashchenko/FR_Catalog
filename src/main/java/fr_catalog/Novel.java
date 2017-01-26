package fr_catalog;

import javafx.beans.property.*;

/**
 * Created by Administrator on 26.10.2016.
 */
public class Novel {
	private final IntegerProperty novel_id;
	private final StringProperty novel_title;
	private final StringProperty published;
	private final StringProperty begins_dr;
	private final StringProperty ends_dr;

	private final IntegerProperty author_id;
	private final StringProperty author; // combined name, second name, and surname

	private final IntegerProperty anthology_id;
	private final StringProperty anthology_title;
	private final StringProperty anthology_published;

	private final IntegerProperty series_id;
	private final StringProperty series_title;
	private final IntegerProperty number;

	private final BooleanProperty read;


	private Author authorObject = new Author();

	public Novel() {
		this("No Title", "No Author");
	}

	public Novel(String title, String author) {
		this.novel_id = new SimpleIntegerProperty();
		this.novel_title = new SimpleStringProperty();
		this.published = new SimpleStringProperty();
		this.begins_dr = new SimpleStringProperty();
		this.ends_dr = new SimpleStringProperty();

		this.author_id = new SimpleIntegerProperty();
		this.author = new SimpleStringProperty();

		this.anthology_id = new SimpleIntegerProperty();
		this.anthology_title = new SimpleStringProperty();
		this.anthology_published = new SimpleStringProperty();

		this.series_id = new SimpleIntegerProperty();
		this.series_title = new SimpleStringProperty();
		this.number = new SimpleIntegerProperty();

		this.read = new SimpleBooleanProperty(false);

	}

	public int getNovel_id() {
		return novel_id.get();
	}

	public IntegerProperty novel_idProperty() {
		return novel_id;
	}

	public String getNovel_title() {
		return novel_title.get();
	}

	public StringProperty novel_titleProperty() {
		return novel_title;
	}

	public String getPublished() {
		return published.get();
	}

	public StringProperty publishedProperty() {
		return published;
	}

	public String getBegins_dr() {
		return begins_dr.get();
	}

	public StringProperty begins_drProperty() {
		return begins_dr;
	}

	public String getEnds_dr() {
		return ends_dr.get();
	}

	public StringProperty ends_drProperty() {
		return ends_dr;
	}

	public int getAuthor_id() {
		return author_id.get();
	}

	public IntegerProperty author_idProperty() {
		return author_id;
	}

	public String getAuthor() {
		return author.get();
	}

	public StringProperty authorProperty() {
		return author;
	}

	public int getAnthology_id() {
		return anthology_id.get();
	}

	public IntegerProperty anthology_idProperty() {
		return anthology_id;
	}

	public String getAnthology_title() {
		return anthology_title.get();
	}

	public StringProperty anthology_titleProperty() {
		return anthology_title;
	}

	public String getAnthology_published() {
		return anthology_published.get();
	}

	public StringProperty anthology_publishedProperty() {
		return anthology_published;
	}

	public int getSeries_id() {
		return series_id.get();
	}

	public IntegerProperty series_idProperty() {
		return series_id;
	}

	public String getSeries_title() {
		return series_title.get();
	}

	public StringProperty series_titleProperty() {
		return series_title;
	}

	public int getNumber() {
		return number.get();
	}

	public IntegerProperty numberProperty() {
		return number;
	}

	public boolean getRead() {
		return read.get();
	}

	public BooleanProperty readProperty() {
		return read;
	}

//------- set

	public Novel setNovelId(int novel_id) {
		this.novel_id.set(novel_id);
		return this;
	}

	public Novel setNovelTitle(String novelTitle) {
		this.novel_title.set(novelTitle);
		return this;
	}

	public Novel setNovelPublished(String published) {
		this.published.set(published);
		return this;
	}

	public Novel setBeginsDr(String begins_dr) {
		this.begins_dr.set(begins_dr);
		return this;
	}

	public Novel setEndsDr(String ends_dr) {
		this.ends_dr.set(ends_dr);
		return this;
	}

	public Novel setAuthorId(int author_id) {
		this.author_id.set(author_id);
		return this;
	}

	public Novel setAuthor(String author) {
		this.author.set(author);
		return this;
	}

	public Novel setAnthologyId(int anthology_id) {
		this.anthology_id.set(anthology_id);
		return this;
	}

	public Novel setAnthologyTitle(String anthology_title) {
		this.anthology_title.set(anthology_title);
		return this;
	}

	public Novel setAnthologyPublished(String anthology_published) {
		this.anthology_published.set(anthology_published);
		return this;
	}

	public Novel setSeries(int series_id) {
		this.series_id.set(series_id);
		return this;
	}

	public Novel setSeriesTitle(String series_title) {
		this.series_title.set(series_title);
		return this;
	}

	public Novel setNumber(int number) {
		this.number.set(number);
		return this;
	}

	public Novel setRead(boolean read) {
		this.read.set(read);
		return this;
	}

	public Author getAuthorObject(){
		return this.authorObject;
	}
}
