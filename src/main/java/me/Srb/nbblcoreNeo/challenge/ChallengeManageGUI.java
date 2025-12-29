package me.Srb.nbblcoreNeo.challenge;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.model.Team;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ChallengeManageGUI {

    public void open(Challenge challenge, Player player) {
        Gui gui = Gui.gui()
                .title(Component.text("§e§lEDYCJA §r§7» §r§6" + challenge.getName())
                        .decorate(TextDecoration.BOLD))
                .rows(3)
                .create();

        // Cancel default click action and fill empty slots
        gui.setDefaultClickAction(event -> event.setCancelled(true));
        gui.getFiller().fill(
                ItemBuilder.from(Material.GRAY_STAINED_GLASS_PANE)
                        .name(Component.text(" "))
                        .asGuiItem()
        );

        gui.setItem(2, 3, createNameItem(challenge, player));
        gui.setItem(2, 5, createTimeItem(challenge, player));
        gui.setItem(2, 7, createTeamsItem(challenge, player));

        gui.open(player);
    }

    private GuiItem createNameItem(Challenge challenge, Player player) {
        return ItemBuilder.from(Material.BOOK)
                .name(Component.text("§e§lNazwa"))
                .lore(
                        Component.empty(),
                        Component.text("§8Aktualna: §f" + challenge.getName()),
                        Component.empty(),
                        Component.text("§a§oKliknij, aby zmienić")
                )
                .asGuiItem(event -> {
                    chatCommandPrompt(player, "/challenge manage " + challenge.getName() + " name ");
                });
    }

    private GuiItem createTimeItem(Challenge challenge, Player player) {
        return ItemBuilder.from(Material.CLOCK)
                .name(Component.text("§e§lCzas"))
                .lore(
                        Component.empty(),
                        Component.text("§8Aktualny: §f" + challenge.getTime()),
                        Component.empty(),
                        Component.text("§a§oKliknij, aby zmienić")
                )
                .asGuiItem(event -> {
                    chatCommandPrompt(player, "/challenge manage " + challenge.getName() + " time ");
                });
    }

    private GuiItem createTeamsItem(Challenge challenge, Player player) {
        return ItemBuilder.from(Material.PLAYER_HEAD)
                .name(Component.text("§e§lDrużyny"))
                .lore(
                        Component.empty(),
                        Component.text("§8Ilość drużyn: §f" + challenge.getTeams().size()),
                        Component.text("§8Skład: §f" + listPlayers(challenge)),
                        Component.empty(),
                        Component.text("§a§oKliknij, aby zmienić")
                )
                .asGuiItem(event -> {
                    // TODO
                });
    }

    private void chatCommandPrompt(Player player, String command) {
        player.closeInventory();

        Component info = Component.text("§7Komenda przygotowana. Kliknij: ");

        Component button = Component.text("§a§l⬛")
                .clickEvent(ClickEvent.suggestCommand(command));

        player.sendMessage(info.append(button));
    }

    private String listPlayers(Challenge challenge) {
        for (Team team : challenge.getTeams()) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (UUID playerId : team.getPlayers()) {
                sb.append(playerId.toString(), 0, 8).append(", ");
            }
            if (!team.getPlayers().isEmpty()) {
                sb.setLength(sb.length() - 2); // Remove last comma and space
            }
            sb.append("] ");
            return sb.toString().trim();
        }
        return "[]";
    }
}
