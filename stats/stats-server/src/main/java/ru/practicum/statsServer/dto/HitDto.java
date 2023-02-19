package ru.practicum.statsServer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.statsServer.model.Hit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HitDto {

    private String app;

    private String uri;

    private String ip;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String timestamp;

    public static Hit dtoToInctance(HitDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new Hit().setApp(dto.getApp())
                .setUri(dto.getUri())
                .setIp(dto.getIp())
                .setTimestamp(LocalDateTime.parse(dto.getTimestamp(), formatter));
    }

}
