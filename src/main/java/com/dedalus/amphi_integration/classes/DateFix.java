package com.dedalus.amphi_integration.classes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class DateFix {

    public static String dateFix(LocalDateTime localDateTime) {
        String returnDate;
        if (localDateTime != null) {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Stockholm"));

            returnDate = zonedDateTime.format(dateTimeFormatter);
        } else {
            returnDate = null;
        }

        return returnDate;
    }

    public static String dateFixShort(LocalDateTime localDateTime) {
        String returnDate;
        if (localDateTime != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Stockholm"));

            returnDate = zonedDateTime.format(dateTimeFormatter);
        } else {
            returnDate = null;
        }

        return returnDate;
    }

}
