package ru.netology.graphics;

import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.image.Converter;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        converter.setMaxRatio(2);  // выставляет максимально допустимое соотрношение сторон картинки
        converter.setMaxWidth(200); //максимум символов по ширине
        converter.setMaxHeight(100); //максимум символов в высоту
        converter.convert(url);
    }
}
