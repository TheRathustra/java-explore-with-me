package ru.practicum.mainService.service.api;

import ru.practicum.mainService.model.Event;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface EventStatsService {

    Map<Long, Long> getStats(List<Event> events, Boolean unique);

    void sendStats(HttpServletRequest request);

    void setViews(Map<Long, Long> views, List<Event> events);

}
