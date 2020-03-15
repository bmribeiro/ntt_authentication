package com.everis.security.clock.boundary;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.ejb.Stateless;

import com.everis.security.clock.exceptions.DateFieldNotValidException;

@Stateless
public class ClockService {

    public LocalDateTime getCurrentTime(String zone){
        return LocalDateTime.now(ZoneId.of(zone.replace("%3A", ":")));
    }

    public Object getLocalTimeField(String zone, String field){
        LocalDateTime time = LocalDateTime.now(ZoneId.of(zone.replace("%3A", ":")));
        switch(field){
            case "year":
                return time.getYear();
            case "month":
                return time.getMonth();
            case "day":
                return time.getDayOfMonth();
            case "time":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                return time.format(formatter);
            default:
                throw new DateFieldNotValidException();
        }
    }
}