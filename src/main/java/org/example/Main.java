package org.example;

import org.example.common.Command;
import org.example.common.Messages;
import org.example.service.AnimalMenuService;
import org.example.service.AnimalService;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        AnimalMenuService animalMenuService = new AnimalMenuService(scanner);
        Map<String, Executor> orchestrator = init(animalMenuService);
        animalMenuService.main();
        String command;

        do {
            command = scanner.next();

            orchestrator
                    .getOrDefault(command, () -> System.out.println(Messages.INCORRECT_COMMAND.getMessage()))
                    .execute();
            animalMenuService.taskIsCompleted();
        } while (!command.equals(Command.EXIT.getCommand()));

        scanner.close();
    }

    private static Map<String, Executor> init(AnimalMenuService animalMenuService) {
        AnimalService animalService = new AnimalService(animalMenuService);
        return Map.of(
                Command.LEAVE.getCommand(), animalService.addAnimal(),
                Command.TAKE.getCommand(), animalService.removeAnimal(),
                Command.SHOW.getCommand(), animalService.listAnimal(),
                Command.SAVE.getCommand(), animalService.save(),
                Command.EXIT.getCommand(), animalService.exit()
        );
    }
}