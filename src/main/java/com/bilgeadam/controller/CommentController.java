package com.bilgeadam.controller;

import com.bilgeadam.entity.Comment;
import com.bilgeadam.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
    public ResponseEntity<Comment> save(@RequestBody Comment comment){
        return  ResponseEntity.ok(commentService.save(comment));
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<Comment>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }
    @GetMapping("/find-by-movie-comment/{id}")
    public ResponseEntity<List<Comment>> findByMovieId(@PathVariable int id){
        return ResponseEntity.ok(commentService.findByMovieId(id));
    }
    //birfilme belli tarih aralıgında
    @GetMapping("/find-by-movie-and-date-comment/{movieId}")
    public ResponseEntity<List<Comment>> findByMovieIdAndDateBetween(@PathVariable Integer movieId,
                                                                      String dateStart,String dateEnd){
        return ResponseEntity.ok(commentService.findByMovieIdAndDateBetween(movieId, dateStart, dateEnd));
    }















}
