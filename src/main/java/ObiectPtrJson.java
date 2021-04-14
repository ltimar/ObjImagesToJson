import java.util.ArrayList;
import java.util.List;

public class ObiectPtrJson {
    private List<Double> v;
    private List<Double> vt;
    private List<Double> vn;
    //private List<Double> vp;
    private List<Integer> fs;  // poate avea s - off si s-on !!!!

    public ObiectPtrJson(){
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
}
