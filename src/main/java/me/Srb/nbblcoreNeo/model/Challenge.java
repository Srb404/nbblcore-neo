package me.Srb.nbblcoreNeo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
public class Challenge {

    private String name;
    private int time;
    @Singular
    private List<Team> teams;
}
