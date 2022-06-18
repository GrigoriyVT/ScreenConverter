package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema {
    //'#' (231 - 255), '$' (198 - 230), '@' (165 - 197), '&' (132 - 164), '?' (99 - 131), '+' (66 - 98), '^' (33 - 65), ''' (0-32)

    private final char[] schema = {',', '^', '+', '?', '&', '@', '$', '#'}; // 8 наборов символов

    @Override
    public char convert(int color) {
        //int[][] range = new int[][]{{0, 32}, {33, 65}, {66, 98}, {99, 131}, {132, 164}, {165, 197}, {198, 230}, {231, 255}}; // 8 числовых диапазонов
        //String[] schema = {"'''", "^^^", "+++", "???", "&&&", "@@@", "$$$", "###"}; // 8 наборов символов
       /* for (int i = 0; i < schema.length; i++) {
            for (int j = range[i][0]; j <= range[i][1]; j++) {
                if (pixArray[0] == j) {
                    //System.out.print(schema[i]);
                    return schema[i];
                }
            }
        }*/
        return schema[color / (255 / 8)];
    }
}
