package com.byteowls.vaadin.chartjs.options.types;

import com.byteowls.vaadin.chartjs.config.ChartConfig;
import com.byteowls.vaadin.chartjs.options.AbstractOptions;
import com.byteowls.vaadin.chartjs.options.PieAnimation;
import com.byteowls.vaadin.chartjs.options.scale.RadialLinearScale;
import com.byteowls.vaadin.chartjs.utils.JUtils;

import elemental.json.JsonObject;

public class PolarAreaChartOptions extends AbstractOptions<PolarAreaChartOptions> {

    private static final long serialVersionUID = -8062416164912751507L;

    private Double startAngle;
    private PieAnimation<PolarAreaChartOptions> pieAnimation;
    private RadialLinearScale scale;

    public PolarAreaChartOptions(ChartConfig chartConfig) {
        super(chartConfig);
    }

    /**
     * Sets the starting angle for the first item in a dataset
     */
    public PolarAreaChartOptions startAngle(double startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    public PolarAreaChartOptions scale(RadialLinearScale scale) {
        this.scale = scale;
        return this;
    }

    /**
     * Step into the charts animation configuration
     */
    public PieAnimation<PolarAreaChartOptions> animation() {
        if (pieAnimation == null) {
            pieAnimation = new PieAnimation<>(getThis());
        }
        return pieAnimation;
    }


    @Override

    public JsonObject buildJson() {
        JsonObject map = super.buildJson();
        JUtils.putNotNull(map, "startAngle", startAngle);
        JUtils.putNotNull(map, "scale", scale);
        JUtils.putNotNull(map, "animation", pieAnimation);
        return map;
    }
    @Override
    public PolarAreaChartOptions getThis() {
        return this;
    }

}
