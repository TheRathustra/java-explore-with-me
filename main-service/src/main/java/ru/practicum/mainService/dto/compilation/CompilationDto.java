package ru.practicum.mainService.dto.compilation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.mainService.model.Category;
import ru.practicum.mainService.model.Event;
import ru.practicum.mainService.model.User;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {

    private Long id;

    private Set<EventShortDto> events;

    private Boolean pinned;

    private String title;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class EventShortDto {

        private Long id;

        private String annotation;

        private CategoryDto category;

        private Integer confirmedRequests;

        private LocalDateTime eventDate;

        private UserShortDto initiator;

        private Boolean paid;

        private String title;

        private Long views;

        @AllArgsConstructor
        @NoArgsConstructor
        @Setter
        @Getter
        public static class CategoryDto {

            private Long id;

            private String name;

            public static CategoryDto entityToCategoryDto(Category entity) {
                return new CategoryDto(entity.getId(),
                        entity.getName());
            }
        }

        public static EventShortDto entityToEventShortDto(Event entity) {
            EventShortDto dto = new EventShortDto();
            dto.setId(entity.getId());
            dto.setAnnotation(entity.getAnnotation());
            dto.setCategory(CompilationDto.EventShortDto.CategoryDto.entityToCategoryDto(entity.getCategory()));
            dto.setConfirmedRequests(entity.getConfirmedRequests());
            dto.setEventDate(entity.getEventDate());
            dto.setInitiator(CompilationDto.EventShortDto.UserShortDto.entityToUserShortDto(entity.getInitiator()));
            dto.setPaid(entity.getPaid());
            dto.setTitle(entity.getTitle());
            dto.setViews(entity.getViews());
            return dto;
        }

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
    }
}

