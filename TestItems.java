package entity;

import javax.persistence.*;
import java.util.Scanner;

@Entity
@Table(name = "items", schema = "todo")
public class TestItems {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    @Basic
    @Column(name = "priority", nullable = true)
    private Integer priority;
    @Basic
    @Column(name = "description", nullable = true, length = 255)
    private String description;

    static Scanner sc = new Scanner(System.in);

    public TestItems(String title, int priority, String description) {
        this.title = title;
        this.priority = priority;
        this.description = description;
    }

    public TestItems() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestItems testItems = (TestItems) o;

        if (id != testItems.id) return false;
        if (title != null ? !title.equals(testItems.title) : testItems.title != null) return false;
        if (priority != null ? !priority.equals(testItems.priority) : testItems.priority != null) return false;
        if (description != null ? !description.equals(testItems.description) : testItems.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
