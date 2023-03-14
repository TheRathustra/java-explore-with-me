package ru.practicum.mainService.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.mainService.model.Event;
import ru.practicum.mainService.model.User;

@Setter
@Getter
@NoArgsConstructor
public class CommentShortDto {

    private Long id;

    private UserShortDto author;

    private EventShortDto event;

    private String text;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class UserShortDto {

        private Long id;

        private String name;

        public static UserShortDto entityToUserShortDto(User entity) {
            UserShortDto dto = new UserShortDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            return dto;
        }

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class EventShortDto {

        private Long id;

        private String title;

        private Long category;

        private CommentShortDto.UserShortDto initiator;

        public static EventShortDto entityToEventShortDto(Event entity) {
            EventShortDto dto = new EventShortDto();
            dto.setId(entity.getId());
            dto.setCategory(entity.getCategory().getId());
            dto.setTitle(entity.getTitle());
            dto.setInitiator(CommentShortDto.UserShortDto.entityToUserShortDto(entity.getInitiator()));
            return dto;
        }

    }

}
