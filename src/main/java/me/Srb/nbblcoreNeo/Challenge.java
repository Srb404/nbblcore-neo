package me.Srb.nbblcoreNeo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Challenge {
    private String challengeName;
    private int time;
    private ArrayList<Team> teams;

    @Override
    public String toString() {
        return "Challenge{" +
                "name='" + challengeName + '\'' +
                ", time=" + time +
                ", teams=" + teams +
                '}';
    }
}
