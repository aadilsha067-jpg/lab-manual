class ParkingSystem {
    private int[] count;

    public ParkingSystem(int big, int medium, int small) {
        // Index 1 = big, Index 2 = medium, Index 3 = small
        count = new int[]{0, big, medium, small};
    }
    
    public boolean addCar(int carType) {
        if (count[carType] > 0) {
            count[carType]--;
            return true;
        }
        return false;
    }
}