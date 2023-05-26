package hospedeAPI.example.hospedeAPI.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class Utils {

    public Date convertStringInDate(String data) {
        String[] dataSplit = data.split("/");
        int dia = Integer.parseInt(dataSplit[0]);
        int mes = Integer.parseInt(dataSplit[1]) - 1;
        int ano = Integer.parseInt(dataSplit[2]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes, dia);
        return calendar.getTime();
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }


    public int calculateDays(Date startDate, Date endDate) {
        long difference = endDate.getTime() - startDate.getTime();
        int days = (int) (difference / (24 * 60 * 60 * 1000));
        return days;
    }


    public Date addDays(Date date, int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }


    public int getDayOfWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    public boolean isWeekday(int dayOfWeek) {
        return dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY;
    }
    
    public boolean isWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }
}
