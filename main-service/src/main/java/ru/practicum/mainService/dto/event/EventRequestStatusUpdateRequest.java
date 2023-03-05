package ru.practicum.mainService.dto.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class EventRequestStatusUpdateRequest {

    Set<Long> requestIds;

    private String status;

}
