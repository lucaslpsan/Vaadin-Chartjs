package com.example.charts;

import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.example.AbstractChartView;
import com.example.BancoService;
import com.example.DemoUtils;
import com.byteowls.vaadin.chartjs.options.InteractionMode;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.CategoryScale;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.example.ObjetoUnico;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringView
public class StackedLineChartView extends AbstractChartView {

    private static final long serialVersionUID = -9142435970343490721L;

    ObjetoUnico objetoUnico = (ObjetoUnico) UI.getCurrent().getSession().getAttribute("objetoConjunto");

    @Override
    public Component getChart() {
        LineChartConfig lineConfig = new LineChartConfig();

        List<String> lbs = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(objetoUnico.getJson());

        for(int i = 0; i < jsonArray.length(); i++){
            lbs.add(jsonArray.getJSONObject(i).get("data").toString());
        }

        lineConfig.data()
            .labelsAsList(lbs)
            .addDataset(new LineDataset().label(objetoUnico.getDataset()).borderColor(DemoUtils.RGB_RED).backgroundColor(DemoUtils.RGB_RED))
            .and()
        .options()
            .responsive(true)
            .title()
                .display(true)
                .text(objetoUnico.getTitulo())
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
                        .labelString("Data")
                        .and()
                    .position(Position.BOTTOM))
            .add(Axis.Y, new LinearScale()
                    .stacked(true)
                    .scaleLabel()
                        .display(true)
                        .labelString("Valor")
                        .and()
                    .position(Position.RIGHT))
            .and()
        .done();
        
        // add random data for demo
        List<String> labels = lineConfig.data().getLabels();
        for (Dataset<?, ?> ds : lineConfig.data().getDatasets()) {
            LineDataset lds = (LineDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add(Double.parseDouble(jsonArray.getJSONObject(i).get("valor").toString()));
            }
            lds.dataAsList(data);
            lds.borderColor(ColorUtils.randomColor(0.3));
            lds.backgroundColor(ColorUtils.randomColor(0.5));
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
