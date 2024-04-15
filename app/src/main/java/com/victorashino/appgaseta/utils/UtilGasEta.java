package com.victorashino.appgaseta.utils;

public class UtilGasEta {

    public static String calculeBestOption(double gasolina, double etanol) {

        double idealPrice = gasolina * 0.70;
        String returnMessage;

        if (etanol <= idealPrice) {
            returnMessage = "Abastecer com Etanol";
        } else {
            returnMessage = "Abastecer com Gasolina";
        }
        return returnMessage;
    }
}
