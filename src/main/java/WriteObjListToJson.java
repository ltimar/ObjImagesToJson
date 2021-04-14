import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Paths;

public class WriteObjListToJson {
    //public final static String  jsonFileName = "d:\\Lori\\2021\\proiecte\\AndreiOBJToJson\\MulteOb.json";
    public final static String  jsonFileName = "d:\\Lori\\2021\\proiecte\\AndreiOBJToJson\\SimpleRobotRez.json";

    //public void creareObjectMapper(){
    public static void main(String args[]){
        String objFileName = "d:\\Lori\\2021\\proiecte\\AndreiOBJToJson\\SimpleRobot.obj";
       // String objFileName = "d:\\Lori\\2021\\proiecte\\AndreiOBJToJson\\3D_Arrows_by_Peter_Iliev_obj.obj";
       // String objFileName = args[0];
       // String jsonFileName = args[1];

        ObjListFromObjInputFile lauObjList = new ObjListFromObjInputFile(objFileName);

        // create object mapper instance
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        try( BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(jsonFileName))) {
            // convert map to JSON file
            // sa verific cum arata lista
            mapper.writeValue(Paths.get(jsonFileName).toFile(), lauObjList.creareObjectListForJson());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
