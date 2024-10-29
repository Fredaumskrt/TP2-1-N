package model;

import java.io.*;
import java.lang.reflect.Constructor;
import aed3.HashExtensivel;
import aed3.ParIDPosicao;

public class Arquivo<T extends Registro> {
    protected RandomAccessFile arquivo;
    protected Constructor<T> construtor;
    protected String nomeArquivo;
    protected int ultimoID;
    protected HashExtensivel<ParIDPosicao> indice;  

    public Arquivo(Constructor<T> construtor, String nomeArquivo) throws Exception {
        this.construtor = construtor;
        this.nomeArquivo = nomeArquivo;
        this.arquivo = new RandomAccessFile(nomeArquivo, "rw");
        this.indice = new HashExtensivel<>(ParIDPosicao.class.getConstructor(long.class, long.class), 10, "indice.db", "cestos.db");
        
        if (arquivo.length() == 0) {
            arquivo.writeInt(0); 
            ultimoID = 0;
        } else {
            arquivo.seek(0);
            ultimoID = arquivo.readInt();
        }
    }

    public int create(T objeto) throws Exception {
        objeto.setID(++ultimoID);
        byte[] data = objeto.toByteArray();
        long posicao = arquivo.length();
        arquivo.seek(posicao);
        arquivo.writeByte(0);  
        arquivo.writeShort(data.length);
        arquivo.write(data);
        arquivo.seek(0);
        arquivo.writeInt(ultimoID);
        indice.create(new ParIDPosicao(ultimoID, posicao));
        return objeto.getID();
    }

    public T read(int id) throws Exception {
        ParIDPosicao posicao = indice.read(id);
        if (posicao == null || posicao.getPosicao() == -1) {
            return null;
        }
        arquivo.seek(posicao.getPosicao());
        if (arquivo.readByte() == 1) {
            return null;  
        }
        int tamanho = arquivo.readShort();
        byte[] data = new byte[tamanho];
        arquivo.readFully(data);
        T objeto = construtor.newInstance();
        objeto.fromByteArray(data);
        return objeto;
    }

    public boolean update(T objeto) throws Exception {
        ParIDPosicao posicao = indice.read(objeto.getID());
        if (posicao == null || posicao.getPosicao() == -1) {
            return false;
        }
        arquivo.seek(posicao.getPosicao());
        arquivo.readByte();
        int tamanho = arquivo.readShort();
        byte[] dataNovo = objeto.toByteArray();
        
        if (dataNovo.length > tamanho) {
            delete(objeto.getID());
            create(objeto);
        } else {
            arquivo.seek(posicao.getPosicao() + 3);
            arquivo.write(dataNovo);
        }
        return true;
    }

    public boolean delete(int id) throws Exception {
        ParIDPosicao posicao = indice.read(id);
        if (posicao == null || posicao.getPosicao() == -1) {
            return false;
        }
        arquivo.seek(posicao.getPosicao());
        arquivo.writeByte(1);  
        return true;
    }
}
