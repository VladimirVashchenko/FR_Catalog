package frCatalog.entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Administrator on 03.02.2017.
 */
@Embeddable
public class SeriesNovelId implements Serializable {
	private Series series;
	private Novel novel;

	@ManyToOne
//	@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID", nullable = false)
	public Series getSeries(){
		return series;
	}

	public void setSeries(Series series){
		this.series = series;
	}

	@ManyToOne
//	@JoinColumn(name = "NOVEL_ID", referencedColumnName = "ID", nullable = false)
	public Novel getNovel(){
		return novel;
	}

	public void setNovel(Novel novel){
		this.novel = novel;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SeriesNovelId that = (SeriesNovelId) o;

		if (novel != null ? !novel.equals(that.novel) : that.novel != null) return false;
		if (series != null ? !series.equals(that.series) : that.series != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (novel != null ? novel.hashCode() : 0);
		result = 31 * result + (series != null ? series.hashCode() : 0);
		return result;
	}
}
