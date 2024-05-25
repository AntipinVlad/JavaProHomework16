package org.example.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Command {
    LEAVE("leave"),
    TAKE("take"),
    SHOW("show"),
    SAVE("save"),
    EXIT("exit");

    private String command;
}
