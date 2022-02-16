import java.util.Scanner;

public class Q1 {
    public static int refuelling(int distance, int fullTankDistance, int numOfGasStations, int [] gasStations){
        int numOfRefills = 0;
        int distanceInTank = fullTankDistance;
        int distanceTravelled = 0;
        gasStations[numOfGasStations] = distance;
        for (int i = 0; i < numOfGasStations; ++i) {
            if (fullTankDistance + distanceTravelled < gasStations[i]) {
                // Not possible to reach next gas station
                return -1;
            }
            if (distanceInTank + distanceTravelled >= gasStations[i + 1]) {
                // Don't need to fill up at next gas station. Can reach the one after
                distanceInTank -= gasStations[i] - distanceTravelled;
            } else {
                // Need to fill up here
                distanceInTank = fullTankDistance;
                ++numOfRefills;
            }
            distanceTravelled = gasStations[i];
        }
        return numOfRefills;
    }

    public static void main (String[] args) {

        /* This first test is the expected behaviour from the program.
         * Since 1000 - 625 > 250, it's not possible to reach the next gas station,
         * and therefore not possible to reach the destination with that vehicle.
         * Test Case 1:
         * d = 1050, m = 250, n = 4, stops = [200, 450, 625, 1000], should return -1
         */ 
        System.out.println("Test Case 1:\nEnter distance: 1050\nEnter fullTankDistance: 250\nEnter numOfGasStations: 4\nEnter gasStations: 200 450 625 1000");
        System.out.println("Minimum number of refills: " + refuelling(1050, 250, 4, new int[]{200, 450, 625, 1000, 0}) + "\n");

        /* The second test is to show the worst case for the program.
         * Since its possible to reach the destination, the program needs to loop n times.
         * Just for reference, the best case is when the first gas station is too far for the vehicle to make it.
         * Test Case 2:
         * d = 800, m = 150, n = 7, stops = [150, 250, 305, 405, 490, 600, 725], should return 7
         */ 
        System.out.println("Worst Case:\nTest Case 2:\nEnter distance: 800\nEnter fullTankDistance: 150\nEnter numOfGasStations: 7\nEnter gasStations: 150 250 305 405 490 600 725");
        System.out.println("Minimum number of refills: " + refuelling(800, 150, 7, new int[]{150, 250, 305, 405, 490, 600, 725, 0}) + "\n");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter distance: ");
        final int distance /* d */ = input.nextInt();
        System.out.print("Enter fullTankDistance: ");
        final int fullTankDistance /* m */ = input.nextInt();
        System.out.print("Enter numOfGasStations: ");
        final int numOfGasStations /* n */ = input.nextInt();
        int [] gasStations = new int[numOfGasStations + 1];
        System.out.print("Enter gasStations: ");
        for (int i = 0; i < numOfGasStations; ++i){
            gasStations[i] = input.nextInt();
        }

        System.out.println("Minimum number of refills: " + refuelling(distance, fullTankDistance, numOfGasStations, gasStations));
    }
}
