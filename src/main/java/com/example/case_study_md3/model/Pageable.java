package com.example.case_study_md3.model;

public class Pageable {
    private int page = 1;
    private int limit = 3;
    private String kw = "";
    private String sortField = "id";
    private String order = "asc";
    private int type = -1;
    private int totalPage;

    public Pageable() {
    }

    public Pageable(int page, int limit, String kw, String sortField, String order, int type, int totalPage) {
        this.page = page;
        this.limit = limit;
        this.kw = kw;
        this.sortField = sortField;
        this.order = order;
        this.type = type;
        this.totalPage = totalPage;
    }

    public Pageable(int page, int limit, String kw, String sortField, String order, int totalPage) {
        this.page = page;
        this.limit = limit;
        this.kw = kw;
        this.sortField = sortField;
        this.order = order;
        this.totalPage = totalPage;
    }
}
