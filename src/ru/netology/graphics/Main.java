package ru.netology.graphics;

import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.image.Converter;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        String url = "https://avatars.mds.yandex.net/get-zen_doc/1900266/pub_62692904514b9f308de44dd6_62692b3a5a068963bc6f3dca/scale_1200"; //"https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        converter.setMaxRatio(2);  // выставляет максимально допустимое соотрношение сторон картинки
        converter.setMaxWidth(150); //максимум символов по ширине
        converter.setMaxHeight(85); //максимум символов в высоту
        converter.convert(url);
    }
}
