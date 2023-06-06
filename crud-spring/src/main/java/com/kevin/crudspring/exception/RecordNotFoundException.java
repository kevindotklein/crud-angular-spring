package com.kevin.crudspring.exception;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(Long id) {
        super("Registro não encontrado, id: "+id);
    }
}
