package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {
    
    public static boolean validarEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean validarCodigoPostal(String codigoPostal) {
        String CODIGO_POSTAL_PATTERN = "^\\d{5}$";
        Pattern pattern = Pattern.compile(CODIGO_POSTAL_PATTERN);
        Matcher matcher = pattern.matcher(codigoPostal);
        return matcher.matches();
    }
    
    public static boolean validarTelefono(String telefono) {
        String TELEFONO_PATTERN = "^\\d{10}$";
        Pattern pattern = Pattern.compile(TELEFONO_PATTERN);
        Matcher matcher = pattern.matcher(telefono);
        return matcher.matches();
    }

    public static boolean validarRfcEmpresa(String rfc) {
        String RFC_EMPRESA_PATTERN = "^[A-Z&Ñ]{3}(\\d{6})[A-Z0-9]{3}$";
        Pattern pattern = Pattern.compile(RFC_EMPRESA_PATTERN);
        Matcher matcher = pattern.matcher(rfc);
        return matcher.matches();
    }
    
}
