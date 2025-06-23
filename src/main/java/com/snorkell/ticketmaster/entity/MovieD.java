package com.snorkell.ticketmaster.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movie")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "MovieD.getMovieShows",
        query = "SELECT m.name as movieName, s.id as showId, s.theater_id, s.screen_id, s.start_time, s.end_time FROM movie m JOIN show s ON m.id = s.movie_id WHERE m.id = :movieId",
        resultSetMapping = "MovieShowsMapping"
    )
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = "MovieShowsMapping",
        classes = @ConstructorResult(
            targetClass = com.snorkell.ticketmaster.model.MovieShows.class,
            columns = {
                @ColumnResult(name = "movieName", type = String.class),
                @ColumnResult(name = "showId", type = Integer.class),
                @ColumnResult(name = "theater_id", type = Integer.class),
                @ColumnResult(name = "screen_id", type = Integer.class),
                @ColumnResult(name = "start_time", type = String.class),
                @ColumnResult(name = "end_time", type = String.class)
            }
        )
    )
})
public class MovieD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;
    @NonNull
    private String description;

}
