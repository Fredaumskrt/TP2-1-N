package model;

import java.io.*;
import java.lang.reflect.Constructor;
import aed3.ArvoreBMais;
import aed3.ParIdCategoriaIdTarefa;

public class ArquivoTarefas extends Arquivo<Tarefa> {
    private ArvoreBMais<ParIdCategoriaIdTarefa> indiceCategoria;

    public ArquivoTarefas(Constructor<Tarefa> construtor, String nomeArquivo) throws Exception {
        super(construtor, nomeArquivo);
       
        this.indiceCategoria = new ArvoreBMais<>(ParIdCategoriaIdTarefa.class.getConstructor(int.class, int.class), "indiceCategoria.db");
    }

    @Override
    public int create(Tarefa tarefa) throws Exception {
        int id = super.create(tarefa);
        tarefa.setID(id);

        
        int idCategoria = tarefa.getIdCategoria();
        indiceCategoria.create(new ParIdCategoriaIdTarefa(idCategoria, id));
        return id;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Tarefa tarefa = super.read(id);
        if (tarefa == null) return false;

        
        int idCategoria = tarefa.getIdCategoria();
        indiceCategoria.delete(new ParIdCategoriaIdTarefa(idCategoria, id));
        return super.delete(id);
    }

    
    public void listarTarefasPorCategoria(int idCategoria) throws Exception {
        for (ParIdCategoriaIdTarefa par : indiceCategoria.lista(idCategoria)) {
            Tarefa tarefa = super.read(par.getIdTarefa());
            if (tarefa != null) {
                System.out.println(tarefa);
            }
        }
    }
}
