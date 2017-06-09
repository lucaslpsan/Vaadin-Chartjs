package com.example.charts;

import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.example.*;
import com.byteowls.vaadin.chartjs.options.InteractionMode;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.CategoryScale;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringView
public class SimpleLineChartView extends AbstractChartView {

    private static final long serialVersionUID = -1625380456901210625L;

    ObjetoUnico objetoUnico = (ObjetoUnico) UI.getCurrent().getSession().getAttribute("objetoConjunto");

    @Override
    public Component getChart() {
        LineChartConfig lineConfig = new LineChartConfig();

        //String ls = UI.getCurrent().getSession().getAttribute("idConjunto").toString();
        //int seqConjunto = Integer.parseInt(UI.getCurrent().getSession().getAttribute("idConjunto").toString());

        List<String> lbs = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(objetoUnico.getJson());

        for(int i = 0; i < jsonArray.length(); i++){
            lbs.add(jsonArray.getJSONObject(i).get("data").toString());
        }


        lineConfig.data()
            //.labels("01/01/1991", "01/02/1991", "01/03/1991", "01/04/1991", "01/05/1991", "01/06/1991", "01/07/1991")
            .labelsAsList(lbs)
            .addDataset(new LineDataset().label(objetoUnico.getDataset()).fill(false))
            //.addDataset(new LineDataset().label("My Second dataset").fill(false))
            //.addDataset(new LineDataset().label("Hidden dataset").hidden(true))
            .and()
        .options()
            .responsive(true)
            .title()
            .display(true)
            .text(objetoUnico.getTitulo())
            .and()
        .tooltips()
            .mode(InteractionMode.INDEX)
            .intersect(false)
            .and()
        .hover()
            .mode(InteractionMode.NEAREST)
            .intersect(true)
            .and()
        .scales()
        .add(Axis.X, new CategoryScale()
                .display(true)
                .scaleLabel()
                    .display(true)
                    .labelString("Data")
                    .and()
                .position(Position.BOTTOM))
        .add(Axis.Y, new LinearScale()
                .display(true)
                .scaleLabel()
                    .display(true)
                    .labelString("Valor")
                    .and()
                .ticks()
                    .suggestedMin(0)
                    .suggestedMax(10)
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
        chart.addClickListener((a,b) -> {
            LineDataset dataset = (LineDataset) lineConfig.data().getDatasets().get(a);
            DemoUtils.notification(a, b, dataset);
        });
        chart.setJsLoggingEnabled(true);
        return chart;
    }

}
