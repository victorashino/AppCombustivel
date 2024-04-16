package com.victorashino.appgaseta.utils;

public class UtilGasEta {

    public static String calculeBestOption(double gasoline, double etanol) {

        double idealPrice = gasoline * 0.70;
        String returnMessage;

        if (etanol <= idealPrice) {
            returnMessage = "Abastecer com Etanol";
        } else {
            returnMessage = "Abastecer com Gasolina";
        }
        return returnMessage;
    }
}
