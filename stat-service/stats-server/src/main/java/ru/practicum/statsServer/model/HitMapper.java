package ru.practicum.statsServer.model;

import ru.practicum.statsDto.dto.HitAnswer;
import ru.practicum.statsDto.dto.HitDto;
import ru.practicum.statsDto.dto.HitDtoAnswer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HitMapper {

    public static Hit dtoToInctance(HitDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new Hit().setApp(dto.getApp())
                .setUri(dto.getUri())
                .setIp(dto.getIp())
                .setTimestamp(LocalDateTime.parse(dto.getTimestamp(), formatter));
    }

    public static HitDtoAnswer toHitDtoAnswer(HitAnswer hitAnswer) {
        return new HitDtoAnswer()
                .setApp(hitAnswer.getApp())
                .setUri(hitAnswer.getUri())
                .setHits(hitAnswer.getHits());
    }

}
