package frCatalog.model.entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Administrator on 04.02.2017.
 */
@Embeddable
public class SeriesAuthorId implements Serializable{
	private Author author;
	private Series series;

	@ManyToOne
//	@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID", nullable = false)
	public Author getAuthor(){
		return author;
	}

	public void setAuthor(Author author){
		this.author = author;
	}

	@ManyToOne
//	@JoinColumn(name = "SERIES_ID", referencedColumnName = "ID", nullable = false)
	public Series getSeries(){
		return series;
	}

	public void setSeries(Series series){
		this.series = series;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SeriesAuthorId that = (SeriesAuthorId) o;

		if (series != null ? !series.equals(that.series) : that.series != null) return false;
		if (author != null ? !author.equals(that.author) : that.author != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (series != null ? series.hashCode() : 0);
		result = 31 * result + (author != null ? author.hashCode() : 0);
		return result;
	}
}
