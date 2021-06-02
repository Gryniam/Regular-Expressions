import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tasks {
    //1
    static public void link() {
        ArrayList<String> mylinks = new ArrayList<>();
        try {
            String Text = readLinks("in.html");
            String regular = "<a\\s+(?:[^>]*?\\s+)?href[\\s]*=[\\s]*([\"])(.*?)\\1";//Найшов в неті, виділяє все до лапок і саме посилання запихає в другу групу
            Pattern href = Pattern.compile(regular, Pattern.CASE_INSENSITIVE);
            Matcher matcher = href.matcher(Text);
            while (matcher.find()) {
                mylinks.add(matcher.group(2));
            }
            writeLinks(mylinks);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static public void Contacts(){
        try {
            String Text = readLinks("KodforFirst.html");
            Pattern MIF = Pattern.compile("ФАКУЛЬТЕТ МАТЕМАТИКИ([\\s\\S]+?)ФІЗИКО-ТЕХНІЧНИЙ ФАКУЛЬТЕТ");
            Matcher matcher = MIF.matcher(Text);
            while(matcher.find()) {
                var TextWithMIF = matcher.group(1).toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //3
    static public void Months() {
        try {
            String Text = readLinks("zavd3.txt");
            String s = "січня";
            Pattern month = Pattern.compile("(лют[а-я]*|берез[а-я]*|квіт[а-я]*|трав[а-я]*|черв[а-я]*|лип[а-я]*|серп[а-я]*|верес[а-я]*|жовт[а-я]*|листопад[а-я]*|груд[а-я])", Pattern.CASE_INSENSITIVE);
            Matcher matcher = month.matcher(Text);
            Text = matcher.replaceAll(s);
            try {
                FileWriter writer = new FileWriter("zavd3zrob.txt", false);
                writer.write(Text);
                writer.close();
            } catch (IOException e) {
                System.out.println("Запис не здійснено");
                e.printStackTrace();
            }
            System.out.println("3 завдання завершено");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //4
    static public void MonthesDays() {
        ArrayList<String> dates = new ArrayList<>();
        try {
            String text = readLinks("zavd4.txt");
            final String datt = "\\/\\*((\\s)|())((((0[1-9]|[12][0-9]|3[01])[- -.](0[1-9]|1[012])[- -.](19|20\\d\\d)))|(((0[1-9]|[12][0-9]|3[01])[- -.])(лют[а-я]*|берез[а-я]*|квіт[а-я]*|трав[а-я]*|черв[а-я]*|лип[а-я]*|серп[а-я]*|верес[а-я]*|жовт[а-я]*|листопад[а-я]*|груд[а-я])[- -.](19|20\\d\\d))|((0[1-9]|[12][0-9]|3[01])[- -.](0[1-9]|1[012])))((\\s)|())\\*\\/";
            final Pattern pattern = Pattern.compile(datt, Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(text);
            System.out.println("        Дати        ");
            System.out.println("----------------------");
            while (matcher.find()) {
                System.out.println(matcher.group(4));
                System.out.println("----------------------");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //5
    static public void Zoo() {
        try {
            String text = readLinks("zoo.txt");
            final String catdog = "((чорний|білий)\\s(кіт|пес))";
            final Pattern pattern = Pattern.compile(catdog, Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(text);
            int[] arr = new int[4];
            while (matcher.find()) {
                switch (matcher.group(1)) {
                    case "чорний пес" -> arr[0]++;
                    case "чорний кіт" -> arr[1]++;
                    case "білий пес" -> arr[2]++;
                    case "білий кіт" -> arr[3]++;
                }
            }
            System.out.println("----------------------");
            System.out.println("Чорний пес: " + arr[0]);
            System.out.println("Чорний кіт: " + arr[1]);
            System.out.println("Білий пес: " + arr[2]);
            System.out.println("Білий кіт: " + arr[3]);
            System.out.println("----------------------");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void Validation(){
        try {
            String text = readLinks("zavd6.txt");
            final String regex = "^([А-ЯІ]{1}[а-яі]{1,23}\\s[А-ЯІ]{1}[а-яі]{1,23})*";
            final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            final Matcher matcher = pattern.matcher(text);
            System.out.println("Пройшли валідацію:");
            while(matcher.find()){
                if(matcher.group(1)!= null) {
                    System.out.println(matcher.group(1));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    static private String readLinks(String myFile) throws FileNotFoundException {
        StringBuilder myText = new StringBuilder();
        File m = new File(myFile);
        Scanner reader = new Scanner(m);
        while (reader.hasNextLine()) {
            String information = reader.nextLine() + "\n";
            myText.append(information);
        }
        reader.close();
        return myText.toString();
    }


    static private void writeLinks(ArrayList<String> links) {
        StringBuilder stat = new StringBuilder("""
                <!DOCTYPE html>
                <html>
                 <head>
                <title>Назви</title>
                 </head>
                 <body>
                 <table>""");

        String endfile = """
                \n </table>
                </body>
                </html>""";
        for (int i = 0; i < links.size(); i++) {
            String kusok = String.format("\n  <tr>\n    <td>%d</td>\n   <td>%s</td>\n  </tr>", i + 1, links.get(i));
            stat.append(kusok);
        }
        stat.append(endfile);
        try {
            FileWriter f = new FileWriter("out.html", false);
            f.write(stat.toString());
            f.close();
            System.out.println("Запис здійснено");
        } catch (IOException e) {
            System.out.println("Запис не здійснено");
            e.printStackTrace();
        }

    }


}
