package ru.practicum.mainService.dto.request;

import ru.practicum.mainService.model.Request;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RequestMapper {

    public static ParticipationRequestDto requestToParticipationRequestDto(Request request) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT);

        ParticipationRequestDto dto = new ParticipationRequestDto();
        dto.setId(request.getId());
        dto.setRequester(request.getRequester().getId());
        dto.setEvent(request.getEvent().getId());
        dto.setCreated(request.getCreated().format(formatter));
        dto.setStatus(request.getStatus().name());
        return dto;
    }

}
