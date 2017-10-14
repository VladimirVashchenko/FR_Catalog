package frCatalog.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 02.02.2017.
 */
@Entity
@Table(name = "NOVEL", uniqueConstraints = {
		@UniqueConstraint(columnNames = "NOVEL_TITLE"),
		@UniqueConstraint(columnNames = "PUBLISHED_AD") })
public class Novel implements Serializable {
	private Integer novelId;
	private String novelTitle;
	private String publishedAd;
	private String beginsDr;
	private String endsDr;
	private SingleNovels singleNovels;
	private ReadNovels readNovels;
	private Set<NovelAuthor> novelAuthors = new HashSet<NovelAuthor>(0);
	private Set<SeriesNovel> seriesNovels = new HashSet<SeriesNovel>(0);
	private Set<AnthologyNovel> anthologyNovels = new HashSet<AnthologyNovel>(0);

	public Novel() {
	}

	public Novel(String novelTitle) {
		this.novelTitle = novelTitle;
	}

	public Novel(String novelTitle, String publishedAd) {
		this.novelTitle = novelTitle;
		this.publishedAd = publishedAd;
	}

	public Novel(String novelTitle, String publishedAd, String beginsDr, String endsDr) {
		this.novelTitle = novelTitle;
		this.publishedAd = publishedAd;
		this.beginsDr = beginsDr;
		this.endsDr = endsDr;
	}

	public Novel(String novelTitle, String publishedAd, String beginsDr, String endsDr, Set<NovelAuthor> novelAuthors) {
		this.novelTitle = novelTitle;
		this.publishedAd = publishedAd;
		this.beginsDr = beginsDr;
		this.endsDr = endsDr;
		this.novelAuthors = novelAuthors;
	}

	@Id
	@Column(name = "NOVEL_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getNovelId() {
		return novelId;
	}

	public void setNovelId(Integer id) {
		this.novelId = id;
	}

	// self-explanatory
	@Column(name = "NOVEL_TITLE", unique = true, nullable = false, length = 100)
	public String getNovelTitle() {
		return novelTitle;
	}

	public void setNovelTitle(String novelTitle) {
		this.novelTitle = novelTitle;
	}

	// when the novel was published
	@Column(name = "PUBLISHED_AD", length = 30)
	public String getPublishedAd() {
		return publishedAd;
	}

	public void setPublishedAd(String publishedAd) {
		this.publishedAd = publishedAd;
	}

	// when the story starts
	@Column(name = "BEGINS_DR", length = 30)
	public String getBeginsDr() {
		return beginsDr;
	}

	public void setBeginsDr(String beginsDr) {
		this.beginsDr = beginsDr;
	}

	// when the story ends
	@Column(name = "ENDS_DR", length = 30)
	public String getEndsDr() {
		return endsDr;
	}

	public void setEndsDr(String endsDr) {
		this.endsDr = endsDr;
	}

	// is this a single novel or part of series/anthology
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "novel", cascade = CascadeType.ALL)
	public SingleNovels getSingleNovels(){
		return this.singleNovels;
	}

	public void setSingleNovels(SingleNovels singleNovels){
		this.singleNovels = singleNovels;
	}

	// have the user read the novel?
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "novel", cascade = CascadeType.ALL)
	public ReadNovels getReadNovels(){
		return this.readNovels;
	}

	public void setReadNovels(ReadNovels readNovels){
		this.readNovels = readNovels;
	}

	// one novel written by many authors
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.novel", cascade=CascadeType.ALL)
	public Set<NovelAuthor> getNovelAuthors() {
		return this.novelAuthors;
	}

	public void setNovelAuthors(Set<NovelAuthor> novelAuthors) {
		this.novelAuthors = novelAuthors;
	}

	//	one novel in many series
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.novel", cascade=CascadeType.ALL)
	public Set<SeriesNovel> getSeriesNovels() {
		return this.seriesNovels;
	}

	public void setSeriesNovels(Set<SeriesNovel> seriesNovels) {
		this.seriesNovels = seriesNovels;
	}


	//	one novel in many anthologies
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.novel", cascade=CascadeType.ALL)
	public Set<AnthologyNovel> getAnthologyNovels() {
		return this.anthologyNovels;
	}

	public void setAnthologyNovels(Set<AnthologyNovel> anthologyNovels) {
		this.anthologyNovels = anthologyNovels;
	}
}
