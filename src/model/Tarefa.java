package model;

import java.io.*;
import java.util.Date;

public class Tarefa implements Registro {
    private int id;
    private String nome;
    private String descricao;
    private Date dataCriacao;
    private Date dataConclusao;
    private String status;
    private int prioridade;
    private int idCategoria;  

    
    public Tarefa() {
    }

    
    public Tarefa(int id, String nome, String descricao, Date dataCriacao, Date dataConclusao, String status, int prioridade, int idCategoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status = status;
        this.prioridade = prioridade;
        this.idCategoria = idCategoria;
    }

    
    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    
    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(nome);
        dos.writeUTF(descricao);
        dos.writeLong(dataCriacao != null ? dataCriacao.getTime() : 0);
        dos.writeLong(dataConclusao != null ? dataConclusao.getTime() : 0);
        dos.writeUTF(status);
        dos.writeInt(prioridade);
        dos.writeInt(idCategoria);
        return baos.toByteArray();
    }

    @Override
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.nome = dis.readUTF();
        this.descricao = dis.readUTF();
        long dataCriacaoMillis = dis.readLong();
        this.dataCriacao = dataCriacaoMillis > 0 ? new Date(dataCriacaoMillis) : null;
        long dataConclusaoMillis = dis.readLong();
        this.dataConclusao = dataConclusaoMillis > 0 ? new Date(dataConclusaoMillis) : null;
        this.status = dis.readUTF();
        this.prioridade = dis.readInt();
        this.idCategoria = dis.readInt();
    }
}
