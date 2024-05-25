package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.Getter;
import org.example.Executor;
import org.example.common.Messages;
import org.example.model.Animal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnimalService {
    private static final String FILE_PATH = "src/main/resources/vault.json";
    private final ObjectMapper mapper = new JsonMapper();
    private final AnimalMenuService menuService;
    private final File vault = new File(FILE_PATH);
    @Getter
    private final List<Animal> animals;

    public AnimalService(AnimalMenuService animalMenuService) {
        this.menuService = animalMenuService;
        this.animals = loadAnimals();
    }

    public String getFilePath() {
        return FILE_PATH;
    }

    private List<Animal> loadAnimals() {
        CollectionType animalCollectionType = mapper.getTypeFactory()
                .constructCollectionType(List.class, Animal.class);

        try {
            if (!vault.exists()) {
                vault.createNewFile();
                return new ArrayList<>();
            }
            if (vault.length() == 0) return new ArrayList<>();

            return mapper.readValue(vault, animalCollectionType);

        } catch (IOException e) {
            System.err.println(Messages.CANNOT_LOAD_ANIMAL.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        return new ArrayList<>();
    }

    public Executor addAnimal() {
        return () -> this.animals.add(menuService.leave());
    }


    public Executor removeAnimal() {
        return () -> {
            String name = menuService.take();
            animals.removeIf(animal -> Objects
                    .equals(name, animal.getName()));
        };
    }

    public Executor listAnimal() {
        return () -> this.animals.forEach(System.out::println);
    }

    public Executor save() {
        return () -> {
            try {
                mapper.writeValue(vault, animals);
            } catch (IOException e) {
                System.err.println(Messages.CANNOT_SAVE_ANIMAL.getMessage());
                e.printStackTrace();
            }
        };
    }

    public Executor exit() {
        return save();
    }
}
