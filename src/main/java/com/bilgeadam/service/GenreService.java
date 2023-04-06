package com.bilgeadam.service;

import com.bilgeadam.entity.Genre;
import com.bilgeadam.repository.IGenreRepository;
import com.bilgeadam.utility.ICrudService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService implements ICrudService<Genre,Integer> {

    private final IGenreRepository genreRepository;

    public GenreService(IGenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Iterable<Genre> saveAll(Iterable<Genre> entities) {
        return genreRepository.saveAll(entities);
    }

    @Override
    public Genre update(Genre genre) {
        return genreRepository.saveAndFlush(genre);
    }

    @Override
    public Genre delete(Integer integer) {
        return null;
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(Integer integer) {
        return genreRepository.findById(integer);
    }

    //dataimpl için gelen veri string. bizimki list ınteger
    //onun dönüşümünü yapıcaz.
    public List<Integer> createGenresWithNames(List<String> genres) {
        List<Integer> genreList=new ArrayList<>();
        for (String name: genres) {
            Optional<Genre> genre=genreRepository.findOptionalByName(name);
            if (genre.isPresent()) {
                genreList.add(genre.get().getId());
            }else {
                Genre myGenre=Genre.builder()
                        .name(name)
                        .build();
                save(myGenre);
                genreList.add(myGenre.getId());
            }
        }
        return genreList;
    }
















}
