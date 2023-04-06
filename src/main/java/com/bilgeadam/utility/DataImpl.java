package com.bilgeadam.utility;

import com.bilgeadam.entity.Comment;
import com.bilgeadam.entity.Movie;
import com.bilgeadam.entity.User;
import com.bilgeadam.service.CommentService;
import com.bilgeadam.service.GenreService;
import com.bilgeadam.service.MovieService;
import com.bilgeadam.service.UserService;
import com.bilgeadam.utility.data.Sample;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataImpl implements ApplicationRunner {

    private final MovieService movieService;
    private final GenreService genreService;
    private final CommentService commentService;
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
            //getAllMovies();
            //createUser();
            //setCommentByMovieId();
    }
    public void getAllMovies(){
        try {
            URL url=new URL("https://api.tvmaze.com/shows");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

            //gson; json formatının javaya karıştırılmış hali
            String value="";
            value= bufferedReader.readLine();
            Sample[] array=new Gson().fromJson(value, Sample[].class);

            Arrays.asList(array).forEach(x-> {
                Movie movie=null;
                if (x.network== null) {
                    movie= Movie.builder()
                            .id(x.id)
                            .url(x.url)
                            .image(x.image.medium)
                            .language(x.language)
                            .premired(LocalDate.parse(x.premiered))
                            .summary(x.summary)
                            .name(x.name)
                            .genreId(genreService.createGenresWithNames(x.genres))
                            .rating(x.rating.average)
                            .build();
                }else{
                    movie= Movie.builder()
                            .id(x.id)
                            .url(x.url)
                            .image(x.image.medium)
                            .language(x.language)
                            .premired(LocalDate.parse(x.premiered))
                            .summary(x.summary)
                            .name(x.name)
                            .genreId(genreService.createGenresWithNames(x.genres))
                            .rating(x.rating.average)
                            .country(x.network.country.name)
                            .build();
                }
                movieService.save(movie);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(){
        User user1=User.builder()
                .name("John")
                .surname("JohnSur")
                .email("john@gmail.com")
                .password("Abc1234**")
                .repassword("Abc1234**")
                .phone("1233333333")
                .movieId(List.of(8,3,17,18,9,85,78,127,1,120))
                .genreId(List.of(1,2,5,6))
                .build();
        List<Comment> comment1=List.of(
          Comment.builder().content("Güzel film").date(LocalDate.now()).userId(1).movieId(18).build(),
          Comment.builder().content("Güzel").date(LocalDate.now()).userId(1).movieId(127).build(),
          Comment.builder().content("Film").date(LocalDate.now()).userId(1).movieId(76).build()
        );
        commentService.saveAll(comment1);
        user1.setCommentsId(comment1.stream().map(x-> x.getId()).toList());
        userService.save(user1);

        ///----user2------------------
        User user2=User.builder()
                .name("Ayşe")
                .surname("AyşeSur")
                .email("ayse@gmail.com")
                .password("Abc1234**")
                .repassword("Abc1234**")
                .phone("44444444")
                .movieId(List.of(5,6,7,8))
                .genreId(List.of(5,3,2))
                .build();
        List<Comment> comment2=List.of(
                Comment.builder().content("Berbat").date(LocalDate.now()).userId(2).movieId(14).build(),
                Comment.builder().content("Kötü").date(LocalDate.now()).userId(2).movieId(24).build(),
                Comment.builder().content("Olabilir").date(LocalDate.now()).userId(2).movieId(114).build()
        );
        commentService.saveAll(comment2);
        user1.setCommentsId(comment2.stream().map(x-> x.getId()).toList());
        userService.save(user2);

        //userService.saveAll(List.of(user1, user2)); şeklinde toplu kayıtta atabilirz.
    }

    public void setCommentByMovieId(){
        commentService.findAll().stream().forEach(x-> {
            Movie movie = movieService.findById(x.getMovieId()).get(); // optional cunku gelen
            movie.getCommentId().add(x.getId());
            movieService.save(movie);
        });

    }

















}
