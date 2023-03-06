package ru.practicum.mainService.dto.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.mainService.model.Status;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class EventRequestStatusUpdateRequest {

    Set<Long> requestIds;

    private Status status;

}
