package model;

public class Chart {
    private int chart_image;
    private String chart_title;

    public Chart(int chart_image, String chart_title){
        this.chart_image = chart_image;
        this.chart_title = chart_title;
    }

    public int getChart_image() {
        return chart_image;
    }

    public void setChart_image(int chart_image) {
        this.chart_image = chart_image;
    }

    public String getChart_title() {
        return chart_title;
    }

    public void setChart_title(String chart_title) {
        this.chart_title = chart_title;
    }
}
