package frCatalog.model.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.FetchType;
import java.io.Serializable;

/**
 * Created by Administrator on 02.02.2017.
 */
@Entity
@Table(name = "SINGLE_NOVELS")
public class SingleNovels implements Serializable{
	private Integer novelId;
//	private Boolean aBook/* = false*/;
	private Novel novel;

	public SingleNovels(){
	}

	public SingleNovels(Novel novel/*, Boolean aBook*/){
		this.novel = novel;
//		this.aBook = aBook;
	}

	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "foreign",
			parameters = @Parameter(name = "property", value = "novel"))
	@Column(name = "NOVEL_ID", nullable = false, unique = true)
	public Integer getNovelId() {
		return this.novelId;
	}

	public void setNovelId(Integer novelId) {
		this.novelId = novelId;
	}

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@PrimaryKeyJoinColumn
	public Novel getNovel() {
		return this.novel;
	}

	public void setNovel(Novel novel) {
		this.novel = novel;
	}

//	@Column(name = "A_BOOK", columnDefinition = "boolean default false", nullable = false)
//	public Boolean getaBook() {
//		return aBook;
//	}
//
//	public void setaBook(Boolean aBook) {
//		this.aBook = aBook;
//	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SingleNovels that = (SingleNovels) o;

		if (novelId != null ? !novelId.equals(that.novelId) : that.novelId != null) return false;
//		if (aBook != null ? !aBook.equals(that.aBook) : that.aBook != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = novelId != null ? novelId.hashCode() : 0;
		result = 31 * result /*+ (aBook != null ? aBook.hashCode() : 0)*/;
		return result;
	}
}
