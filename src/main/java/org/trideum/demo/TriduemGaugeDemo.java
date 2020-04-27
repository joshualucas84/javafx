package org.trideum.demo;


import eu.hansolo.medusa.*;
import eu.hansolo.medusa.Gauge.*;
import eu.hansolo.medusa.Marker.MarkerType;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;


/**
 * This demo shows the usage of the standard Medusa gauge
 */
public class TriduemGaugeDemo extends Application {
    private static final Random RND = new Random();
    private              Gauge  gauge;
    private              FGauge fGauge;
    private              Button button;

    @Override public void init() {
        gauge =
            GaugeBuilder.create()
                      .customFont(Font.loadFont( getClass().getResource
                          ("/css/BebasNeue.otf").toExternalForm(), 13))
                        .foregroundBaseColor(Color.WHITE)                                                // Color for title, subtitle, unit, value, tick label, zeroColor, tick mark, major tick mark, medium tick mark and minor tick mark
                        .valueColor(Color.WHITE)                                                         // Color for value text
                        .decimals(0)
                        .lcdVisible(false)                                                         // Number of decimals for the value/lcd text
                        .scaleDirection(ScaleDirection.CLOCKWISE)                                        // Direction of Scale (CLOCKWISE, COUNTER_CLOCKWISE)
                        .minValue(0)                                                                     // Start value of Scale
                        .maxValue(9)                                                                   // End value of Scale
                        .startAngle(320)                                                                 // Start angle of Scale (bottom -> 0, direction -> CCW)
                        .angleRange(270)                                                                 // Angle range of Scale starting from the start angle
                        .majorTickMarksVisible(true)                                                     // Should major tick marks be visible
                        .majorTickMarkType(TickMarkType.LINE)                                            // Tick mark type for major tick marks (LINE, DOT, TRAPEZOID, TICK_LABEL)
                        .majorTickMarkColor(Color.WHITE)                                                 // Color for major tick marks (overriden by tick mark sections)
                        .mediumTickMarksVisible(true)                                                    // Should medium tick marks be visible
                         .mediumTickMarkType(TickMarkType.LINE)                                           // Tick mark type for medium tick marks (LINE, DOT, TRAPEZOID)
                         .mediumTickMarkColor(Color.WHITE)                                                // Color for medium tick marks (overriden by tick mark sections)
                        .minorTickMarksVisible(false)                                                     // Should minor tick marks be visible
                        .minorTickMarkType(TickMarkType.LINE)                                            // Tick mark type for minor tick marks (LINE, DOT, TRAPEZOID)
                        .minorTickMarkColor(Color.WHITE)                                                 // Color for minor tick marks (override by tick mark sections)
                        .valueVisible(false)
                         .needleType(NeedleType.VARIOMETER)
                        .needleShape(NeedleShape.ANGLED)                                                 // Shape of needle (ANGLED, ROUND, FLAT)
                        .needleSize(NeedleSize.STANDARD)                                                 // Size of needle (THIN, STANDARD, THICK)
                        .needleColor(Color.CRIMSON)
                         .innerShadowEnabled(true)
                        .startFromZero(false)                                                            // Should needle start from the 0 value
                        .returnToZero(false)                                                             // Should needle return to the 0 value (only makes sense when animated==true)
                        .animated(true)                                                                 // Should needle be animated
                        .animationDuration(500)                                                          // Speed of needle in milliseconds (10 - 10000 ms)
                        .build();



        fGauge = FGaugeBuilder
                .create()
                .prefSize(237, 237)
                .gauge(gauge)
                 .gaugeDesign(GaugeDesign.METAL)
                .gaugeBackground(GaugeDesign.GaugeBackground.BLACK)
                .foregroundVisible(true)
                .build();

        button = new Button("Toggle Value");
        button.setOnMousePressed(event -> gauge.setValue(RND.nextDouble() * gauge.getRange() + gauge.getMinValue()));
    }

    @Override public void start(Stage stage) {
        HBox pane = new HBox(fGauge, button);
        pane.setPadding(new Insets(10));
        pane.setSpacing(20);

        Scene scene = new Scene(pane);

        stage.setTitle(" Trideum Gauge Demo");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
