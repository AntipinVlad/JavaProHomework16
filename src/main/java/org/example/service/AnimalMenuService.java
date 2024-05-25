package org.example.service;

import org.example.common.Command;
import org.example.common.Messages;
import org.example.model.Animal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnimalMenuService {
    private final Scanner scanner;

    public AnimalMenuService(Scanner scanner) {
        this.scanner = scanner;
    }

    private final List<String> commands = Arrays.asList(
            Command.LEAVE.getCommand(),
            Command.TAKE.getCommand(),
            Command.SHOW.getCommand()
    );

    public void main() {
        System.out.println("welcome,\n -> " + String.join("\n -> ", commands));
    }

    public Animal leave() {
        Animal.AnimalBuilder builder = Animal.builder();
        System.out.println(Messages.ENTER_NAME.getMessage());
        builder.name(scanner.next());
        System.out.println(Messages.AGE.getMessage());
        builder.age(scanner.nextInt());
        System.out.println(Messages.TYPE.getMessage());
        builder.type(scanner.next());
        System.out.println(Messages.BREAD.getMessage());
        builder.bread(scanner.next());
        return builder.build();
    }

    public String take() {
        System.out.println(Messages.ANIMAL_NAME_FOR_TAKE.getMessage());
        return scanner.next();
    }

    public void taskIsCompleted() {
        System.out.println(Messages.SUCCESSFULLY_DONE.getMessage());
    }
}
