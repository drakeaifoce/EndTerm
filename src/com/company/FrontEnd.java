package com.company;

import com.company.admin.Admin;
import com.company.controllers.PlayerController;
import com.company.controllers.QuestionController;
import com.company.controllers.TeamController;
import com.company.repositories.interfaces.IPlayerRepository;
import com.company.repositories.interfaces.IQuestionRepository;
import com.company.repositories.interfaces.ITeamRepository;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Darkhan and Kuanyshbek
 */

public class FrontEnd {
    private final Scanner scanner;
    private final PlayerController playerController;
    private final Admin admin;
    private final Quiz quiz;
    private final TeamController teamController;

    public FrontEnd(IQuestionRepository questionRepository, IPlayerRepository playerRepository, ITeamRepository teamRepository) {
        playerController = new PlayerController(playerRepository);
        scanner = new Scanner(System.in);
        admin = new Admin(questionRepository);
        quiz = new Quiz(questionRepository, playerRepository, teamRepository);
        teamController = new TeamController(teamRepository);
    }

    // start

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("Welcome to the quiz application from Darkhan and Kuanyshbek");
            System.out.println("Select option:");
            System.out.println("1. Solo game");
            System.out.println("2. Get scoreboard of solo game");
            System.out.println("3. Team game");
            System.out.println("4. Get scoreboard of team game");
            System.out.println("0. Exit");

            try {
                System.out.print("Enter option (0-4): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    soloMenu();
                } else if (option == 2) {
                    getScoreboard();
                } else if (option == 3) {
                    teamMenu();
                } else if (option == 4) {
                    teamScoreboard();
                } else if (option == 2565) {
                    adminMenu();
                } else if (option == 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
        }
    }
    /**
     * This code intended to the enter admin access
     */
    public void adminMenu() {
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (admin.checkPassword(password) == true) {
            admin.adminControl();
        } else {
            System.out.println("Access denied");
        }
    }
    /**
     * This code intended to enter to the solo game
     */
    public void soloMenu() {
        System.out.print("Enter your name: ");
        String username = scanner.next();

        String registration = playerController.registerPlayer(username);
        int id = playerController.getId();

        System.out.print("Starting game");
        for (int i = 0; i < 3; i++) {
            System.out.print(" .");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();

        quiz.quizStart(id);
    }
    /**
     * This code intended to the play team quiz
     */
    public void teamMenu() {
        System.out.print("Enter the number of players (minimum 4) : ");
        int amount = scanner.nextInt();

        System.out.println("Enter the names of the players: ");
        String x = scanner.nextLine();
        String names_string = scanner.nextLine();
        String[] names = names_string.split(" ");
        String[] teams = new String[4];
        int[] ids = new int[4];
        int num = 1;

        for (int i = 0; i < 4; i++) {
            System.out.println();
            System.out.print("Team number " + num + ", with ");
            for (int j = i; j < names.length; j += 4) {
                if (names[j] == null) {
                    break;
                }
                System.out.print(names[j] + ", ");
                num++;
            }

            teams[i] = scanner.next();
            teamController.registerTeam(teams[i]);
            ids[i] = teamController.getId();
        }

        System.out.print("Starting game");
        for (int i = 0; i < 3; i++) {
            System.out.print(" .");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println();

        quiz.teamQuiz(ids);
    }

    public void getScoreboard() {
        playerController.getScoreboard();
    }

    public void teamScoreboard() {
        teamController.getScoreboard();
    }
}
