package com.example.case_study_md3.model;

public class ProductPageable {
    private int page = 1;
    private int limit = 3;
    private String kw = "";
    private String sortField = "id";
    private String order = "asc";
    private int idCategory = -1;
    private int totalPage;
    private String scale = "all";
}
