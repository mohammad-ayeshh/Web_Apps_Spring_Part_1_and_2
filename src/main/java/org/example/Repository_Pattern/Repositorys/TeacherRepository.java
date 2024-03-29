package Repository_Pattern.Repositorys;

import Repository_Pattern.Models.Teacher;

import java.util.List;

public interface TeacherRepository {
    void addTeacher(Teacher teacher);
    Teacher getTeacherById(int teacherId);
    List<Teacher> getAllTeachers();
    void updateTeacher(Teacher teacher);
    void deleteTeacher(int teacherId);
}
