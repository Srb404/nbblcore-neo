package me.Srb.nbblcoreNeo.challenge;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.Srb.nbblcoreNeo.model.Challenge;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ChallengeManageGUI {

    public void open(Challenge challenge, Player player) {
        Gui gui = Gui.gui()
                .title(Component.text("§6§lEDYCJA §7» §a" + challenge.getName()))
                .rows(3)
                .create();

        gui.setDefaultClickAction(event -> event.setCancelled(true));
        gui.getFiller().fill(
                ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE)
                        .name(Component.text(" "))
                        .asGuiItem()
        );

        GuiItem nameItem = ItemBuilder
                .from(Material.BOOK)
                .name(Component.text("§e§lNAZWA"))
                .lore(
                        Component.text("§7"),
                        Component.text("§7Aktualna: §f§l" + challenge.getName()),
                        Component.text("§aKliknij, aby zmienić")
                )
                .asGuiItem(event -> {
                    // TODO
                });

        GuiItem timeItem = ItemBuilder
                .from(Material.CLOCK)
                .name(Component.text("§e§lCZAS"))
                .lore(
                        Component.text("§7"),
                        Component.text("§7Aktualny: §f§l" + challenge.getTime()),
                        Component.text("§aKliknij, aby zmienić")
                )
                .asGuiItem(event -> {
                    // TODO
                });

        GuiItem teamsItem = ItemBuilder
                .from(Material.PLAYER_HEAD)
                .name(Component.text("§e§lDRUŻYNY"))
                .lore(
                        Component.text("§7"),
                        Component.text("§7Skład: §f§l" + challenge.getTeams()),
                        Component.text("§aKliknij, aby edytować")
                )
                .asGuiItem(event -> {
                    // TODO
                });

        gui.setItem(2, 3, nameItem);
        gui.setItem(2, 5, timeItem);
        gui.setItem(2, 7, teamsItem);

        gui.open(player);
    }
}
