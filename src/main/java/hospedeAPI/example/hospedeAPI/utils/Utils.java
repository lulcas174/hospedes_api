package hospedeAPI.example.hospedeAPI.utils;

import org.springframework.stereotype.Component;

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


    public boolean isAfterTime(Date date, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        if (currentHour > hour) {
            return true;
        } else if (currentHour == hour && currentMinute > minute) {
            return true;
        }

        return false;
    }

    public boolean isWeekday(int dayOfWeek) {
        return dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY;
    }

}
