package com.example.case_study_md3.model;

import java.util.Date;

public class Order {
    private int id;
    private Date createAt;
    private int idUser;
    private boolean isPaid;
    private float subTotal;
    private float discount;

    public Order() {
    }

    public Order(int id, Date createAt, int idUser, boolean isPaid, float subTotal, float discount) {
        this.id = id;
        this.createAt = createAt;
        this.idUser = idUser;
        this.isPaid = isPaid;
        this.subTotal = subTotal;
        this.discount = discount;
    }

    public Order(Date createAt, int idUser, boolean isPaid, float subTotal, float discount) {
        this.createAt = createAt;
        this.idUser = idUser;
        this.isPaid = isPaid;
        this.subTotal = subTotal;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
