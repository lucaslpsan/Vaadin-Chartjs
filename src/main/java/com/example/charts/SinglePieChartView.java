package com.example.charts;

import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.PieChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.PieDataset;
import com.example.AbstractChartView;
import com.example.DemoUtils;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

@UIScope
@SpringView
public class SinglePieChartView extends AbstractChartView {

    private static final long serialVersionUID = 4894923343920891837L;

    @Override
    public Component getChart() {
        PieChartConfig config = new PieChartConfig();
        config
            .data()
                .labels("Red", "Green", "Yellow", "Grey", "Dark Grey")
                .addDataset(new PieDataset().label("Dataset 1"))
                .and();
        
        config.
            options()
                .responsive(true)
                .title()
                    .display(true)
                    .text("Chart.js Single Pie Chart")
                    .and()
                .animation()
                    //.animateScale(true)
                    .animateRotate(true)
                    .and()
               .done();
        
        List<String> labels = config.data().getLabels();
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            PieDataset lds = (PieDataset) ds;
            List<Double> data = new ArrayList<>();
            List<String> colors = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((double) (Math.round(Math.random() * 100)));
                colors.add(ColorUtils.randomColor(0.7));
            }
            lds.backgroundColor(colors.toArray(new String[colors.size()]));
            lds.dataAsList(data);
        }
        
        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);
        chart.addClickListener((a,b) -> {
            PieDataset dataset = (PieDataset) config.data().getDatasets().get(a);
            DemoUtils.notification(a, b, dataset);
        });
        return chart; 
    }

}
