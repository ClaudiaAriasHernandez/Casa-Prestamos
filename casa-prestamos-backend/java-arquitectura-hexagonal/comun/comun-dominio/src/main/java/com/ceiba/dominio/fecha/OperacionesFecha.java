package com.ceiba.dominio.fecha;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class OperacionesFecha {

    private OperacionesFecha() {
    }

    public static LocalDate generarFechaActual() {

        return LocalDate.now();
    }

    public static Date convertirFecha(LocalDate fecha) {
 //cambiar al servicio
        return Date.from(fecha.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertirFormato(Date fecha) {
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat parseador = new SimpleDateFormat(strDateFormat);
        String date = parseador.format(fecha);

        try {
            return parseador.parse(date);
        } catch (ParseException e) {
            return fecha;
        }
    }

}
