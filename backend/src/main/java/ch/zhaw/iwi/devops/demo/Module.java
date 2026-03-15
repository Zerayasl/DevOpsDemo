package ch.zhaw.iwi.devops.demo;

public class Module {

    private Integer id;
    private String title;
    private String description;
    private String semester;
    private String ects;

    public Module() {
    }

    public Module(Integer id, String title, String description, String semester, String ects) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.semester = semester;
        this.ects = ects;
    }

    public Integer getId() {
        return id;
    }    

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSemester() {
        return semester;
    }

    public String getEcts() {
        return ects;
    }
}