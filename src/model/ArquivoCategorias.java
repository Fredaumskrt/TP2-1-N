package model;

import java.io.*;
import java.lang.reflect.Constructor;

import aed3.ArvoreBMais;
import aed3.ParNomeId;
import model.Arquivo;

public class ArquivoCategorias extends Arquivo<Categoria> {
    private ArvoreBMais<ParNomeId> indiceNome;

    public ArquivoCategorias(Constructor<T> construtor, String nomeArquivo) throws Exception {
        super(construtor, nomeArquivo);
        this.indiceNome = new ArvoreBMais<>(ParNomeId.class.getConstructor(String.class, int.class), "indiceNomeCategoria.db");
    }

    @Override
    public int create(T categoria) throws Exception {
        int id = super.create(categoria);
        categoria.setID(id);

        if (categoria instanceof Categoria) {
            String nomeCategoria = ((Categoria) categoria).getNome();
            indiceNome.create(new ParNomeId(nomeCategoria, id));
        }
        return id;
    }

    @Override
    public boolean delete(int id) throws Exception {
        T categoria = super.read(id);
        if (categoria == null) return false;

        if (categoria instanceof Categoria) {
            String nomeCategoria = ((Categoria) categoria).getNome();
            indiceNome.delete(new ParNomeId(nomeCategoria, id));
        }
        return super.delete(id);
    }

    public T buscarPorNome(String nome) throws Exception {
        ParNomeId parNomeId = indiceNome.read(new ParNomeId(nome));
        if (parNomeId == null) {
            return null;
        }
        return super.read(parNomeId.getID());
    }
}
