package com.bilgeadam.service;

import com.bilgeadam.entity.Comment;
import com.bilgeadam.entity.Movie;
import com.bilgeadam.repository.ICommentRepository;
import com.bilgeadam.utility.ICrudService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICrudService<Comment,Integer> {

    private final ICommentRepository commentRepository;

    public CommentService(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Iterable<Comment> saveAll(Iterable<Comment> entities) {
        return commentRepository.saveAll(entities);
    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }
    @Override
    public Comment delete(Integer integer) {
        return null;
    }
    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
    @Override
    public Optional<Comment> findById(Integer integer) {
        Optional<Comment> optionalMovie=commentRepository.findById(integer);
        if (optionalMovie.isEmpty()){
            throw new NotFoundException("Liste boş");
        }
        return optionalMovie;
    }
    //filme göre yorumlar
    public List<Comment> findByMovieId(int id){
        return commentRepository.findByMovieId(id);
    }
    //tarih aralıgında birfilme yorumlar
    public List<Comment> findByMovieIdAndDateBetween(Integer movieId, String dateStart, String dateEnd){
        LocalDate date1=LocalDate.parse(dateStart, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate date2=LocalDate.parse(dateEnd, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return commentRepository.findByMovieIdAndDateBetween(movieId, date1, date2);
    }




}

