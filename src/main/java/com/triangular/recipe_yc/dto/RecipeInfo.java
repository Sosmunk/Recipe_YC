package com.triangular.recipe_yc.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RecipeInfo {

    String name;

    String picture;

    String text;

}
