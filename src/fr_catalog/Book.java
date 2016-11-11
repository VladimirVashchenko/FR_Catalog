package fr_catalog;

import javafx.beans.property.*;

/**
 * Created by Administrator on 26.10.2016.
 */
public class Book {
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

    public Book() {
        this("No Title", "No Author");
    }

    public Book(String title, String author) {
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

    public void setNumber(int number) {
        this.number.set(number);
    }

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }


    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }


    public void setNovelTitle(String title) {
        this.novelTitle.set(title);
    }

    public String getNovelTitle() {
        return novelTitle.get();
    }

    public StringProperty novelTitleProperty() {
        return novelTitle;
    }


    public void setSeries(String series) {
        this.series.set(series);
    }

    public String getSeries() {
        return series.get();
    }

    public StringProperty seriesProperty() {
        return series;
    }


    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }


    public void setPublished(String published) {
        this.published.set(published);
    }

    public String getPublished() {
        return published.get();
    }

    public StringProperty publishedProperty() {
        return published;
    }


    public void setBegins(String begins) {
        this.begins.set(begins);
    }

    public String getBegins() {
        return begins.get();
    }

    public StringProperty beginsProperty() {
        return begins;
    }


    public void setEnds(String ends) {
        this.ends.set(ends);
    }

    public String getEnds() {
        return ends.get();
    }

    public StringProperty endsProperty() {
        return ends;
    }


    public void setYear(String year) {
        this.year.set(year);
    }

    public String getYear() {
        return year.get();
    }

    public StringProperty yearProperty() {
        return year;
    }


    public void setHaveRead(boolean haveRead) {
        this.haveRead.set(haveRead);
    }

    public boolean getHaveRead() {
        return haveRead.get();
    }

    public BooleanProperty haveReadProperty() {
        return haveRead;
    }
}
