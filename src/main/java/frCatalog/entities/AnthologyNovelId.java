package frCatalog.entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Administrator on 03.02.2017.
 */
@Embeddable
public class AnthologyNovelId implements Serializable {
	private Anthology anthology;
	private Novel novel;

	@ManyToOne
//	@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID", nullable = false)
	public Anthology getAnthology(){
		return anthology;
	}

	public void setAnthology(Anthology anthology){
		this.anthology = anthology;
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

		AnthologyNovelId that = (AnthologyNovelId) o;

		if (novel != null ? !novel.equals(that.novel) : that.novel != null) return false;
		if (anthology != null ? !anthology.equals(that.anthology) : that.anthology != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (novel != null ? novel.hashCode() : 0);
		result = 31 * result + (anthology != null ? anthology.hashCode() : 0);
		return result;
	}
}
