package Repository_Pattern.Models;

public class CourseImpl implements Course {
    private String id;
    private String name;
    private String teacher;
    // Add additional fields as needed for the course entity

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public CourseImpl(String id, String name, String teacher) {
        this.id = id;
        this.name = name;
        // Initialize additional fields as needed for the course entity
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "CourseImpl{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
    // Implement additional methods as needed for the course entity
}