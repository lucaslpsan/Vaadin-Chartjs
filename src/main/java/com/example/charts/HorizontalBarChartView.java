package com.example.charts;

import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.example.AbstractChartView;
import com.example.BancoService;
import com.example.DemoUtils;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.elements.Rectangle.RectangleEdge;
import com.example.ObjetoUnico;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringView
public class HorizontalBarChartView extends AbstractChartView {

    private static final long serialVersionUID = -3541750814970379914L;

    ObjetoUnico objetoUnico = (ObjetoUnico) UI.getCurrent().getSession().getAttribute("objetoConjunto");

    @Override
    public Component getChart() {
        BarChartConfig barConfig = new BarChartConfig();

        List<String> lbs = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(objetoUnico.getJson());

        for(int i = 0; i < jsonArray.length(); i++){
            lbs.add(jsonArray.getJSONObject(i).get("data").toString());
        }

        barConfig.horizontal();
        barConfig.
            data()
                .labelsAsList(lbs)
                .addDataset(new BarDataset().backgroundColor("rgba(220,220,220,0.5)").label(objetoUnico.getDataset()))
                .and()
            .options()
                .responsive(true)
                .title()
                    .display(true)
                    .text(objetoUnico.getTitulo())
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
                data.add(Double.parseDouble(jsonArray.getJSONObject(i).get("valor").toString()));
            }
            lds.dataAsList(data);
            lds.borderColor(ColorUtils.randomColor(0.3));
            lds.backgroundColor(ColorUtils.randomColor(0.5));
        }
        
        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        chart.addClickListener((a,b) -> {            BarDataset dataset = (BarDataset) barConfig.data().getDatasets().get(a);
            DemoUtils.notification(a, b, dataset);
        });
        return chart; 
    }

}
