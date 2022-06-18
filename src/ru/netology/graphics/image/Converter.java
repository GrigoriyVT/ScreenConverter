package ru.netology.graphics.image;

import javax.imageio.ImageIO;
//import javax.xml.validation.Schema;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
//import java.lang.Object;

public class Converter implements TextGraphicsConverter {
    private int width;//максимальная ширина
    private int height;//максимальная высота
    private double maxRatio; //максимум соотношение сторон
    private double ratio; //мое соотношение сторон
    private int newWidth;
    private int newHeight;
    private TextColorSchema schema = new ColorSchema();

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new URL(url)); //загрузка изображения
            int OurWith = img.getWidth(); //получили ширину текущего рисунка
            int OurHeight = img.getHeight(); //получили высоту текущего рисунка
            System.out.println("Параметры исходного изображения: " + OurWith + "x" + OurHeight); //выводим размер исходной картинки
            Image scaledImg = null;
            ratio = OurWith / OurHeight;
            if (ratio > maxRatio) //если соотношение сторон не удовлетворяет заданному, выбросить ошибку
                throw new BadImageSizeException(ratio, maxRatio);
            if (OurWith > width || OurHeight > height) { //если ширина или высота больше заданных меняем разрешение
                if (OurWith < OurHeight) { //если вертикальное изображение
                    double a = OurWith / height;
                    double b = OurHeight / width;
                    newWidth = (int) (OurWith / a);
                    newHeight = (int) (OurHeight / b);
                } else { //если горизонтальное изображение
                    double a = OurWith / width;
                    double b = OurHeight / height;
                    newWidth = (int) (OurWith / a);
                    newHeight = (int) (OurHeight / b);
                }
                scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); //меняем размер картинки ближе к заданным параметрам
            } else {//ничего не меняем если размеры картинки соответствуют заданнымы
                newWidth = OurWith;
                newHeight = OurHeight;
                scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            }
            BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY); //создаем новую пустую черно белую картинку с заданными размерами
            Graphics2D graphics = bwImg.createGraphics();  //создаем объект для 2д рисования
            graphics.drawImage(scaledImg, 0, 0, null);  //копируем картинку
            ImageIO.write(bwImg, "png", new File("out.png"));  //сохраняем черно-белую картинку в файл

            System.out.println("Параметры измененного изображения в пикселях: " + newWidth + "х" + newHeight); //выводим параметры картинки
            WritableRaster bwRaster = bwImg.getRaster();
            int[] pixArray = new int[3]; // массив оттенков RGB -> Black/white
            StringBuilder chars = new StringBuilder();

            for (int y = 0; y < bwRaster.getHeight(); y++) { //проходим по матрице пикселей
                chars.append("\n");
                for (int x = 0; x < bwRaster.getWidth(); x++) {
                    int color = bwRaster.getPixel(x, y, pixArray)[0];//оттенок пикселя
                    char c = schema.convert(color); //запоминаем символ c, например, в двумерном массиве или как-то ещё на ваше усмотрение
                    chars
                            .append(c)
                            .append(c)
                            .append(c);

                }
            }
            System.out.println(chars.toString());
        } catch (
                IOException e) {
            System.out.println("Ошибка доступа к изображению или ссылка не актуальна!");
        }
        return null;
    }

    @Override
    public void setMaxWidth(int width) {
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}