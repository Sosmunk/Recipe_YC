package com.triangular.recipe_yc.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RecipeInfo {
    UUID id;

    String name;

    String picture;

    String text;

}
