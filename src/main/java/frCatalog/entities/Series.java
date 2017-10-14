package frCatalog.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 03.02.2017.
 */

@Entity
@Table(name = "SERIES")
public class Series implements Serializable {
	private Integer seriesId;
	private String seriesTitle;
	private Set<SeriesNovel> seriesNovels = new HashSet<SeriesNovel>(0);

	public Series() {
	}

	public Series(String seriesTitle) {
		this.seriesTitle = seriesTitle;
	}

	public Series(String seriesTitle, Set<SeriesNovel> seriesNovels) {
		this.seriesTitle = seriesTitle;
		this.seriesNovels = seriesNovels;
	}

	@Id
	@Column(name = "SERIES_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getSeriesId() {
		return this.seriesId;
	}

	public void setSeriesId(Integer id) {
		this.seriesId = id;
	}

	@Column(name = "SERIES_TITLE", nullable = false, length = 100)
	public String getSeriesTitle() {
		return this.seriesTitle;
	}

	public void setSeriesTitle(String seriesTitle) {
		this.seriesTitle = seriesTitle;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.series", cascade = CascadeType.ALL)
	public Set<SeriesNovel> getSeriesNovels() {
		return this.seriesNovels;
	}

	public void setSeriesNovels(Set<SeriesNovel> seriesNovels) {
		this.seriesNovels = seriesNovels;
	}
}
