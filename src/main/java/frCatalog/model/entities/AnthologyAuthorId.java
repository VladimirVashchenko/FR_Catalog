package frCatalog.model.entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Administrator on 04.02.2017.
 */
@Embeddable
public class AnthologyAuthorId implements Serializable {
	private Author author;
	private Anthology anthology;

	@ManyToOne
//	@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID", nullable = false)
	public Author getAuthor(){
		return author;
	}

	public void setAuthor(Author author){
		this.author = author;
	}

	@ManyToOne
//	@JoinColumn(name = "ANTHOLOGY_ID", referencedColumnName = "ID", nullable = false)
	public Anthology getAnthology(){
		return anthology;
	}

	public void setAnthology(Anthology anthology){
		this.anthology = anthology;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AnthologyAuthorId that = (AnthologyAuthorId) o;

		if (anthology != null ? !anthology.equals(that.anthology) : that.anthology != null) return false;
		if (author != null ? !author.equals(that.author) : that.author != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (anthology != null ? anthology.hashCode() : 0);
		result = 31 * result + (author != null ? author.hashCode() : 0);
		return result;
	}
}
