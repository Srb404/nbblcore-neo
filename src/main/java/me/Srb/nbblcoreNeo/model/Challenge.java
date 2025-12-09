package me.Srb.nbblcoreNeo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public record Challenge(String challengeName, int time, ArrayList<Team> teams) {
}
