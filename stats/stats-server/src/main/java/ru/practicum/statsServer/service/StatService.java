package ru.practicum.statsServer.service;

import ru.practicum.statsServer.model.Hit;

import java.util.List;

public interface StatService {

    Hit add(Hit hit);

    List<Hit> getStats();

}
