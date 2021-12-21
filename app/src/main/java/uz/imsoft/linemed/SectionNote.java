package uz.imsoft.linemed;

public class SectionNote {

    private String Id;
    private String nomi;
    private int img;

    public SectionNote(String id, String nomi, int img) {
        Id = id;
        this.nomi = nomi;
        this.img = img;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNomi() {
        return nomi;
    }

    public void setNomi(String nomi) {
        this.nomi = nomi;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
