package tk.Cloud1008.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FILE")
public class File {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private Long id;

	@Column(name="NAME")
    private String name;
	
	@Column(name="SIZE")
    private Long size;
	
	@Column(name="PATH")
    private String path;
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getSize() {
		return size;
	}

	public String getPath() {
		return path;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
