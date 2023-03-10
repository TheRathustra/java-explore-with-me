package ru.practicum.mainService.service.impl.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.event.EventFullDto;
import ru.practicum.mainService.dto.event.EventMapper;
import ru.practicum.mainService.dto.event.EventSpecs;
import ru.practicum.mainService.dto.event.UpdateEventAdminRequest;
import ru.practicum.mainService.error.exception.category.CategoryNotFoundException;
import ru.practicum.mainService.error.exception.event.EventIncorrectStateForAdmin;
import ru.practicum.mainService.error.exception.event.EventNotFoundException;
import ru.practicum.mainService.model.Category;
import ru.practicum.mainService.model.Event;
import ru.practicum.mainService.model.State;
import ru.practicum.mainService.model.StateAction;
import ru.practicum.mainService.repository.admins.CategoryRepositoryAdmin;
import ru.practicum.mainService.repository.admins.EventRepositoryAdmin;
import ru.practicum.mainService.service.api.EventStatsService;
import ru.practicum.mainService.service.api.admins.EventServiceAdmin;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceAdminImpl implements EventServiceAdmin {

    private final EventRepositoryAdmin repository;

    private final CategoryRepositoryAdmin categoryRepository;

    private final EventStatsService eventStatsService;

    @Autowired
    public EventServiceAdminImpl(EventRepositoryAdmin repository, CategoryRepositoryAdmin categoryRepository, EventStatsService eventStatsService) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.eventStatsService = eventStatsService;
    }

    @Override
    public List<EventFullDto> getEvents(List<Long> users, List<String> states, List<Long> categories, String rangeStart, String rangeEnd, Integer from, Integer size) {

        Specification<Event> spec = EventSpecs.byUsers(users)
                .and(EventSpecs.byStates(states))
                .and(EventSpecs.byCategories(categories))
                .and(EventSpecs.byRangeStart(rangeStart))
                .and(EventSpecs.byRangeEnd(rangeEnd));

        int page = from / size;
        Pageable pageRequest = PageRequest.of(page, size);

        List<Event> events = repository.findAll(spec, pageRequest).toList();
        Map<Long, Long> stats = eventStatsService.getStats(events, false);
        eventStatsService.setViews(stats, events);

        return events.stream().map(EventMapper::entityToEventFullDto).collect(Collectors.toList());
    }

    @Override
    public EventFullDto updateEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        Optional<Event> eventOptional = repository.findById(eventId);
        if (eventOptional.isEmpty()) {
            throw new EventNotFoundException("Event with id=" + eventId + " was not found");
        }

        Event event = eventOptional.get();
        if ((updateEventAdminRequest.getStateAction() == StateAction.PUBLISH_EVENT)
                && event.getState() != State.PENDING) {
            throw new EventIncorrectStateForAdmin("Cannot publish the event because it's not in the right state: "
                                                + event.getState());
        }

        if (updateEventAdminRequest.getStateAction() == StateAction.REJECT_EVENT
                && event.getState() == State.PUBLISHED) {
            throw new EventIncorrectStateForAdmin("Cannot publish the event because it's not in the right state: "
                    + event.getState());
        }

        if (updateEventAdminRequest.getStateAction() == StateAction.PUBLISH_EVENT
                && updateEventAdminRequest.getEventDate() != null
                && updateEventAdminRequest.getEventDate().isBefore(LocalDateTime.now()
                .minus(1, ChronoUnit.HOURS))) {
            throw new EventIncorrectStateForAdmin("Cannot publish the event because incorrect event date");
        }

        if (updateEventAdminRequest.getAnnotation() != null)
            event.setAnnotation(updateEventAdminRequest.getAnnotation());
        if (updateEventAdminRequest.getCategory() != null) {
            Optional<Category> category = categoryRepository.findById(updateEventAdminRequest.getCategory());
            if (category.isEmpty()) {
                throw new CategoryNotFoundException("Category with id=" + updateEventAdminRequest.getCategory()
                        + " was not found");
            }
            event.setCategory(category.get());
        }
        if (updateEventAdminRequest.getDescription() != null)
            event.setDescription(updateEventAdminRequest.getDescription());
        if (updateEventAdminRequest.getEventDate() != null)
            event.setEventDate(updateEventAdminRequest.getEventDate());
        if (updateEventAdminRequest.getLocation() != null) {
            event.setLat(updateEventAdminRequest.getLocation().getLat());
            event.setLon(updateEventAdminRequest.getLocation().getLon());
        }
        if (updateEventAdminRequest.getPaid() != null)
            event.setPaid(updateEventAdminRequest.getPaid());
        if (updateEventAdminRequest.getParticipantLimit() != null)
            event.setParticipantLimit(updateEventAdminRequest.getParticipantLimit());
        if (updateEventAdminRequest.getRequestModeration() != null)
            event.setRequestModeration(updateEventAdminRequest.getRequestModeration());
        if (updateEventAdminRequest.getStateAction() != null) {
            if (updateEventAdminRequest.getStateAction() == StateAction.PUBLISH_EVENT) {
                event.setState(State.PUBLISHED);
                event.setPublishedOn(LocalDateTime.now());
            } else if (updateEventAdminRequest.getStateAction() == StateAction.SEND_TO_REVIEW) {
                event.setState(State.PENDING);
            } else {
                event.setState(State.CANCELED);
            }
        }
        if (updateEventAdminRequest.getTitle() != null)
            event.setTitle(updateEventAdminRequest.getTitle());

        Event updatedEvent = repository.save(event);

        return EventMapper.entityToEventFullDto(updatedEvent);
    }
}
