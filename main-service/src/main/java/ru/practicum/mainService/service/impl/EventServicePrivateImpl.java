package ru.practicum.mainService.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.mainService.model.Event;
import ru.practicum.mainService.service.api.EventServicePrivate;

import java.util.List;

@Service
public class EventServicePrivateImpl implements EventServicePrivate {

    @Override
    public List<Event> GetUserEvents(Long userId, Integer from, Integer size) {
        return null;
    }
}
