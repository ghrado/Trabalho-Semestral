package com.gestaovendas.model.interfaces;

import java.util.List;

/**
 * Interface para operações de gerenciamento (CRUD)
 */
public interface Gerenciavel<T> {
    boolean criar(T objeto);
    T buscar(String id);
    List<T> listarTodos();
    boolean atualizar(T objeto);
    boolean excluir(String id);
    boolean reintegrar(String id);
}

