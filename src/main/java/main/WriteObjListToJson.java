package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.nio.file.Paths;

public class WriteObjListToJson {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Not enough parameters");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath;
        if (args.length == 2) {
            outputFilePath = args[1];
        } else {
            System.out.println("Output file not found...");
            System.out.println("Placing output file next to the input");
            outputFilePath = inputFilePath.split(".obj")[0] + ".json";
        }

        ObjListFromObjInputFile lauObjList = new ObjListFromObjInputFile(inputFilePath);

        // create object mapper instance
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        try {
            // convert map to JSON file
            // sa verific cum arata lista
            mapper.writeValue(Paths.get(outputFilePath).toFile(), lauObjList.creareObjectListForJson());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
