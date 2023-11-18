package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {
    
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String CODIGO_POSTAL_PATTERN = "^\\d{5}$";
    public static final String TELEFONO_PATTERN = "^\\d{10}$";
    public static final String RFC_EMPRESA_PATTERN = "^[A-Z&Ñ]{3}(\\d{6})[A-Z0-9]{3}$";
    
    public static boolean validarCadena(String cadena, String expresionRegular) {
        Pattern pattern = Pattern.compile(expresionRegular);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }
    
}
