package org.example.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Messages {
    ENTER_NAME("Please enter the name: "),
    AGE("age: "),
    TYPE("type: "),
    BREAD("bread: "),
    ANIMAL_NAME_FOR_TAKE("type animal name to take it from the shelter: "),
    SUCCESSFULLY_DONE("Successfully done!"),
    CANNOT_LOAD_ANIMAL("Cannot load animal data"),
    CANNOT_SAVE_ANIMAL("cannot save animals"),
    INCORRECT_COMMAND("Incorrect command");

    private final String message;
}
