package com.example.app;

public class ModelMedicine {
    String image, interaction, missed, name, note, overdose, overview, precaution, side, storage, use, warning;

    public ModelMedicine(String image, String interaction, String missed, String name, String note, String overdose, String overview, String precaution, String side, String storage, String use, String warning) {
        this.image = image;
        this.interaction = interaction;
        this.missed = missed;
        this.name = name;
        this.note = note;
        this.overdose = overdose;
        this.overview = overview;
        this.precaution = precaution;
        this.side = side;
        this.storage = storage;
        this.use = use;
        this.warning = warning;
    }

    public ModelMedicine() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInteraction() {
        return interaction;
    }

    public void setInteraction(String interaction) {
        this.interaction = interaction;
    }

    public String getMissed() {
        return missed;
    }

    public void setMissed(String missed) {
        this.missed = missed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOverdose() {
        return overdose;
    }

    public void setOverdose(String overdose) {
        this.overdose = overdose;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPrecaution() {
        return precaution;
    }

    public void setPrecaution(String precaution) {
        this.precaution = precaution;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
