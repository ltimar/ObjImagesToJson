package main;

import java.util.ArrayList;
import java.util.List;

public class ObiectPtrJson {
    public static final String VERTEX_INDICES_FACE_SERIALIZER = "vi";
    public static final String VERTEX_INDICES_TEXTURE_COORDS_FACE_SERIALIZER = "vit";
    public static final String VERTEX_INDICES_TEXTURE_COORDS_NORMALS_FACE_SERIALIZER = "vitn";
    public static final String VERTEX_INDICES_NORMALS_FACE_SERIALIZER = "vin";

    private String faceSerializer;
    private List<Double> v;
    private List<Double> vt;
    private List<Double> vn;
    //private List<Double> vp;
    private List<Integer> fs;  // poate avea s - off si s-on !!!!

    public ObiectPtrJson() {
        this.v = new ArrayList<>();
        this.vt = new ArrayList<>();
        this.vn = new ArrayList<>();
        this.fs = new ArrayList<>();
    }

    public List<Double> getV() {
        return v;
    }

    public List<Double> getVt() {
        return vt;
    }

    public List<Double> getVn() {
        return vn;
    }

    public List<Integer> getFs() {
        return fs;
    }

    public void setFaceSerializer(String faceSerializer) {
        this.faceSerializer = faceSerializer;
    }

    public String getFaceSerializer() {
        return faceSerializer;
    }
}
