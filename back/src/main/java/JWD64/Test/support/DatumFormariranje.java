//private LocalDateTime getLocalDateTime(String datumIVreme) throws DateTimeParseException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate datum = LocalDate.parse(datumIVreme.substring(0, 10), formatter);
//        LocalTime vreme = LocalTime.parse(datumIVreme.substring(11), DateTimeFormatter.ofPattern("HH:mm"));
//        return LocalDateTime.of(datum, vreme);
//    }