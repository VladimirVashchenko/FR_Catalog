package frCatalog.model;

import frCatalog.model.entities.Anthology;
import frCatalog.model.entities.Author;
import frCatalog.model.entities.Novel;
import frCatalog.model.entities.Series;

/**
 * Created by Administrator on 11.02.2017.
 */
public class CompositeEntity {
	private Author author;
	private Novel novel;
	private Anthology anthology;
	private Series series;

	public Anthology getAnthology() {
		return anthology;
	}

	public void setAnthology(Anthology anthology) {
		this.anthology = anthology;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Novel getNovel() {
		return novel;
	}

	public void setNovel(Novel novel) {
		this.novel = novel;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}
}
