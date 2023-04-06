package com.bilgeadam.controller;

import com.bilgeadam.entity.Movie;
import com.bilgeadam.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/save")
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.save(movie));
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<Movie>> findAll(){
        return ResponseEntity.ok(movieService.findAll());
    }
    //getmapping için 3 anatasyon vardır
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Optional<Movie>>findById(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.findById(id));
    }
    @GetMapping("/find-by-rating-greater-than/{rating}")
    public ResponseEntity<List<Movie>> findByRatingGreaterThan(@PathVariable double rating){
        return ResponseEntity.ok(movieService.findByRatingGreaterThan(rating));
    }
    @GetMapping("/find-by-premired-before/{date}")
    public ResponseEntity<List<Movie>> findByPremiredBefore(@PathVariable String date){
        return  ResponseEntity.ok(movieService.findByPremiredBefore
                (LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }
    @GetMapping("/count-identical-rating/{rating}")
    public ResponseEntity<Object> countByIdenticalRating(double rating){
        return ResponseEntity.ok(movieService.countByIdenticalRating(rating));
    }
    //rating=filmadedi
    @GetMapping("/count-rating-group-by-rating")
    public ResponseEntity<Object[]> countByRatingGroupByRating(){
        return ResponseEntity.ok(movieService.countByRatingGroupByRating());
    }
    //  7 8 9 ratinge sahipler
    @GetMapping("/count-rating-in")
    public ResponseEntity<List<Movie>> findByRatingIn(){
        return ResponseEntity.ok(movieService.findByRatingIn());
    }
    @GetMapping("/find-by-contains-name/{name}")
    public ResponseEntity<List<Movie>> findByNameContainsIgnoreCase(@PathVariable String name){
        return ResponseEntity.ok(movieService.findByNameContainsIgnoreCase(name));
    }




}
