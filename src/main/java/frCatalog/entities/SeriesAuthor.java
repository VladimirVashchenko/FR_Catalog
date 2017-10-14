package frCatalog.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 04.02.2017.
 */
@Entity
@Table (name = "AUTHOR_SERIES")
@AssociationOverrides({
		@AssociationOverride(name = "pk.series", joinColumns = @JoinColumn(name = "SERIES_ID")),
		@AssociationOverride(name = "pk.author", joinColumns = @JoinColumn(name = "AUTHOR_ID"))})
public class SeriesAuthor implements Serializable {
	private SeriesAuthorId pk = new SeriesAuthorId();

	public SeriesAuthor() {
	}

	@EmbeddedId
	public SeriesAuthorId getPk() {
		return pk;
	}

	public void setPk(SeriesAuthorId pk) {
		this.pk = pk;
	}

	@Transient
	public Author getAuthor() {
		return getPk().getAuthor();
	}

	public void setAuthor(Author author) {
		getPk().setAuthor(author);
	}

	@Transient
	public Series getSeries() {
		return getPk().getSeries();
	}

	public void setSeries(Series novel) {
		getPk().setSeries(novel);
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		SeriesAuthor that = (SeriesAuthor) o;
		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
