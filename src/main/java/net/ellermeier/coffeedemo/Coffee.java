package net.ellermeier.coffeedemo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor(staticName = "of")
public class Coffee {
    @NonNull
    private final String id;
    @NonNull
    private String name;

    public static Coffee of(@NonNull final String name) {
        return Coffee.of(UUID.randomUUID().toString(), name);
    }
}
