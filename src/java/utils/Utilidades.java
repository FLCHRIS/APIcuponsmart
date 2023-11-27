package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String CODIGO_POSTAL_PATTERN = "^\\d{5}$";
    public static final String TELEFONO_PATTERN = "^\\d{10}$";
    public static final String RFC_EMPRESA_PATTERN = "^[A-Z&Ñ]{3}(\\d{6})[A-Z0-9]{3}$";
    public static final String FECHA_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
    public static final String CURP_PATTERN = "^([A-Z][AEIOUX][A-Z]{2}\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d])(\\d)$";

    public static boolean validarCadena(String cadena, String expresionRegular) {
        Pattern pattern = Pattern.compile(expresionRegular);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }

    //Devuelve true si la cadena que llega tiene la sintaxis de un decimal
    public static boolean esDecimal(String cad) {
        boolean hayPunto = false;
        StringBuffer parteEntera = new StringBuffer();
        StringBuffer parteDecimal = new StringBuffer();
        int i = 0, posicionDelPunto;

        for (i = 0; i < cad.length(); i++) {
            if (cad.charAt(i) == '.') //Detectar si hay un punto decimal en la cadena
            {
                hayPunto = true;
            }
        }
        if (hayPunto) //Si hay punto guardar la posicion donde se encuentra el carater punto
        {
            posicionDelPunto = cad.indexOf('.');                  //(si la cadena tiene varios puntos, detecta donde esta el primero).
        } else {
            return false;                                       //Si no hay punto; no es decimal
        }
        if (posicionDelPunto == cad.length() - 1 || posicionDelPunto == 0) //Si el punto esta al final o al principio no es un decimal
        {
            return false;
        }

        for (i = 0; i < posicionDelPunto; i++) {
            parteEntera.append(cad.charAt(i));                 //Guardar la parte entera en una variable
        }
        for (i = 0; i < parteEntera.length(); i++) {
            if (!Character.isDigit(parteEntera.charAt(i))) //Si alguno de los caracteres de la parte entera no son digitos no es decimal
            {
                return false;
            }
        }

        for (i = posicionDelPunto + 1; i < cad.length(); i++) {
            parteDecimal.append(cad.charAt(i));                 //Guardar la parte decimal en una variable
        }
        for (i = 0; i < parteDecimal.length(); i++) {
            if (!Character.isDigit(parteDecimal.charAt(i))) //Si alguno de los caracteres de la parte decimal no es un digito no es decimal
            {
                return false;                                   //Incluye el caso en el que la cadena tenga dos o mas puntos
            }
        }
        return true;                                            //Si paso todas las pruebas anteriores, la cadena es un Numero decimal
    }

}
