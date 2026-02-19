# NbblcoreNeo
![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Paper](https://img.shields.io/badge/Paper-1.21-blue)
![CommandAPI](https://img.shields.io/badge/CommandAPI-11.1.0-green)
![Triumph GUI](https://img.shields.io/badge/Triumph_GUI-3.1.13-purple)

A plugin for managing team challenges on Minecraft servers.

## Description

NbblcoreNeo is a challenge management system for Minecraft servers. Administrators can create challenges in which teams compete against the clock. Each challenge supports multiple teams with configurable start and finish locations, optional time limits, and player assignment.

All data is automatically saved to JSON files.

## Roadmap

| Feature | Status |
|---------|:------:|
| **Challenge Configuration** | |
| Creating and removing challenges | Done |
| Listing challenges with validation status | Done |
| Renaming a challenge | Done |
| Setting a time limit | Done |
| Challenge management GUI | Done |
| **Team System** | |
| Adding players to teams | Done |
| Removing players from teams | Todo |
| Setting start location | Todo |
| Setting finish location | Todo |
| Removing teams | Todo |
| **Gameplay** | |
| Starting and stopping a challenge | Todo |
| Teleporting teams to start positions | Todo |
| Countdown timer | Todo |
| Challenge completion detection | Todo |
| **Additional** | |
| Permission system | Todo |
| Statistics and leaderboards | Todo |
| Completion rewards | Todo |

## Commands

| Command | Description |
|---------|-------------|
| `/challenge create <name>` | Creates a new challenge |
| `/challenge remove <name>` | Removes a challenge |
| `/challenge list` | Displays the list of challenges with status |
| `/challenge manage <name>` | Opens the challenge management GUI |
| `/challenge manage <name> name <new>` | Renames a challenge |
| `/challenge manage <name> time <seconds>` | Sets the time limit (0 = no limit) |
| `/challenge manage <name> team add <id> <player>` | Adds a player to a team |
