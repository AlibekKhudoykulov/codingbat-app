package ecma.ai.codingbatapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskDto {
    @NotNull(message = "Name must not be empty")
    private String name;

    @NotNull(message = "Description must not be empty")
    private String description;

    @NotNull(message = "CategoryId must not be empty")
    private Integer categoryId;

    @NotNull(message = "UserId must not be empty")
    private Integer userId;
}
