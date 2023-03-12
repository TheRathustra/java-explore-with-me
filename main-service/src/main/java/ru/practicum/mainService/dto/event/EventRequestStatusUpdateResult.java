package ru.practicum.mainService.dto.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class EventRequestStatusUpdateResult {

    List<ParticipationRequestDto> confirmedRequests;

    List<ParticipationRequestDto> rejectedRequests;

    @NoArgsConstructor
    @Setter
    @Getter
    public static class ParticipationRequestDto {

        private Long id;

        private String created;

        private Long event;

        private Long requester;

        private String status;

    }

}
