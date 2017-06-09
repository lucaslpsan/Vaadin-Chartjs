package com.example;


import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

public class ObjetoUnico {

    private String json;
    private String titulo;
    private String dataset;

    public ObjetoUnico(String json, String dataset, String titulo) {
        this.json = json;
        this.dataset = dataset;
        this.titulo = titulo;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
