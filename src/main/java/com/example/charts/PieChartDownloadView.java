//package com.byteowls.vaadin.chartjs.demo.ui.charts;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.byteowls.vaadin.chartjs.ChartJs;
//import com.byteowls.vaadin.chartjs.config.ChartConfig;
//import com.byteowls.vaadin.chartjs.config.PieChartConfig;
//import com.byteowls.vaadin.chartjs.data.Dataset;
//import com.byteowls.vaadin.chartjs.data.PieDataset;
//import com.byteowls.vaadin.chartjs.demo.ui.AbstractChartView;
//import com.byteowls.vaadin.chartjs.demo.ui.ChartUtils;
//import com.vaadin.server.FontAwesome;
//import com.vaadin.spring.annotation.SpringView;
//import com.vaadin.spring.annotation.UIScope;
//import com.vaadin.ui.Alignment;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.Component;
//import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.Notification;
//import com.vaadin.ui.Notification.Type;
//import com.vaadin.ui.VerticalLayout;
//
//@UIScope
//@SpringView
//public class PieChartDownloadView extends AbstractChartView {
//
//    private static final long serialVersionUID = 4894923343920891837L;
//
//    @Override
//    public Component getChart() {
//        PieChartConfig config = new PieChartConfig();
//        config
//        .data()
//        .labels("Red", "Green", "Yellow", "Grey", "Dark Grey")
//        .addDataset(new PieDataset().label("Dataset 1"))
//        .and();
//
//        config.
//        options()
//        .responsive(true)
//        .title()
//        .display(true)
//        .text("Chart.js Pie Chart (Data Refresh)")
//        .and()
//        .animation()
//        //.animateScale(true)
//        .animateRotate(true)
//        .and()
//        .done();
//
//        ChartJs chart = new ChartJs(config);
//        chart.setJsLoggingEnabled(true);
//        chart.addClickListener((a,b) -> {
//            PieDataset dataset = (PieDataset) config.data().getDatasets().get(a);
//            ChartUtils.notification(a, b, dataset);
//        });
//        refreshChartData(chart);
//        chart.setWidth(100, Unit.PERCENTAGE);
//
//        Button downloadButton = new Button("Download as PNG", FontAwesome.DOWNLOAD);
//        downloadButton.addClickListener(e -> chart.download());
//        chart.addDownloadListener(imageData -> {
//            Notification.show("Download stored in DataBox and is now ready for download", Type.HUMANIZED_MESSAGE);
//        });
//
//        Button refreshButton = new Button("Refresh Data", FontAwesome.REFRESH);
//        refreshButton.addClickListener(e -> refreshChartData(chart));
//
//        HorizontalLayout btnWrapper = new HorizontalLayout(refreshButton, downloadButton);
//        btnWrapper.setSpacing(true);
//
//        VerticalLayout layout = new VerticalLayout();
//        layout.setSizeFull();
//        layout.addComponent(btnWrapper);
//        layout.addComponent(chart);
//        layout.setComponentAlignment(btnWrapper, Alignment.TOP_CENTER);
//        layout.setComponentAlignment(chart, Alignment.MIDDLE_CENTER);
//        layout.setExpandRatio(chart, 5);
//        return layout; 
//    }
//
//    protected void refreshChartData(ChartJs chart) {
//        generateRandomData(chart.getConfig());
//        chart.refreshData();
//    }
//
//    private void generateRandomData(ChartConfig chartConfig) {
//        PieChartConfig config = (PieChartConfig) chartConfig;
//        List<String> labels = config.data().getLabels();
//        for (Dataset<?, ?> ds : config.data().getDatasets()) {
//            PieDataset lds = (PieDataset) ds;
//            List<Double> data = new ArrayList<>();
//            List<String> colors = new ArrayList<>();
//            for (int i = 0; i < labels.size(); i++) {
//                data.add((double) (Math.round(Math.random() * 100)));
//                colors.add(ChartUtils.randomColor(0.7));
//            }
//            lds.backgroundColor(colors.toArray(new String[colors.size()]));
//            lds.dataAsList(data);
//        }
//    }
//
//}
