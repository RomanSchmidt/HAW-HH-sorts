package sort;

import edu.princeton.cs.algs4.MaxPQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Vector {
    private double[] values;

    public Vector(double[] var) {
        values = new double[var.length];
        System.arraycopy(var, 0, values, 0, var.length);
    }

    // range setzt minimum einer koordinate auf -range und maximum auf range
    // amount gibt an wieviele vektoren in eine datei geschrieben werden.
    // m ist die in der aufgabenstellung geforderte anzahl der nahsten vektoren.
    public static void main(String[] args) throws IOException {
        final double range = 2000;
        final int amount = 500000;
        final int dimensions = 2;
        final int m = 10;
        final String filename = "vectors.txt";

        generateVectors(range, amount, dimensions, filename);

        Scanner scanner = new Scanner(Paths.get(filename), StandardCharsets.UTF_8);
        Vector vector = stringToVector(scanner.nextLine());

        MaxPQ<Vector> vectors = new MaxPQ<>(m + 1, new Comparator<Vector>() {
            public int compare(Vector v1, Vector v2) {
                return Double.compare(v1.distanceTo(vector), v2.distanceTo(vector));
            }
        });

     /*   while(scanner.hasNext()){
            Vector v = parseVector(scanner.nextLine());
            vectors.add(v);
        }
        scanner.close();*/

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            String str = br.readLine();
            while ((str = br.readLine()) != null) {
                Vector v = Vector.stringToVector(str);
                vectors.insert(v);
                if (vectors.size() > m) {
                    vectors.delMax();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The " + m + " nearest vectors to vector v= " + vector.toString());
        for (int i = 0; i < m; i++) {
            Vector current = vectors.delMax();
            assert current != null;
            System.out.println(current.toString() + " - distance: " + current.distanceTo(vector));
        }
    }

    //erzeugt einen Vektor aus einem String
    private static Vector stringToVector(String str) {
        String[] temp = str.split(",");
        double[] vct = new double[temp.length];
        for (int i = 0; i < temp.length; i++) {
            vct[i] = Double.parseDouble(temp[i]);
        }
        return new Vector(vct);
    }

    // methode zum füllen der datei mit vektoren
    private static void generateVectors(double range, int amount, int dimensions, String file) throws IOException {
        Random rand = new Random();
        PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
        for (int i = 0; i < amount; i++) {
            for (int ii = 1; ii < dimensions; ii++) {
                writer.write(Double.toString(rand.nextDouble() * 2 * range - range));
                writer.write(",");
            }
            writer.write(Double.toString(rand.nextDouble() * 2 * range - range));
            writer.write("\n");
        }
        writer.close();
    }

    public String toString() {
        return Arrays.toString(values);
    }

    private int dimension() {
        return values.length;
    }

    // berechnet die Distanz(in double) dieses Vektors zu einem gegebenen
    private double distanceTo(Vector other) {
        if (this.dimension() != other.dimension())
            throw new IllegalArgumentException();

        double result = 0;
        for (int i = 0; i < dimension(); i++) {
            result += Math.pow((this.values[i] - other.values[i]), 2);
        }
        return Math.sqrt(result);
    }
}