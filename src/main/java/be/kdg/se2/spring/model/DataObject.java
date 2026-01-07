package be.kdg.se2.spring.model;

/**
 * Data object for repository operations.
 * Place in: src/main/java/be/kdg/se2/templates/spring/model/
 */
public class DataObject {
    private Long id;
    private String name;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
