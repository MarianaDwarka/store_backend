package com.marianadwarka.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Integer idCategory;

    @Size(min = 3)
    private String nameofCategory;

    @Size(min = 3)
    private String descriptionCategory;

    @NotNull
    private boolean enabledCategory;

    /*@NotEmpty
    @NotBlank
    @Max
    @Min
    @Email
    @Pattern(regexp = "[0-9]+")*/
}
