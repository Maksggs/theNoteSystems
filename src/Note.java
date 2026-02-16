import javax.lang.model.element.Name;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Note {
    static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
    public static class notes{
        static String Name;
        static String Opisanie;
        static String date;

        notes(String Name, String Opisanie){
            this.Name = Name;
            this.Opisanie = Opisanie;
            Date date = new Date();
            this.date = formatter.format(date);
        }

        public static void Get(){
            System.out.print(notes.Name +"\n"+notes.Opisanie +"\n"+ notes.date );
        }

    }
    public static void main(String[] args) {
    notes note = new notes("Первая","Первая заметка, пока что");
    note.Get();


    }


}
