package com.bilgeadam.service;

import com.bilgeadam.entity.Movie;
import com.bilgeadam.repository.IMovieRepository;
import com.bilgeadam.utility.ICrudService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements ICrudService<Movie,Integer> {

    private final IMovieRepository movieRepository;

    public MovieService(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Iterable<Movie> saveAll(Iterable<Movie> entities) {
        return movieRepository.saveAll(entities);
    }

    @Override
    public Movie update(Movie movie) {
        return movieRepository.saveAndFlush(movie);
    }
    @Override
    public Movie delete(Integer integer) {
        return null;
    }
    public void deleteById(Integer integer) {
        movieRepository.deleteById(integer);
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movieList = movieRepository.findAll();
        if (movieList.isEmpty()) {
            throw  new NotFoundException("Liste Boş");
        }
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Integer integer) {
        Optional<Movie> optionalMovie=movieRepository.findById(integer);
        if (optionalMovie.isEmpty()){
            throw new NotFoundException("Liste boş");
        }
        return optionalMovie;
    }
    //rating buyukluk metodu için
    public List<Movie> findByRatingGreaterThan(double rating){
        return movieRepository.findByRatingGreaterThan(rating);
    }
    //tarihten önce için
    public List<Movie> findByPremiredBefore(LocalDate premired){
        return movieRepository.findByPremiredBefore(premired);
    }
    public Object countByIdenticalRating(double rating){
        return  movieRepository.countByIdenticalRating(rating);
    }
    //filmratingi ve adedi.
    public Object[] countByRatingGroupByRating(){
        return  movieRepository.countByRatingGroupByRating();
    }
    // 7 8 9 ratinge sahip olanlar
    public List<Movie> findByRatingIn(){
        List<Double> ratings=List.of(7.0,8.0,9.0);
        return movieRepository.findByRatingIn(ratings);
    }
    //name içeriğine göre
    public List<Movie> findByNameContainsIgnoreCase(String name){
        return movieRepository.findByNameContainsIgnoreCase(name);
    }



























}
