package frCatalog.model.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 03.02.2017.
 */
@Entity
@Table(name = "AUTHOR_ANTHOLOGY")
@AssociationOverrides({
		@AssociationOverride(name = "pk.anthology", joinColumns = @JoinColumn(name = "ANTHOLOGY_ID")),
		@AssociationOverride(name = "pk.author", joinColumns = @JoinColumn(name = "AUTHOR_ID"))})
public class AnthologyAuthor implements Serializable {
	private AnthologyAuthorId pk = new AnthologyAuthorId();

	public AnthologyAuthor() {
	}

	@EmbeddedId
	public AnthologyAuthorId getPk() {
		return pk;
	}

	public void setPk(AnthologyAuthorId pk) {
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
	public Anthology getAnthology() {
		return getPk().getAnthology();
	}

	public void setAnthology(Anthology novel) {
		getPk().setAnthology(novel);
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		AnthologyAuthor that = (AnthologyAuthor) o;
		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
