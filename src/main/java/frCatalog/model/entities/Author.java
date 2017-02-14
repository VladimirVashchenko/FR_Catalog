package frCatalog.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Administrator on 02.02.2017.
 */
@Entity
@Table(name = "AUTHOR")
public class Author implements Serializable {
	private Integer authorId;
	private String firstName;
	private String secondName;
	private String surname;
	private Set<NovelAuthor> novelAuthors = new HashSet<NovelAuthor>(0);
	private Set<AnthologyAuthor> anthologyAuthors = new HashSet<AnthologyAuthor>(0);

	public Author() {
	}

	public Author(String surname) {
		this.surname = surname;
	}

	public Author(String firstName, String surname) {
		this.firstName = firstName;
		this.surname = surname;
	}

	public Author(String firstName, String secondName, String surname) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.surname = surname;
	}

	public Author(String firstName, String secondName, String surname, Set<NovelAuthor> novelAuthors) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.surname = surname;
		this.novelAuthors = novelAuthors;
	}

	@Id
	@Column(name = "AUTHOR_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(Integer id) {
		this.authorId = id;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 10)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "SECOND_NAME", nullable = false, length = 10)
	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Column(name = "SURNAME", nullable = false, length = 10)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.author", cascade = CascadeType.ALL)
	public Set<NovelAuthor> getNovelAuthors() {
		return this.novelAuthors;
	}

	public void setNovelAuthors(Set<NovelAuthor> novelAuthors) {
		this.novelAuthors = novelAuthors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.author", cascade = CascadeType.ALL)
	public Set<AnthologyAuthor> getAnthologyAuthors() {
		return this.anthologyAuthors;
	}

	public void setAnthologyAuthors(Set<AnthologyAuthor> anthologyAuthors) {
		this.anthologyAuthors = anthologyAuthors;
	}
}
