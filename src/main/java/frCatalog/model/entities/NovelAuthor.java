package frCatalog.model.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 02.02.2017.
 */
@Entity
@Table(name = "AUTHOR_NOVEL")
@AssociationOverrides({
		@AssociationOverride(name = "pk.author", joinColumns = @JoinColumn(name = "AUTHOR_ID")),
		@AssociationOverride(name = "pk.novel", joinColumns = @JoinColumn(name = "NOVEL_ID"))})
public class NovelAuthor implements Serializable {
	private NovelAuthorId pk = new NovelAuthorId();

	public NovelAuthor() {
	}

	@EmbeddedId
	public NovelAuthorId getPk() {
		return pk;
	}

	public void setPk(NovelAuthorId pk) {
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

		NovelAuthor that = (NovelAuthor) o;
		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
