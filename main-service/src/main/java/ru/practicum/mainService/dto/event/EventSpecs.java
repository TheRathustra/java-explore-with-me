package ru.practicum.mainService.dto.event;

import org.springframework.data.jpa.domain.Specification;
import ru.practicum.mainService.model.Event;
import ru.practicum.mainService.model.State;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventSpecs {

    public static Specification<Event> byUsers(List<Long> users) {
        return (root, query, cb) -> users == null ? null : cb.equal(root.get("initiator").get("id"), users);
    }

    public static Specification<Event> byStates(List<String> states) {
        return (root, query, cb) -> states == null ? null : cb.equal(root.get("state"), states.stream().map(
                State::valueOf));
    }

    public static Specification<Event> byCategories(List<Long> categories) {
        return (root, query, cb) -> categories == null ? null : cb.equal(root.get("category").get("id"), categories);
    }

    public static Specification<Event> byRangeStart(String rangeStart) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return (root, query, cb) -> rangeStart == null ? null : cb.greaterThan(root.get("eventDate"),
                LocalDateTime.parse(rangeStart, formatter));
    }

    public static Specification<Event> byRangeEnd(String rangeEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return (root, query, cb) -> rangeEnd == null ? null : cb.lessThan(root.get("eventDate"),
                LocalDateTime.parse(rangeEnd, formatter));
    }

}
