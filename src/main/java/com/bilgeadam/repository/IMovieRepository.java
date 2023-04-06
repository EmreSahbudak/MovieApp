package com.bilgeadam.repository;

import com.bilgeadam.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IMovieRepository extends JpaRepository<Movie,Integer> {

    //ratingden büyükleri
    List<Movie> findByRatingGreaterThan(double rating);

    //tarhiten itibaren olanlar
    List<Movie> findByPremiredBefore(LocalDate premired);

    //belli bir rating adedi ve ratingi
    @Query(value ="select rating, count(*) as FilmAdedi from movie where rating=?1 group by rating",
            nativeQuery = true)
    Object countByIdenticalRating(double rating);

    //rating=filmadı=adedi şeklinde listeleme
    @Query(value ="select rating, count(rating) from movie group by rating",
            nativeQuery = true)
    Object[] countByRatingGroupByRating();

    //7 8 9 ratingler
    List<Movie> findByRatingIn(List<Double> ratings);

    //contains name
    List<Movie> findByNameContainsIgnoreCase(String name);





    //jpaBuddy ile deneme metodu idi
//    @Query("""
//            select m from Movie m
//            where upper(m.country) like upper(concat('%', ?1)) and upper(m.name) like upper(concat('%', ?2))""")
//    List<Movie> denemeQuery(String country, String name);


}
