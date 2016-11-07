import javafx.beans.property.*;

/**
 * Created by Administrator on 26.10.2016.
 */
public class Book {
    private final IntegerProperty number;
    private final StringProperty title;
    private final StringProperty series;
    private final StringProperty author;
    private final SimpleIntegerProperty published;
    private final SimpleIntegerProperty begins;
    private final SimpleIntegerProperty ends;
    private final SimpleIntegerProperty year;
    private final BooleanProperty haveRead;

    public Book() {
        this("No Title", "No Author");
    }

    public Book(String title, String series) {
        this.number = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty(title);
        this.series = new SimpleStringProperty(series);
        this.author = new SimpleStringProperty("");
        this.published = new SimpleIntegerProperty();
        this.begins = new SimpleIntegerProperty();
        this.ends = new SimpleIntegerProperty();
        this.year = new SimpleIntegerProperty();
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


    public void setPublished(int published) {
        this.published.set(published);
    }

    public int getPublished() {
        return published.get();
    }

    public IntegerProperty publishedProperty() {
        return published;
    }


    public void setBegins(int begins) {
        this.begins.set(begins);
    }

    public int getBegins() {
        return begins.get();
    }

    public IntegerProperty beginsProperty() {
        return begins;
    }


    public void setEnds(int ends) {
        this.ends.set(ends);
    }

    public int getEnds() {
        return ends.get();
    }

    public IntegerProperty endsProperty() {
        return ends;
    }


    public void setYear(int year) {
        this.year.set(year);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
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
