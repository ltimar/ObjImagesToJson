package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ObjListFromObjInputFile {
    // aici citesc din fisier si intorc o lista de obiecte main.ObiectPtrJson
    private String inputObjFileName;
    private List<ObiectPtrJson> listaObiectePtrJson = new ArrayList<>();

    public ObjListFromObjInputFile(String numeFisier) {
        inputObjFileName = numeFisier;
    }

    public List<ObiectPtrJson> creareObjectListForJson() {
        ObiectPtrJson crtObiectPtrJson = new ObiectPtrJson();
        OptiParser lineParser = new OptiParser();
        boolean newObjectFlag = false;
        boolean vFlag = false;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.inputObjFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.startsWith("v") && line.charAt(1) == ' ') {
                    if (!vFlag && newObjectFlag) {
                        // am creat deja un obiect si am ajuns la un nou bulk de v-uri
                        vFlag = true;
                        newObjectFlag = false;
                        listaObiectePtrJson.add(crtObiectPtrJson);
                        crtObiectPtrJson = new ObiectPtrJson(); // creez un nou obiect pe care il voi popula
                    } else {
                        vFlag = true;
                    }
                    // am observat ca elementele v, vn, vt sunt in paragrafe.
                    // daca s-a terminat paragraful, si dau din nou de un nou paragraf v,
                    // consider ca e vorba deun nou obiect
                    lineParser.parseLine(crtObiectPtrJson.getV(), line);
                    // atentie aici!!!! in cadrul metodei parseLine modific (adaug) valorile noi gasite in line
                } else if (line.startsWith("vt")) {
                    if (vFlag) {
                        newObjectFlag = true;
                        vFlag = false;
                    }

                    lineParser.parseLine(crtObiectPtrJson.getVt(), line);
                    // s-a terminat un rand de vt
                } else if (line.startsWith("vn")) {
                    if (vFlag) {
                        newObjectFlag = true;
                        vFlag = false;
                    }
                    lineParser.parseLine(crtObiectPtrJson.getVn(), line);
                    // s-a terminat un rand de vn
                } else if (line.startsWith("f")) {
                    if (vFlag) {
                        newObjectFlag = true;
                        vFlag = false;
                    }
                    if (crtObiectPtrJson.getFaceSerializer() == null) {
                        crtObiectPtrJson.setFaceSerializer(findFaceLineParser(line));
                    }
                    lineParser.parsePolygonalFaceLine(crtObiectPtrJson.getFs(), line);
                }
                // s-a terminat un rand de vn
//                else   // deocamdata nu se foloseste vp -ul
//                if (line.startsWith("vp")) {  // vp ??
//                    if (vFlag){
//                        newObjectFlag = true;
//                        vFlag = false;
//                    }
//                    lineParser.parseLine(crtObiectPtrJson.getVp(),line);
                // s-a terminat un rand de vp
                // }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            // Exception handling
        }
        //adaug si ultimul elemet in lista
        listaObiectePtrJson.add(crtObiectPtrJson);
        return this.listaObiectePtrJson;
    }

    private String findFaceLineParser(String line) {
        if (Pattern.compile("^f .*/.*/.* ").matcher(line).find()) {
            return ObiectPtrJson.VERTEX_INDICES_TEXTURE_COORDS_NORMALS_FACE_SERIALIZER;
        }

        if (Pattern.compile("^f .*/.* ").matcher(line).find()) {
            return ObiectPtrJson.VERTEX_INDICES_TEXTURE_COORDS_FACE_SERIALIZER;
        }

        if (Pattern.compile("^f .*//.* ").matcher(line).find()) {
            return ObiectPtrJson.VERTEX_INDICES_NORMALS_FACE_SERIALIZER;
        }

        return ObiectPtrJson.VERTEX_INDICES_FACE_SERIALIZER;
    }
}
