package model;

/**
 * Chart Class
 *
 * This class represent a single chart element in the profile layout. A chart contains useful stats of a person profile
 * that are calculated based on the conversation that the user does and the posts he likes and so on.
 * The carts are helpful when they give a quick overview of the data. So that, they need to be clear and easy to read.
 * So, this class represent the model class for the <layout name="item_profile_chart"></layout> that is a single item in
 * the recycler view in the <layout name="fragment_profile_chart"></layout> that belongs to the <layout name="fragment_profile"></layout>
 */
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
