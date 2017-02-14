package frCatalog.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 03.02.2017.
 */
@Entity
@Table(name = "ANTHOLOGY")
public class Anthology implements Serializable{
	private Integer anthologyId;
	private String anthologyTitle;
	private AnthologyAuthor anthologyAuthor;
	private Set<AnthologyNovel> anthologyNovels = new HashSet<AnthologyNovel>(0);

	public Anthology() {
	}

	public Anthology(String anthologyTitle) {
		this.anthologyTitle = anthologyTitle;
	}

	public Anthology(String anthologyTitle, Set<AnthologyNovel> anthologyNovels) {
		this.anthologyTitle = anthologyTitle;
		this.anthologyNovels = anthologyNovels;
	}

	@Id
	@Column(name = "ANTHOLOGY_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAnthologyId() {
		return this.anthologyId;
	}

	public void setAnthologyId(Integer id) {
		this.anthologyId = id;
	}

	@Column(name = "ANTHOLOGY_TITLE", nullable = false, length = 100)
	public String getAnthologyTitle() {
		return this.anthologyTitle;
	}

	public void setAnthologyTitle(String anthologyTitle) {
		this.anthologyTitle = anthologyTitle;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.anthology", cascade = CascadeType.ALL)
	public Set<AnthologyNovel> getAnthologyNovels() {
		return this.anthologyNovels;
	}

	public void setAnthologyNovels(Set<AnthologyNovel> anthologyNovels) {
		this.anthologyNovels = anthologyNovels;
	}


	@OneToOne(fetch = FetchType.LAZY, mappedBy = "pk.anthology", cascade=CascadeType.ALL)
	public AnthologyAuthor getAnthologyAuthor() {
		return anthologyAuthor;
	}

	public void setAnthologyAuthor(AnthologyAuthor anthologyAuthor) {
		this.anthologyAuthor = anthologyAuthor;
	}
}
