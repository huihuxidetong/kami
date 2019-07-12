package com.fh.service.qr;

public class QrCode {

    private String path;
    private Integer with;
    private String scene;
    private boolean auto_color;
    private LineColor lineColor;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getWith() {
        return with;
    }

    public void setWith(Integer with) {
        this.with = with;
    }

    public boolean isAuto_color() {
        return auto_color;
    }

    public void setAuto_color(boolean auto_color) {
        this.auto_color = auto_color;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public LineColor getLineColor() {
        return lineColor;
    }

    public void setLineColor(LineColor lineColor) {
        this.lineColor = lineColor;
    }
}
