package functions;

import java.util.ArrayList;

public class PerigonNews {

    private String status, totalResults;
    private ArrayList<ModelPerigonNews> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<ModelPerigonNews> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ModelPerigonNews> articles) {
        this.articles = articles;
    }
}
