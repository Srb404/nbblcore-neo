package me.Srb.nbblcoreNeo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
public class Challenge {

    @Singular
    private List<Team> teams;
    private String challengeName;
    private int time;
}
