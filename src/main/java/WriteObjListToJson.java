import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Paths;

public class WriteObjListToJson {

    public static void main(String args[]) {
        ObjListFromObjInputFile lauObjList = new ObjListFromObjInputFile(args[0]);

        // create object mapper instance
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(args[1]))) {
            // convert map to JSON file
            // sa verific cum arata lista
            mapper.writeValue(Paths.get(args[1]).toFile(), lauObjList.creareObjectListForJson());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
