package com.empresa.libraryapi.model.repository;

import com.empresa.libraryapi.model.entity.Loan;
import com.empresa.libraryapi.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query(value = " SELECT CASE WHEN ( count(l.id) > 0 ) THEN TRUE ELSE FALSE END " +
                     " FROM Loan l WHERE l.book = :book AND (l.returned IS NULL OR l.returned IS FALSE) ")
    boolean existsByBookAndNotReturned( @Param("book") Book book );
    @Query(value = " SELECT l FROM Loan as l JOIN l.book as b WHERE b.isbn = :isbn OR l.customer = :customer")
    Page<Loan> findByBookIsbnOrCustomer(
            @Param("isbn") String isbn,
            @Param("customer")String customer,
            Pageable pageable);

    Page<Loan> findByBook(Book book, Pageable pageable);

    @Query("SELECT l FROM Loan l WHERE l.loanDate <= :threeDaysAgo and (l.returned IS NULL OR l.returned IS FALSE)")
    List<Loan> findByLoanDateLessThanAndNotReturned(@Param("threeDaysAgo") LocalDate threeDayAgo);
}
