package ecma.ai.codingbatapp.payload;

import ecma.ai.codingbatapp.entity.ProgrammingLanguage;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CategoryDto {
    @NotNull(message = "name must not be empty")
    private String name;

    @NotNull(message = "Description must not be empty")
    private String description;

    private int starNumber;

    private List<Integer> languageList;
}
