package com.bezkoder.spring.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.bezkoder.spring.graphql.model.Author;
import com.bezkoder.spring.graphql.model.Tutorial;
import com.bezkoder.spring.graphql.repository.AuthorRepository;
import com.bezkoder.spring.graphql.repository.TutorialRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Component
public class Query implements GraphQLQueryResolver {
	private AuthorRepository authorRepository;
	private TutorialRepository tutorialRepository;
	
	GraphQLScalarType longScalar =
      ExtendedScalars.newAliasedScalar("Long")
          .aliasedScalar(ExtendedScalars.GraphQLLong)
          .build();

	@Autowired
	public Query(AuthorRepository authorRepository, TutorialRepository tutorialRepository) {
		this.authorRepository = authorRepository;
		this.tutorialRepository = tutorialRepository;
	}

	public Iterable<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public Iterable<Tutorial> findAllTutorials() {
		return tutorialRepository.findAll();
	}

//	public Page<Author> findAllAuthors(Pageable pageable) {
//		return authorRepository.findAll(pageable);
//	}
//
//	public Page<Tutorial> findAllTutorials(Pageable pageable) {
//		return tutorialRepository.findAll(pageable);
//	}

	public long countAuthors() {
		return authorRepository.count();
	}

	public long countTutorials() {
		return tutorialRepository.count();
	}

}
