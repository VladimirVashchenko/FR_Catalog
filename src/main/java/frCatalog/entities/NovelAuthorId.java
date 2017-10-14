package frCatalog.entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Administrator on 02.02.2017.
 */
@Embeddable
public class NovelAuthorId implements Serializable {
	private Novel novel;
	private Author author;

	@ManyToOne
//	@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID", nullable = false)
	public Author getAuthor(){
		return author;
	}

	public void setAuthor(Author author){
		this.author = author;
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

		NovelAuthorId that = (NovelAuthorId) o;

		if (novel != null ? !novel.equals(that.novel) : that.novel != null) return false;
		if (author != null ? !author.equals(that.author) : that.author != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (novel != null ? novel.hashCode() : 0);
		result = 31 * result + (author != null ? author.hashCode() : 0);
		return result;
	}
}
