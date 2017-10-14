package frCatalog.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 03.02.2017.
 */
@Entity
@Table(name = "ANTHOLOGY_NOVEL")
@AssociationOverrides({
		@AssociationOverride(name = "pk.anthology", joinColumns = @JoinColumn(name = "ANTHOLOGY_ID")),
		@AssociationOverride(name = "pk.novel", joinColumns = @JoinColumn(name = "NOVEL_ID"))})
public class AnthologyNovel implements Serializable {
	private AnthologyNovelId pk = new AnthologyNovelId();

	public AnthologyNovel() {
	}

	@EmbeddedId
	public AnthologyNovelId getPk() {
		return pk;
	}

	public void setPk(AnthologyNovelId pk) {
		this.pk = pk;
	}

	@Transient
	public Anthology getAnthology() {
		return getPk().getAnthology();
	}

	public void setAnthology(Anthology anthology) {
		getPk().setAnthology(anthology);
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

		AnthologyNovel that = (AnthologyNovel) o;
		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
