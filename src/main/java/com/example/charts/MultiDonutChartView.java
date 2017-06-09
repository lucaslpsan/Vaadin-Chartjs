package com.example.charts;

import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.DonutChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.PieDataset;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.example.AbstractChartView;
import com.example.DemoUtils;
import com.example.ObjetoUnico;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import org.json.JSONArray;

@UIScope
@SpringView
public class MultiDonutChartView extends AbstractChartView {

    private static final long serialVersionUID = 4894923343920891837L;

    ObjetoUnico objetoUnico = (ObjetoUnico) UI.getCurrent().getSession().getAttribute("objetoConjunto");

    @Override
    public Component getChart() {
        DonutChartConfig config = new DonutChartConfig();

        List<String> lbs = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(objetoUnico.getJson());

        for(int i = 0; i < jsonArray.length(); i++){
            lbs.add(jsonArray.getJSONObject(i).get("data").toString());
        }

        config
            .data()
                .labelsAsList(lbs)
                .addDataset(new PieDataset().label(objetoUnico.getDataset()))
                //.addDataset(new PieDataset().label("Dataset 2"))
                //.addDataset(new PieDataset().label("Dataset 3"))
                .and();
        
        config.
            options()
                .responsive(true)
                .title()
                    .display(true)
                    .text(objetoUnico.getTitulo())
                    .and()
                .animation()
                    .animateScale(true)
                    .animateRotate(true)
                    .and()
               .done();
        
        String[] colors = new String[] {"#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360"};
        
        List<String> labels = config.data().getLabels();
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            PieDataset lds = (PieDataset) ds;
            lds.backgroundColor(colors);
            List<Double> data = new ArrayList<>();
            /*for (int i = 0; i < labels.size(); i++) {
                data.add((double) (Math.round(Math.random() * 100)));
            }*/
            for (int i = 0; i < labels.size(); i++) {
                data.add(Double.parseDouble(jsonArray.getJSONObject(i).get("valor").toString()));
            }

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
