package ru.practicum.mainService.controller.privates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.request.ParticipationRequestDto;
import ru.practicum.mainService.service.api.privates.RequestServicePrivate;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/requests")
public class RequestControllerPrivate {

    private final RequestServicePrivate requestService;

    @Autowired
    public RequestControllerPrivate(RequestServicePrivate requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public List<ParticipationRequestDto> getUserRequests(@PathVariable(name = "userId") Long userId) {
        //В случае, если по заданным фильтрам не найдено ни одной заявки, возвращает пустой список
        return requestService.getUserRequests(userId);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ParticipationRequestDto> addParticipationRequest(@PathVariable(name = "userId") Long userId,
                                                                          @RequestParam(name = "eventId") Long eventId) {
        /*
        нельзя добавить повторный запрос (Ожидается код ошибки 409)
        инициатор события не может добавить запрос на участие в своём событии (Ожидается код ошибки 409)
        нельзя участвовать в неопубликованном событии (Ожидается код ошибки 409)
        если у события достигнут лимит запросов на участие - необходимо вернуть ошибку (Ожидается код ошибки 409)
        если для события отключена пре-модерация запросов на участие, то запрос должен автоматически перейти в
        состояние подтвержденного
         */
        ParticipationRequestDto requestDto = requestService.addParticipationRequest(userId, eventId);
        return new ResponseEntity<>(requestDto, HttpStatus.CREATED);
    }

    @PatchMapping(path = "{requestId}/cancel")
    @Transactional
    public ParticipationRequestDto cancelRequest(@PathVariable(name = "userId") Long userId,
                                                 @PathVariable(name = "requestId") Long requestId) {
        return requestService.cancelRequest(userId, requestId);
    }


}
