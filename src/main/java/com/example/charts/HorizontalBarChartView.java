package com.example.charts;

import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.example.AbstractChartView;
import com.example.DemoUtils;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.elements.Rectangle.RectangleEdge;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

@UIScope
@SpringView
public class HorizontalBarChartView extends AbstractChartView {

    private static final long serialVersionUID = -3541750814970379914L;

    @Override
    public Component getChart() {
        BarChartConfig barConfig = new BarChartConfig();
        barConfig.horizontal();
        barConfig.
            data()
                .labels("January", "February", "March", "April", "May", "June", "July")
                .addDataset(new BarDataset().backgroundColor("rgba(220,220,220,0.5)").label("Dataset 1"))
                .addDataset(new BarDataset().backgroundColor("rgba(151,187,205,0.5)").label("Dataset 2").hidden(true))
                .addDataset(new BarDataset().backgroundColor("rgba(151,187,205,0.5)").label("Dataset 3"))
                .and()
            .options()
                .responsive(true)
                .title()
                    .display(true)
                    .text("Chart.js Horizontal Bar Chart")
                    .and()
                 .elements()
                     .rectangle()
                         .borderWidth(2)
                         .borderColor("rgb(0, 255, 0)")
                         .borderSkipped(RectangleEdge.LEFT)
                         .and()
                     .and()
                 .legend()
                     .fullWidth(false)
                     .position(Position.LEFT)
                     .and()
               .done();
        
        List<String> labels = barConfig.data().getLabels();
        for (Dataset<?, ?> ds : barConfig.data().getDatasets()) {
            BarDataset lds = (BarDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((double) (Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100));
            }
            lds.dataAsList(data);
        }
        
        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        chart.addClickListener((a,b) -> {
            BarDataset dataset = (BarDataset) barConfig.data().getDatasets().get(a);
            DemoUtils.notification(a, b, dataset);
        });
        return chart; 
    }

}
