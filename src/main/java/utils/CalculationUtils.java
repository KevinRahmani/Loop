package utils;

import model.beans.ArticleEntity;

import java.util.List;

public class CalculationUtils {

    public static int calculateFidelityPoint(double totalPrice) {
        final double RANGE_50_POINT = 100;
        return (int) (totalPrice / RANGE_50_POINT) * 50;
    }

    public static double calculRevenue(List<ArticleEntity> articleEntityList){
        double revenue = 0;
        for(ArticleEntity article : articleEntityList){
            revenue += article.getSales() * article.getPrix();
        }
        return revenue;
    }

}
