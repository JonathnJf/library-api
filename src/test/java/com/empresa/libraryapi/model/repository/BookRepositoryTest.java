package com.empresa.libraryapi.model.repository;

import com.empresa.libraryapi.model.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    BookRepository repository;

    @Test
    @DisplayName("Deve lançar  verdadeiro quando existir um livro na base  com isbn informado")
    public void returnTrueWhenIsbnExists() {
        //Cenario
        String isbn = "123";
        Book book = Book.builder().title("As aventuras").author("Fulano").isbn(isbn).build();
        entityManager.persist(book);
        //Execução
        boolean exists = repository.existsByIsbn(isbn);
        //Verificação
        assertThat(exists).isTrue();

    }

    @Test
    @DisplayName("Deve lançar false quando não existir um livro na base  com isbn informado")
    public void returnFalseWhenIsbnDoesentExist() {
        //Cenario
        String isbn = "123";
        //Execução
        boolean exists = repository.existsByIsbn(isbn);
        //Verificação
        assertThat(exists).isFalse();
    }

}
