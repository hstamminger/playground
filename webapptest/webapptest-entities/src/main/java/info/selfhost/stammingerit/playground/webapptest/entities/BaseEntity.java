package info.selfhost.stammingerit.playground.webapptest.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private String id;// = IdGenerator.createId();

	@Version
	@Column(name = "OPTLOCK_VERSION")
	private Long version;

	public String getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(String id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BaseEntity that = (BaseEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}