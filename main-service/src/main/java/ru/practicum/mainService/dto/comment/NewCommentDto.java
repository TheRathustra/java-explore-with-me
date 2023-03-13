package ru.practicum.mainService.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class NewCommentDto {

    private Long author;

    private Long event;

    private String text;

}
