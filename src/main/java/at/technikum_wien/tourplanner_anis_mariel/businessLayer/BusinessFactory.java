package at.technikum_wien.tourplanner_anis_mariel.businessLayer;

public class BusinessFactory {
    private static IBusinessLayer business;

    public static  IBusinessLayer getBusiness() {
        if (business == null){
            business = new BusinessImplementation();
        }
        return business;
    }
}

