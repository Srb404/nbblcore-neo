package me.Srb.nbblcoreNeo.ui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.model.Team;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ChallengeManageGUI {

    private static final int ROW_ITEMS = 2;
    private static final int SLOT_NAME = 3;
    private static final int SLOT_TIME = 5;
    private static final int SLOT_TEAMS = 7;

    private Player player;
    private Challenge challenge;
    private String commandBase;

    public void open(Challenge challenge, Player player) {
        this.player = player;
        this.challenge = challenge;
        this.commandBase = "/challenge manage " + challenge.getName() + " ";

        Gui gui = Gui.gui()
                .title(Component.text("EDYCJA ", NamedTextColor.YELLOW, TextDecoration.BOLD)
                        .append(Component.text("» ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                        .append(Component.text(challenge.getName(), NamedTextColor.GOLD).decoration(TextDecoration.BOLD, false)))
                .rows(3)
                .create();

        gui.setDefaultClickAction(event -> event.setCancelled(true));
        gui.getFiller().fill(
                ItemBuilder.from(Material.GRAY_STAINED_GLASS_PANE)
                        .name(Component.text(" "))
                        .asGuiItem()
        );

        gui.setItem(ROW_ITEMS, SLOT_NAME, createNameItem());
        gui.setItem(ROW_ITEMS, SLOT_TIME, createTimeItem());
        gui.setItem(ROW_ITEMS, SLOT_TEAMS, createTeamsItem());

        gui.open(player);
    }

    private GuiItem createNameItem() {
        return createItem(Material.BOOK, "Nazwa", "Aktualna", challenge.getName(), "name ");
    }

    private GuiItem createTimeItem() {
        String timeDisplay = challenge.getTime() == 0 ? "bez limitu" : challenge.getTime() + "s";
        return createItem(Material.CLOCK, "Czas", "Aktualny", timeDisplay, "time ");
    }

    private GuiItem createTeamsItem() {
        List<Team> teams = getTeamsSafe();

        return ItemBuilder.from(Material.PLAYER_HEAD)
                .name(Component.text("Drużyny", NamedTextColor.YELLOW, TextDecoration.BOLD))
                .lore(buildTeamsLore(teams))
                .asGuiItem(event -> chatCommandPrompt("team add "));
    }

    private GuiItem createItem(Material material, String title, String label, String value, String subcommand) {
        return ItemBuilder.from(material)
                .name(Component.text(title, NamedTextColor.YELLOW, TextDecoration.BOLD))
                .lore(buildLore(label, value))
                .asGuiItem(event -> chatCommandPrompt(subcommand));
    }

    private List<Component> buildLore(String label, String value) {
        return List.of(
                Component.empty(),
                Component.text(label + ": ", NamedTextColor.DARK_GRAY)
                        .append(Component.text(value, NamedTextColor.WHITE)),
                Component.empty(),
                Component.text("Kliknij, aby zmienić", NamedTextColor.GREEN, TextDecoration.ITALIC)
        );
    }

    private List<Component> buildTeamsLore(List<Team> teams) {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.empty());
        lore.add(Component.text("Ilość drużyn: ", NamedTextColor.DARK_GRAY)
                .append(Component.text(String.valueOf(teams.size()), NamedTextColor.WHITE)));
        lore.add(Component.text("Skład: ", NamedTextColor.DARK_GRAY)
                .append(Component.text(formatAllTeams(teams), NamedTextColor.WHITE)));
        lore.add(Component.empty());
        lore.add(Component.text("Kliknij, aby zarządzać", NamedTextColor.GREEN, TextDecoration.ITALIC));
        return lore;
    }

    private void chatCommandPrompt(String subcommand) {
        player.closeInventory();

        Component message = Component.text("Komenda przygotowana. Kliknij: ", NamedTextColor.GRAY)
                .append(Component.text("[WKLEJ]", NamedTextColor.GREEN, TextDecoration.BOLD)
                        .clickEvent(ClickEvent.suggestCommand(commandBase + subcommand)));

        player.sendMessage(message);
    }

    private List<Team> getTeamsSafe() {
        List<Team> teams = challenge.getTeams();
        return teams != null ? teams : Collections.emptyList();
    }

    private String formatAllTeams(List<Team> teams) {
        if (teams.isEmpty()) {
            return "brak";
        }

        StringBuilder result = new StringBuilder();
        int teamIndex = 1;

        for (Team team : teams) {
            if (team == null) {
                teamIndex++;
                continue;
            }

            result.append("#").append(teamIndex).append(": ");
            result.append(formatTeamPlayers(team));
            result.append(" ");
            teamIndex++;
        }

        return result.toString().trim();
    }

    private String formatTeamPlayers(Team team) {
        List<UUID> players = team.getPlayers();
        if (players == null || players.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (UUID playerId : players) {
            sb.append(getPlayerName(playerId)).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");

        return sb.toString();
    }

    private String getPlayerName(UUID playerId) {
        Player onlinePlayer = Bukkit.getPlayer(playerId);
        if (onlinePlayer != null) {
            return onlinePlayer.getName();
        }
        return playerId.toString().substring(0, 8);
    }
}
