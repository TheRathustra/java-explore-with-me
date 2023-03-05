package ru.practicum.mainService.dto.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ParticipationRequestDto {

    private Long id;

    private String created;

    private Long event;

    private Long requester;

    private String status;

}
