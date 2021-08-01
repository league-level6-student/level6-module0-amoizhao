package _07_intro_to_mocking.models;

public class GasTank {

    double fuelLevelGallons = 12;

    public boolean fill(int octaneGrade){
    	System.out.println("fill");
        return true;
    }

    public double getFuelLevel() {
        return this.fuelLevelGallons;
    }

}
