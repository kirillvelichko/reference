package my.project.configuration.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Property {
    SIGNATURE("signature");

    private final String name;
}
