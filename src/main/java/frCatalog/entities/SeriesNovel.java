package frCatalog.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 03.02.2017.
 */
@Entity
@Table(name = "SERIES_NOVEL")
@AssociationOverrides({
		@AssociationOverride(name = "pk.series", joinColumns = @JoinColumn(name = "SERIES_ID")),
		@AssociationOverride(name = "pk.novel", joinColumns = @JoinColumn(name = "NOVEL_ID"))})
public class SeriesNovel implements Serializable {
	private SeriesNovelId pk = new SeriesNovelId();

	public SeriesNovel() {
	}

	@EmbeddedId
	public SeriesNovelId getPk() {
		return pk;
	}

	public void setPk(SeriesNovelId pk) {
		this.pk = pk;
	}

	@Transient
	public Series getSeries() {
		return getPk().getSeries();
	}

	public void setSeries(Series series) {
		getPk().setSeries(series);
	}

	@Transient
	public Novel getNovel() {
		return getPk().getNovel();
	}

	public void setNovel(Novel novel) {
		getPk().setNovel(novel);
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		SeriesNovel that = (SeriesNovel) o;
		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
