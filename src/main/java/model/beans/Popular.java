package model.beans;

public class Popular {

    public String categorie;
    public String citation;

    public ArticleEntity article;

    public Popular(String categorie, String citation, ArticleEntity article) {
        this.categorie = categorie;
        this.citation = citation;
        this.article = article;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }
}
