package tn.esprit.studentmanagement.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService {

    private static final Logger log =
            LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        log.info("Récupération de tous les étudiants");
        List<Student> students = studentRepository.findAll();
        log.debug("Nombre d'étudiants récupérés : {}", students.size());
        return students;
    }

    @Override
    public Student getStudentById(Long id) {
        log.info("Récupération de l'étudiant avec idStudent={}", id);
        return studentRepository.findById(id)
                .orElseGet(() -> {
                    log.error("Étudiant non trouvé avec idStudent={}", id);
                    return null;
                });
    }

    @Override
    public Student saveStudent(Student student) {
        log.info("Sauvegarde d'un étudiant");
        log.debug("Données étudiant : {}", student);
        try {
            Student saved = studentRepository.save(student);
            log.info("Étudiant sauvegardé avec succès, idStudent={}", saved.getIdStudent());
            return saved;
        } catch (Exception e) {
            log.error("Erreur lors de la sauvegarde de l'étudiant", e);
            throw e;
        }
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Suppression de l'étudiant avec idStudent={}", id);
        try {
            studentRepository.deleteById(id);
            log.info("Étudiant supprimé avec succès, idStudent={}", id);
        } catch (Exception e) {
            log.error("Erreur lors de la suppression de l'étudiant avec idStudent={}", id, e);
            throw e;
        }
    }
}
