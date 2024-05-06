package com.bezkoder.spring.graphql.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "age")
	private Integer age;

	public Author() {
	}

	public Author(Long id) {
		this.id = id;
	}

	public Author(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	private Set<Tutorial> tutorials = new HashSet<>();

	public Set<Tutorial> getTutorials() {
		return tutorials;
	}

	public void setTutorials(Set<Tutorial> tutorials) {
		this.tutorials = tutorials;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
