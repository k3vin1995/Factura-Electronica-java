package dashboard;

import gestion.EstudianteGestion;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import model.YearGender;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LineChartModel;

@Named(value = "chartAreaView")
@Dependent
public class ChartAreaView {

    private LineChartModel areaModel;

    public ChartAreaView() {
    }

    public LineChartModel getAreaModel() {
        return areaModel;
    }

    @PostConstruct
    public void init() {
        createAreaModel();
    }

    private void createAreaModel() {
        areaModel = new LineChartModel();

//        LineChartSeries boys = new LineChartSeries();
//        boys.setFill(true);
//        boys.setLabel("Boys");
//        boys.set("2004", 120);
//        boys.set("2005", 100);
//        boys.set("2006", 44);
//        boys.set("2007", 150);
//        boys.set("2008", 25);
//
//        LineChartSeries girls = new LineChartSeries();
//        girls.setFill(true);
//        girls.setLabel("Girls");
//        girls.set("2004", 52);
//        girls.set("2005", 60);
//        girls.set("2006", 110);
//        girls.set("2007", 90);
//        girls.set("2008", 120);
        LineChartSeries boys = new LineChartSeries();
        boys.setFill(true);
        boys.setLabel("Masculino");
        LineChartSeries girls = new LineChartSeries();
        girls.setFill(true);
        girls.setLabel("Femenino");

        ArrayList<YearGender> datos = EstudianteGestion.getIngresosYearGender();
        int mayor = datos.get(0).getTotal();
        for (YearGender linea : datos) {
            if (linea.getGenero().equalsIgnoreCase("M")) {
                boys.set(linea.getYear(), linea.getTotal());
            } else {
                girls.set(linea.getYear(), linea.getTotal());
            }
            if (mayor < linea.getTotal()) {
                mayor = linea.getTotal();
            }

        }

        areaModel.addSeries(boys);
        areaModel.addSeries(girls);

        areaModel.setTitle("Ingresos por año");
        areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);

        Axis xAxis = new CategoryAxis("Años");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Ingresos");
        yAxis.setMin(0);
        yAxis.setMax(300);
    }
}
