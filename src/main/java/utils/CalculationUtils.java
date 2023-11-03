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

    public static double calculateSimilarity(String category, String valueResearch) {
        int maxLength = Math.max(category.length(), valueResearch.length());
        int similarity = 0;

        if (maxLength > 0) {
            similarity = (int) (100.0 * (1.0 - (double) calculateLevenshteinDistance(category, valueResearch) / maxLength));
        }
        return similarity;
    }

    public static int calculateLevenshteinDistance(String a, String b) {
        int[][] distance = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            distance[i][0] = i;
        }
        for (int j = 1; j <= b.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1), distance[i - 1][j - 1] + cost);
            }
        }
        return distance[a.length()][b.length()];
    }

}
