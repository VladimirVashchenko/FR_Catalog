package frCatalog.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.FetchType;
import java.io.Serializable;

/**
 * Created by Administrator on 02.02.2017.
 */
@Entity
@Table(name = "READ_NOVELS")
public class ReadNovels implements Serializable {
	private Integer novelId;
//	private Boolean readYesNo/* = false*/;
	private Novel novel;

	public ReadNovels(){
	}

	public ReadNovels(Novel novel, Boolean readYesNo){
		this.novel = novel;
//		this.readYesNo = readYesNo;
	}

	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "foreign",
			parameters = @Parameter(name = "property", value = "novel"))
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

	/*@Column(name = "READ_YES_NO", columnDefinition = "boolean default false", nullable = false)
	public Boolean getReadYesNo() {
		return readYesNo;
	}

	public void setReadYesNo(Boolean readYesNo) {
		this.readYesNo = readYesNo;
	}*/

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ReadNovels that = (ReadNovels) o;

		if (novelId != null ? !novelId.equals(that.novelId) : that.novelId != null) return false;
//		if (readYesNo != null ? !readYesNo.equals(that.readYesNo) : that.readYesNo != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = novelId != null ? novelId.hashCode() : 0;
		result = 31 * result /*+ (readYesNo != null ? readYesNo.hashCode() : 0)*/;
		return result;
	}
}
