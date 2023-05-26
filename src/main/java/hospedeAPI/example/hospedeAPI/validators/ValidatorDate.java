package hospedeAPI.example.hospedeAPI.validators;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class ValidatorDate {
    public static boolean isValidDateFormat(String dateStr) {
        if (dateStr.length() != 10) {
            return false;
        }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
