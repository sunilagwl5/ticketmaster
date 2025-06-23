package com.snorkell.ticketmaster.model;

import lombok.Data;

@Data
public class MovieShows {
    private final String movieName;
    private final Integer showId;
    private final Integer theaterId;
    private final Integer screenId;
    private final String startTime;
    private final String endTime;
}
