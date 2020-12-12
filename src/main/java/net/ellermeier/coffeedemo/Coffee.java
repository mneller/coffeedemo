package net.ellermeier.coffeedemo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Coffee {
    @Id
    @NonNull
    private String id;
    @NonNull
    private String name;

    public static Coffee of(@NonNull final String name) {
        return Coffee.of(UUID.randomUUID().toString(), name);
    }
}
