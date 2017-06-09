package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class MybatisSpringVaadinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisSpringVaadinApplication.class, args);
	}
/*
	@Service
	public static class MyService {

		public String sayHi() {
			return "Hello Spring Initializr!";
		}

	}
*/
	/* Vaadin UI class.
     * Specify the theme and URI path for the
     * web application.
     */
	@Theme("valo")
	@SpringUI(path = "")
	public static class VaadinUI extends UI {

		// You can easily autowire the services to you
		// Vaadin applications
		//@Autowired
		//MyService myService;

		@Autowired
		BancoService bancoService;

		@Override
		protected void init(VaadinRequest request) {
			//setContent(new Label(myService.sayHi() + " Iago bobo"));
			final FormLayout layout = new FormLayout();
			layout.setMargin(true);
			layout.addStyleName("outlined");
			layout.setSizeFull();

			Label label = new Label( "SiT - PI 2 " + " Nada acontece Feijoada!");

			ComboBox combobox = new ComboBox("Selecione o orgão :");

			String resultNomeOrgao;

			//Lucas, inseri o combobox aqui
			combobox.setInputPrompt("Selecione");//"Banco Central do Brasil");
			//combobox.addItem("Selecione");
			combobox.addItems(bancoService.findNomeOrgao());
			combobox.setWidth(65, Unit.PERCENTAGE);
			combobox.setNullSelectionAllowed(false);
			//combobox.addValueChangeListener(valueChangeEvent -> Notification.show("Escolheu ", Notification.Type.TRAY_NOTIFICATION));

			ComboBox combobox2 = new ComboBox("Selecione o conjunto de dados :");
			combobox2.setInputPrompt("Selecione");
			combobox2.setWidth(65, Unit.PERCENTAGE);
			combobox2.setNullSelectionAllowed(false);
			combobox2.setTextInputAllowed(false);

            CheckBox estiloGrafico = new CheckBox("Nova aba", true);

            Button confirma = new Button("OK");
            confirma.setDescription("Confirmar");
            confirma.setEnabled(false);

			String[] escolhe = new String[2];


			combobox.addValueChangeListener(new Property.ValueChangeListener() {
				@Override
				public void valueChange(Property.ValueChangeEvent event) {
					Notification.show("Escolheu ", event.getProperty().getValue().toString(), Notification.Type.TRAY_NOTIFICATION);

					escolhe[0] = null;
					escolhe[1] = null;

					escolhe[0] = event.getProperty().getValue().toString();

					combobox2.setTextInputAllowed(true);
					combobox2.removeAllItems();
					combobox2.addItems(bancoService.findNameConjunto(bancoService.findIdOrgaoByName(escolhe[0])));

					if(combobox2.isEmpty()) {
                        combobox2.setTextInputAllowed(false);
                        confirma.setEnabled(false);
					}
				}
			});

			combobox2.addValueChangeListener(new Property.ValueChangeListener() {
				@Override
				public void valueChange(Property.ValueChangeEvent event) {
					escolhe[1] = event.getProperty().getValue().toString();
					confirma.setEnabled(true);
				}
			});



			confirma.addClickListener(new Button.ClickListener() {
				@Override
				public void buttonClick(Button.ClickEvent clickEvent) {
					//Notification.show("Conjunto do ", escolhe[0] + ", Conjunto: " + escolhe[1], Notification.Type.TRAY_NOTIFICATION);

                	int seq = bancoService.findIdConjuntoByName(escolhe[1]);

//					Notification.show("", Integer.toString(bancoService.findIdConjuntoByName(escolhe[1])), Notification.Type.TRAY_NOTIFICATION);

					getSession().setAttribute("idConjunto", seq);
					getSession().setAttribute("nomeConjunto", escolhe[1]);

                    Notification.show("Aqui ", getSession().getAttribute("idConjunto").toString(), Notification.Type.TRAY_NOTIFICATION);

					if(estiloGrafico.getValue() == true) {
						BrowserWindowOpener opener = new BrowserWindowOpener(new ExternalResource("/graficos?id=" + escolhe[1]));
						opener.setFeatures("");
						opener.extend(confirma);
					}else {
						getUI().getPage().setLocation("/graficos?id=" + escolhe[1]);
					}
				}
			});

			layout.addComponents(label, combobox, combobox2, estiloGrafico, confirma);

			HorizontalLayout horizontalLayout = new HorizontalLayout();

			Image imagemJava = new Image();
			imagemJava.setSource(new ThemeResource("../imagens/java.png"));
            Image imagemVaadin = new Image();
            imagemVaadin.setSource(new ThemeResource("../imagens/vaadin.png"));
            Image imagemChartJs = new Image();
            imagemChartJs.setSource(new ThemeResource("../imagens/chartjs.svg"));
            Image imagemOpenSource = new Image();
            imagemOpenSource.setSource(new ThemeResource("../imagens/open-source.svg"));
            Label labelInfo = new Label("<strong>ChartJS for Vaadin</strong> by Michael Oberwasserlechner");
            labelInfo.setContentMode(ContentMode.HTML);

			horizontalLayout.addComponents(imagemJava, imagemVaadin, imagemChartJs, labelInfo, imagemOpenSource);
			horizontalLayout.setComponentAlignment(imagemVaadin, Alignment.MIDDLE_RIGHT);
            horizontalLayout.setComponentAlignment(labelInfo, Alignment.BOTTOM_CENTER);
            horizontalLayout.setComponentAlignment(imagemOpenSource, Alignment.BOTTOM_CENTER);
            //horizontalLayout.setComponentAlignment(imagemChartJs, Alignment.BOTTOM_RIGHT);
			layout.addComponent(horizontalLayout);

			setContent(layout);
		}



	}
}
