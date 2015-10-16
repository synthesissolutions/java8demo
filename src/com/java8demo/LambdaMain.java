package com.java8demo;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * Created by dsherric on 10/13/15.
 */
public class LambdaMain {
    public static void main(String[] args) {
        List<Movie> movies = Movie.createDemoMovies();

        DoubleFunction<Point2D.Double> parabola = (double x) -> new Point2D.Double(x, x*x);
        DoubleFunction<Point2D.Double> v = (double x) -> new Point2D.Double(x, Math.abs(x));

        System.out.println("Parabola");
        pretendGraphIt(-5.0, 11, 1.0, parabola);

        System.out.println("\nV Shape");
        pretendGraphIt(-5.0, 11, 1.0, v);

        System.out.println("\n X Cubed");
        pretendGraphIt(-5.0, 11, 1.0, (x) -> new Point2D.Double(x, Math.pow(x, 3)));

        System.out.println("\nAnswer 1: " + integrate(x -> x + 10, 3, 7));
    }

    public static void pretendGraphIt(double minX, long increments, double step, DoubleFunction<Point2D.Double> function) {
        DoubleStream xValues = DoubleStream.iterate(minX, x -> x + step);

        // If we were actually graphing these points we wouldn't necessarily even need to keep them all in memory with a list
        // we could draw them into the display or an image file one by one
        List<Point2D.Double> pointsToGraph = xValues.limit(increments).mapToObj(function).collect(Collectors.toList());

        pointsToGraph.forEach(p -> System.out.println("(" + p.getX() + ", " + p.getY() + ")"));
    }

    public static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }
}