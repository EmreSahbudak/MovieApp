package com.bilgeadam.repository;

import com.bilgeadam.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment,Integer> {

    //filme göre yorumları listeleyen.
    List<Comment> findByMovieId(int id);

    //belirli iki tarih aralığında bir filmin yorumları gösteren metot
    List<Comment> findByMovieIdAndDateBetween(Integer movieId, LocalDate dateStart, LocalDate dateEnd);

}
