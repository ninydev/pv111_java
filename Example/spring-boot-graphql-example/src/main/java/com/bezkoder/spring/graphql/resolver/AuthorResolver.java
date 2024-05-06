package com.bezkoder.spring.graphql.resolver;

import com.bezkoder.spring.graphql.model.Author;
import com.bezkoder.spring.graphql.model.Tutorial;
import com.bezkoder.spring.graphql.repository.AuthorRepository;
import com.bezkoder.spring.graphql.repository.TutorialRepository;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class AuthorResolver implements GraphQLResolver<Tutorial> {
	@Autowired
	private TutorialRepository tutorialRepository;

	public AuthorResolver(TutorialRepository tutorialRepository) {
		this.tutorialRepository = tutorialRepository;
	}

	public List<Tutorial> getTutorials(Author author) {
		return tutorialRepository.findByAuthorId(author.getId());
	}
}
