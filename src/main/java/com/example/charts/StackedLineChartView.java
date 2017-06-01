package com.example.charts;

import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.example.AbstractChartView;
import com.example.DemoUtils;
import com.byteowls.vaadin.chartjs.options.InteractionMode;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.CategoryScale;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

@UIScope
@SpringView
public class StackedLineChartView extends AbstractChartView {

    private static final long serialVersionUID = -9142435970343490721L;

    @Override
    public Component getChart() {
        LineChartConfig lineConfig = new LineChartConfig();
        lineConfig.data()
            .labels("January", "February", "March", "April", "May", "June", "July")
            .addDataset(new LineDataset().label("My First dataset").borderColor(DemoUtils.RGB_RED).backgroundColor(DemoUtils.RGB_RED))
            .addDataset(new LineDataset().label("My Second dataset").borderColor(DemoUtils.RGB_BLUE).backgroundColor(DemoUtils.RGB_BLUE))
            .addDataset(new LineDataset().label("My Third dataset").borderColor(DemoUtils.RGB_GREEN).backgroundColor(DemoUtils.RGB_GREEN))
            .addDataset(new LineDataset().label("My Third dataset").borderColor(DemoUtils.RGB_YELLOW).backgroundColor(DemoUtils.RGB_YELLOW))
            .and()
        .options()
            .responsive(true)
            .title()
                .display(true)
                .text("Chart.js Line Chart - Stacked Line")
                .and()
            .tooltips()
                .mode(InteractionMode.INDEX)
                .and()
            .hover()
                .mode(InteractionMode.INDEX)
                .and()
            .scales()
            .add(Axis.X, new CategoryScale()
                    .scaleLabel()
                        .display(true)
                        .labelString("Month")
                        .and())
            .add(Axis.Y, new LinearScale()
                    .stacked(true)
                    .scaleLabel()
                        .display(true)
                        .labelString("Value")
                        .and())
            .and()
        .done();
        
        // add random data for demo
        List<String> labels = lineConfig.data().getLabels();
        for (Dataset<?, ?> ds : lineConfig.data().getDatasets()) {
            LineDataset lds = (LineDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((double) (Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100));
            }
            lds.dataAsList(data);
        }

        ChartJs chart = new ChartJs(lineConfig);
        chart.setJsLoggingEnabled(true);
        chart.addClickListener((a,b) -> {
            LineDataset dataset = (LineDataset) lineConfig.data().getDatasets().get(a);
            DemoUtils.notification(a, b, dataset);
        });
        return chart;
    }

}
