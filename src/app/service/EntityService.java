package app.service;

public interface EntityService{

    Type getType();
    <T> T getById(Long id);
    void printAll();
}
