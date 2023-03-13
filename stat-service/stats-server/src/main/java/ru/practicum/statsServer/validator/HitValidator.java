package ru.practicum.statsServer.validator;

import org.springframework.stereotype.Component;
import ru.practicum.statsDto.dto.HitDto;
import ru.practicum.statsServer.error.HitValidationException;

@Component
public class HitValidator {

    public void validateHit(HitDto hit) {
        if (hit.getApp() == null || hit.getApp().isBlank() || hit.getApp().isEmpty()) {
            throw new HitValidationException("Field: app. Error: must not be blank. Value: null");
        }

        if (hit.getIp() == null || hit.getIp().isBlank() || hit.getIp().isEmpty()) {
            throw new HitValidationException("Field: ip. Error: must not be blank. Value: null");
        }

        if (hit.getUri() == null || hit.getUri().isBlank() || hit.getUri().isEmpty()) {
            throw new HitValidationException("Field: uri. Error: must not be blank. Value: null");
        }
    }

}
