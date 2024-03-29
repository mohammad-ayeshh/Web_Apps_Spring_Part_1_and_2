package Repository_Pattern.Models;

// Concrete implementation of the Teacher interface
public class TeacherImpl implements Teacher {
    private int id;
    private String name;
    // Other properties relevant to a teacher
    private String email;
    private String password;

    public TeacherImpl(int id, String name) {
        this.id = id;
        this.name = name;
        // Initialize other properties if needed
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    // Implement other methods if needed
}
