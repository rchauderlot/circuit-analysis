package es.chauder.circuitanalyzer.gui.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Dimension2D;
import javafx.geometry.Side;
import javafx.scene.chart.ValueAxis;
import javafx.util.StringConverter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * A logarithmic axis implementation for JavaFX 2 charts<br>
 * <br>
 *
 *
 */
public class LogarithmicAxis extends ValueAxis<Number> {


    /** We use these for auto ranging to pick a user friendly tick unit. We handle tick units in the range of 1e-10 to 1e+12 */
    private static final double[] TICK_UNIT_DEFAULTS = {
            1.0E-10d, 2.5E-10d, 5.0E-10d, 1.0E-9d, 2.5E-9d, 5.0E-9d, 1.0E-8d, 2.5E-8d, 5.0E-8d, 1.0E-7d, 2.5E-7d, 5.0E-7d,
            1.0E-6d, 2.5E-6d, 5.0E-6d, 1.0E-5d, 2.5E-5d, 5.0E-5d, 1.0E-4d, 2.5E-4d, 5.0E-4d, 0.0010d, 0.0025d, 0.0050d,
            0.01d, 0.025d, 0.05d, 0.1d, 0.25d, 0.5d, 1.0d, 2.5d, 5.0d, 10.0d, 25.0d, 50.0d, 100.0d, 250.0d, 500.0d,
            1000.0d, 2500.0d, 5000.0d, 10000.0d, 25000.0d, 50000.0d, 100000.0d, 250000.0d, 500000.0d, 1000000.0d,
            2500000.0d, 5000000.0d, 1.0E7d, 2.5E7d, 5.0E7d, 1.0E8d, 2.5E8d, 5.0E8d, 1.0E9d, 2.5E9d, 5.0E9d, 1.0E10d,
            2.5E10d, 5.0E10d, 1.0E11d, 2.5E11d, 5.0E11d, 1.0E12d, 2.5E12d, 5.0E12d
    };
    /** These are matching decimal formatter strings */
    private static final String[] TICK_UNIT_FORMATTER_DEFAULTS = {"0.0000000000", "0.00000000000", "0.0000000000",
            "0.000000000", "0.0000000000", "0.000000000",
            "0.00000000", "0.000000000", "0.00000000",
            "0.0000000", "0.00000000", "0.0000000", "0.000000",
            "0.0000000", "0.000000", "0.00000", "0.000000",
            "0.00000", "0.0000", "0.00000", "0.0000", "0.000",
            "0.0000", "0.000", "0.00", "0.000", "0.00", "0.0",
            "0.00", "0.0", "0", "0.0", "0", "#,##0"};

    private IntegerProperty currentRangeIndexProperty = new SimpleIntegerProperty(this, "currentRangeIndex", -1);
    private DefaultFormatter defaultFormatter = new DefaultFormatter(this);

    // -------------- PUBLIC PROPERTIES --------------------------------------------------------------------------------

    /** When true zero is always included in the visible range. This only has effect if auto-ranging is on. */
    private BooleanProperty forceZeroInRange = new BooleanPropertyBase(true) {
        @Override protected void invalidated() {
            // This will effect layout if we are auto ranging
            if(isAutoRanging()) requestAxisLayout();
        }

        @Override
        public Object getBean() {
            return LogarithmicAxis.this;
        }

        @Override
        public String getName() {
            return "forceZeroInRange";
        }
    };
    public final boolean isForceZeroInRange() { return forceZeroInRange.getValue(); }
    public final void setForceZeroInRange(boolean value) { forceZeroInRange.setValue(value); }
    public final BooleanProperty forceZeroInRangeProperty() { return forceZeroInRange; }


    // -------------- CONSTRUCTORS -------------------------------------------------------------------------------------

    /**
     * Create a auto-ranging LogarithmicAxis
     */
    public LogarithmicAxis() {}

    /**
     * Create a non-auto-ranging LogarithmicAxis with the given upper bound, lower bound and tick unit
     *
     * @param lowerBound The lower bound for this axis, ie min plottable value
     * @param upperBound The upper bound for this axis, ie max plottable value
     */
    public LogarithmicAxis(double lowerBound, double upperBound) {
        super(lowerBound, upperBound);
    }

    /**
     * Create a non-auto-ranging LogarithmicAxis with the given upper bound, lower bound and tick unit
     *
     * @param axisLabel The name to display for this axis
     * @param lowerBound The lower bound for this axis, ie min plottable value
     * @param upperBound The upper bound for this axis, ie max plottable value
     */
    public LogarithmicAxis(String axisLabel, double lowerBound, double upperBound) {
        super(lowerBound, upperBound);
        setLabel(axisLabel);
    }

    // -------------- PROTECTED METHODS --------------------------------------------------------------------------------

    /**
     * Get the string label name for a tick mark with the given value
     *
     * @param value The value to format into a tick label string
     * @return A formatted string for the given value
     */
    @Override protected String getTickMarkLabel(Number value) {
        StringConverter<Number> formatter = getTickLabelFormatter();
        if (formatter == null) formatter = defaultFormatter;
        return formatter.toString(value);
    }

    /**
     * Called to get the current axis range.
     *
     * @return A range object that can be passed to setRange() and calculateTickValues()
     */
    @Override protected Object getRange() {
        return new double[]{
                getLowerBound(),
                getUpperBound(),
                getScale(),
                currentRangeIndexProperty.get()
        };
    }

    /**
     * Called to set the current axis range to the given range. If isAnimating() is true then this method should
     * animate the range to the new range.
     *
     * @param range A range object returned from autoRange()
     * @param animate If true animate the change in range
     */
    @Override protected void setRange(Object range, boolean animate) {
        final double[] rangeProps = (double[]) range;
        final double lowerBound = rangeProps[0];
        final double upperBound = rangeProps[1];
        final double scale = rangeProps[2];
        final double rangeIndex = rangeProps[3];
        currentRangeIndexProperty.set((int)rangeIndex);
        final double oldLowerBound = getLowerBound();
        setLowerBound(lowerBound);
        setUpperBound(upperBound);
        currentLowerBound.set(lowerBound);
        setScale(scale);

    }

    /**
     * Calculate a list of all the data values for each tick mark in range
     *
     * @param length The length of the axis in display units
     * @param range A range object returned from autoRange()
     * @return A list of tick marks that fit along the axis if it was the given length
     */
    @Override
    protected List<Number> calculateTickValues(double length, Object range) {
        List<Number> tickPositions = new ArrayList<Number>();
        if (range != null) {
            double lowerBound = ((double[]) range)[0];
            double upperBound = ((double[]) range)[1];
            double logLowerBound = lowerBound > 0 ? Math.log10(lowerBound * 10) - 1 : 0;
            double logUpperBound = upperBound > 0 ? Math.log10(upperBound * 10) - 1 : 0;

            for (double i = logLowerBound; i <= logUpperBound; i += 1) {
                for (double j = 1; j <= 9; j++) {
                    double value = /*i == 0 && j == 1 ? 0 : */ j * Math.pow(10, i);
                    tickPositions.add(value);
                }
            }
        }
        return tickPositions;
    }

    /**
     * Calculate a list of the data values for every minor tick mark
     *
     * @return List of data values where to draw minor tick marks
     */
    @Override
    protected List<Number> calculateMinorTickMarks() {
        Object range = getRange();
        List<Number> minorTickMarksPositions = new ArrayList<Number>();
        if (range != null) {

            double lowerBound = ((double[]) range)[0];
            double upperBound = ((double[]) range)[1];
            double logLowerBound = lowerBound > 0 ? Math.log10(lowerBound * 10) - 1 : 0;
            double logUpperBound = upperBound > 0 ? Math.log10(upperBound * 10) - 1 : 0;

            int minorTickMarkCount = getMinorTickCount();

            for (double i = logLowerBound; i <= logUpperBound; i += 1) {
                for (double j = 0; j <= 9; j += (1. / minorTickMarkCount)) {
                    double value = /* i == 0 && j == 1 ? 0 : */ j * Math.pow(10, i);
                    minorTickMarksPositions.add(value);
                }
            }
        }
        return minorTickMarksPositions;
    }

    /**
     * Measure the size of the label for given tick mark value. This uses the font that is set for the tick marks
     *
     * @param value tick mark value
     * @param range range to use during calculations
     * @return size of tick mark label for given value
     */
    @Override protected Dimension2D measureTickMarkSize(Number value, Object range) {
        final double[] rangeProps = (double[]) range;
        final double rangeIndex = rangeProps[3];
        return measureTickMarkSize(value, getTickLabelRotation(), (int)rangeIndex);
    }

    /**
     * Measure the size of the label for given tick mark value. This uses the font that is set for the tick marks
     *
     * @param value     tick mark value
     * @param rotation  The text rotation
     * @param rangeIndex The index of the tick unit range
     * @return size of tick mark label for given value
     */
    private Dimension2D measureTickMarkSize(Number value, double rotation, int rangeIndex) {
        String labelText;
        StringConverter<Number> formatter = getTickLabelFormatter();
        if (formatter == null) formatter = defaultFormatter;
        if(formatter instanceof DefaultFormatter) {
            labelText = ((DefaultFormatter)formatter).toString(value, rangeIndex);
        } else {
            labelText = formatter.toString(value);
        }
        return measureTickMarkLabelSize(labelText, rotation);
    }

    /**
     * Called to set the upper and lower bound and anything else that needs to be auto-ranged
     *
     * @param minValue The min data value that needs to be plotted on this axis
     * @param maxValue The max data value that needs to be plotted on this axis
     * @param length The length of the axis in display coordinates
     * @param labelSize The approximate average size a label takes along the axis
     * @return The calculated range
     */
    @Override protected Object autoRange(double minValue, double maxValue, double length, double labelSize) {
        final Side side = getSide();
        final boolean vertical = Side.LEFT.equals(side) || Side.RIGHT.equals(side);
        // check if we need to force zero into range
        if (isForceZeroInRange()) {
            if (maxValue < 0) {
                maxValue = 0;
            } else if (minValue > 0) {
                minValue = 0;
            }
        }

        double minRounded;
        if (minValue == 0) {
            minRounded = 0;
        } else {
            double base = Math.pow(10, Math.floor(Math.log10(minValue * 10) - 1));
            minRounded = (Math.ceil(minValue / base)) * base;
        }

        double maxRounded;

        if (maxValue == 0) {
            maxRounded = 0;
        } else {
            double base = Math.pow(10, Math.floor(Math.log10(maxValue * 10) - 1));
            maxRounded = (Math.ceil(maxValue / base)) * base;
        }



        int rangeIndex = (int) maxRounded;

        // calculate new scale
        final double newScale = calculateNewScale(length, minRounded, maxRounded);
        // return new range
        return new double[]{minRounded, maxRounded, newScale, rangeIndex};
    }


    // -------------- INNER CLASSES ------------------------------------------------------------------------------------

    /**
     * Default number formatter for LogarithmicAxis, this stays in sync with auto-ranging and formats values appropriately.
     * You can wrap this formatter to add prefixes or suffixes;
     * @since JavaFX 2.0
     */
    public static class DefaultFormatter extends StringConverter<Number> {
        private DecimalFormat formatter;
        private String prefix = null;
        private String suffix = null;

        /** used internally */
        private DefaultFormatter() {}

        /**
         * Construct a DefaultFormatter for the given LogarithmicAxis
         *
         * @param axis The axis to format tick marks for
         */
        public DefaultFormatter(final LogarithmicAxis axis) {
            formatter = getFormatter(axis.isAutoRanging()? axis.currentRangeIndexProperty.get() : -1);
            final ChangeListener axisListener = new ChangeListener() {
                @Override public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    formatter = getFormatter(axis.isAutoRanging()? axis.currentRangeIndexProperty.get() : -1);
                }
            };
            axis.currentRangeIndexProperty.addListener(axisListener);
            axis.autoRangingProperty().addListener(axisListener);
        }

        /**
         * Construct a DefaultFormatter for the given LogarithmicAxis with a prefix and/or suffix.
         *
         * @param axis The axis to format tick marks for
         * @param prefix The prefix to append to the start of formatted number, can be null if not needed
         * @param suffix The suffix to append to the end of formatted number, can be null if not needed
         */
        public DefaultFormatter(LogarithmicAxis axis, String prefix, String suffix) {
            this(axis);
            this.prefix = prefix;
            this.suffix = suffix;
        }

        private static DecimalFormat getFormatter(int rangeIndex) {
            if (rangeIndex < 0) {
                return new DecimalFormat();
            } else if(rangeIndex >= TICK_UNIT_FORMATTER_DEFAULTS.length) {
                return new DecimalFormat(TICK_UNIT_FORMATTER_DEFAULTS[TICK_UNIT_FORMATTER_DEFAULTS.length-1]);
            } else {
                return new DecimalFormat(TICK_UNIT_FORMATTER_DEFAULTS[rangeIndex]);
            }
        }

        /**
         * Converts the object provided into its string form.
         * Format of the returned string is defined by this converter.
         * @return a string representation of the object passed in.
         * @see StringConverter#toString
         */
        @Override public String toString(Number object) {
            return toString(object, formatter);
        }

        private String toString(Number object, int rangeIndex) {
            return toString(object, getFormatter(rangeIndex));
        }

        private String toString(Number object, DecimalFormat formatter) {
            if (prefix != null && suffix != null) {
                return prefix + formatter.format(object) + suffix;
            } else if (prefix != null) {
                return prefix + formatter.format(object);
            } else if (suffix != null) {
                return formatter.format(object) + suffix;
            } else {
                return formatter.format(object);
            }
        }

        /**
         * Converts the string provided into a Number defined by the this converter.
         * Format of the string and type of the resulting object is defined by this converter.
         * @return a Number representation of the string passed in.
         * @see StringConverter#toString
         */
        @Override public Number fromString(String string) {
            try {
                int prefixLength = (prefix == null)? 0: prefix.length();
                int suffixLength = (suffix == null)? 0: suffix.length();
                return formatter.parse(string.substring(prefixLength, string.length() - suffixLength));
            } catch (ParseException e) {
                return null;
            }
        }
    }


    @Override
    public Number getValueForDisplay(double displayPosition) {

        Object range = getRange();
        double lowerBound = ((double[])range)[0];
        double upperBound = ((double[])range)[1];
        double logLowerBound = lowerBound > 0 ? Math.log10(lowerBound * 10) - 1 : 0;
        double logUpperBound = upperBound > 0 ? Math.log10(upperBound * 10) - 1 : 0;

        double delta = logUpperBound - logLowerBound;
        if (getSide().isVertical()) {
            return Math.pow(10, (((displayPosition - getHeight()) / -getHeight()) * delta) + logLowerBound);
        } else {
            return Math.pow(10, (((displayPosition / getWidth()) * delta) + logLowerBound));
        }
    }

    @Override
    public double getDisplayPosition(Number value) {

        Object range = getRange();
        double lowerBound = ((double[])range)[0];
        double upperBound = ((double[])range)[1];
        double logLowerBound = lowerBound > 0 ? Math.log10(lowerBound * 10) - 1 : 0;
        double logUpperBound = upperBound > 0 ? Math.log10(upperBound * 10) - 1 : 0;

        double delta = logUpperBound - logLowerBound;
        double doubleValue = value.doubleValue();
        double deltaV = doubleValue > 0 ? ( Math.log10(doubleValue * 10) - 1 ) - logLowerBound : 0;
        if (getSide().isVertical()) {
            return (1. - ((deltaV) / delta)) * getHeight();
        } else {
            return ((deltaV) / delta) * getWidth();
        }
    }
}