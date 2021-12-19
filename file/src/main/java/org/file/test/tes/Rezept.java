package org.file.test.tes;

public class Rezept {
    private String name;
    private Zutat[] Zutat;
    private String instructions;
    private Category category;
    private double portions;

    public Rezept(String name, Zutat[] Zutat, String instructions, Category category, double portions) {
        this.name = name;
        this.Zutat = Zutat;
        this.instructions = instructions;
        this.category = category;
        this.portions = portions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Zutat[] getZutat() {
        return Zutat;
    }

    public void setZutat(Zutat[] Zutat) {
        this.Zutat = Zutat;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPortions() {
        return portions;
    }

    public void setPortions(double portions) {
        this.portions = portions;
    }


    public void mengenMultiplizieren(double faktor) {
        System.out.println("faktor:" + faktor);
        this.portions = this.portions * faktor;

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(name + "\n\n");

        stringBuilder.append(String.format("Category: %s\n", category));

        stringBuilder.append(String.format("Zutat for %.1f portions:\n", portions));
        for (Zutat z : Zutat) {
            stringBuilder.append(z.toString() + "\n");
        }
        stringBuilder.append("Preparation:\n");
        stringBuilder.append(instructions + "\n");
        return stringBuilder.toString();
    }

}
