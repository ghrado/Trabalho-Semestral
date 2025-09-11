package com.gestaovendas.model.interfaces;

/**
 * Interface para objetos que podem ser autenticados
 */
public interface Autenticavel {
    boolean autenticar(String usuario, String senha);
    void alterarSenha(String novaSenha);
    boolean isContaBloqueada();
    void bloquearConta();
    void desbloquearConta();
}

