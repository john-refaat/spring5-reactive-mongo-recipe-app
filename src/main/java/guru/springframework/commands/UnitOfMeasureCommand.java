package guru.springframework.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by jt on 6/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasureCommand {
    private String id;
    private String description;

    @Builder
    public UnitOfMeasureCommand(String id, String description) {
        this.id = id;
        this.description = description;
    }
}
