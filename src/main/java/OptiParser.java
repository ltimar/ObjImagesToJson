import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OptiParser {

    public List<Double> parseLine(List<Double> crtDoubleList,String line){
        StringTokenizer st = new StringTokenizer(line);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (isNumeric(token)) {
                crtDoubleList.add(Double.valueOf(token));
            }
        }
        return crtDoubleList;
    }


    private boolean isNumeric(String str) {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

// ptr parametrul f
//   https://en.wikipedia.org/wiki/Wavefront_.obj_file
//    # Polygonal face element (see below)
//    f 1 2 3               -> "f":[1,2,3]
//    f 3/1 4/2 5/3         -> "f":[3,4,5]
//    f 6/4/1 3/5/3 7/6/5   -> "f":[6,3,7]
//    f 7//1 8//2 9//3      -> "f":[7,8,9]
  // intereaeaza doar prima valoare
  // observatie: indiferent de modul de codare, exsita 3 perechi
// am impartit pe token-e si am luat primul numr din fiecare token
    public List<Integer> parsePolygonalFaceLine(List<Integer> facesList, String line) {
        StringTokenizer st = new StringTokenizer(line);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            String intVal = extractFirsIntNumber(token);
            if (! intVal.equals("-1")) {
                facesList.add(Integer.valueOf(intVal));
            }
        }
        return facesList;
    }

    private String extractFirsIntNumber(String str) {
        Pattern p = Pattern.compile("\\d+");
        // Matcher m = p.matcher("string1234more567string890");
        Matcher m = p.matcher(str);
        // while (m.find()) { // extrage toate numerele
        if (m.find()){
            return m.group();
        }
        return "-1";
    }

}
