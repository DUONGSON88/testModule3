package service;

import java.util.List;

public interface iStudentService<S> {
    boolean add(S s);
    boolean edit(S s,int id);
    boolean delete(int id);
    List<S> findAll();
    List<S> findStudentByName(String nameFind);
    S findStudentById(int id);
}
