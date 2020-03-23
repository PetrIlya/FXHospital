package util;

import model.Doctor;
import model.Person;
import model.Record;
import model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RecordGenerator {
    public static final Integer ZERO = 0;

    public static final List<String> names;
    public static final List<String> surnames;
    public static final List<String> middleNames;
    public static final List<String> addresses;
    public static final List<String> illnessAnalysis;

    static {
        names = List.of(
                "Оксана",
                "Жанна",
                "Нона",
                "Эдуард",
                "Измаил",
                "Агафон",
                "Лавр",
                "Дина",
                "Эдуард",
                "Елена",
                "Ольга",
                "Илья",
                "Рената",
                "Анисья",
                "Мария",
                "Вероника",
                "Анфисаn",
                "Евдоким",
                "Борислав",
                "Модест",
                "Таисия",
                "Давид",
                "Ника",
                "Татьяна",
                "Аким",
                "Даниил",
                "Пелагея",
                "Бронислава",
                "Всеволод",
                "Жанна",
                "Агата",
                "Ева",
                "Мстислав",
                "Алла",
                "Герман",
                "Никон",
                "Ефрем",
                "Анастасия"
        );
    }

    static {
        surnames = List.of("Науменко",
                "Болокана",
                "Чадова",
                "Мишина",
                "Гринина",
                "Кузинкова",
                "Николаева",
                "Богоносцева",
                "Соломина",
                "Палюлин");
    }

    static {
        middleNames = List.of("Игнатиевич",
                "Феоктистович",
                "Ипатович",
                "Ипатиевич",
                "Валерьянович",
                "Андриянович",
                "Моисеевич",
                "Фролович",
                "Ипполитович",
                "Валерьянович");
    }

    static {
        illnessAnalysis = List.of("Болен",
                "Здоров",
                "Почти болен",
                "Почти здоров");
    }

    static {
        addresses = List.of("3 Miller Rd. " +
                        "Mason City, IA 50401",
                "7560 N. Edgewater St. " +
                        "North Miami Beach, FL 33160",
                "72 E. Sierra St. " +
                        "Southington, CT 06489",
                "494 Illinois Lane " +
                        "Dublin, GA 31021",
                "125 Liberty Drive " +
                        "Muncie, IN 47302",
                "916 Sulphur Springs Lane " +
                        "Statesville, NC 28625",
                "7972 Big Rock Cove Ave. " +
                        "Rockaway, NJ 07866",
                "26 Lyme Lane " +
                        "Phillipsburg, NJ 08865",
                "74 Indian Spring St. " +
                        "Havertown, PA 19083",
                "54 Harvey St. " +
                        "Manitowoc, WI 54220");
    }

    public static LocalDate generateDate() {
        long minDay = LocalDate.of(2000, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2020, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static <T> T getRandomData(List<T> elements) {
        return elements.get(
                ThreadLocalRandom.
                        current().
                        nextInt(0, elements.size())
        );
    }

    public static Person generatePerson() {
        return new Person(getRandomData(surnames),
                getRandomData(names),
                getRandomData(middleNames));
    }

    public static Student generateStudent() {
        return new Student(getRandomData(addresses),
                generateDate(),
                generateDate(),
                generatePerson());
    }

    public static Doctor generateDoctor() {
        return new Doctor(getRandomData(illnessAnalysis), generatePerson());
    }

    public static Record generateRecord() {
        return new Record(generateStudent(), generateDoctor());
    }

    public static List<Record> generateRecords(int amount) {
        List<Record> records = new ArrayList<>();
        while (amount != 0) {
            amount--;
            records.add(generateRecord());
        }
        return records;
    }
}
