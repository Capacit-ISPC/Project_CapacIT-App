package com.capacitapp.models;

public class UsuarioCurso {
    private Curso curso;
    private boolean expanded;

    public UsuarioCurso(Curso curso) {
        this.curso = curso;
        this.expanded = false;
    }

    public Curso getCurso() {
        return curso;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}