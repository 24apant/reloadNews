package functions;

import java.util.HashMap;

public class ModelNews {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private int upVoteCt;
    private int downVoteCt;
    private int commentCt;

    public ModelNews() {
    }

    @Override
    public String toString() {
        return "ModelNews{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", upVoteCt='" + upVoteCt + '\'' +
                ", downVoteCt='" + downVoteCt + '\'' +
                ", commentCt='" + commentCt + '\'' +
                '}';
    }

    public ModelNews(String author, String title, String description, String url, String urlToImage, String publishedAt, int upVoteCt, int downVoteCt, int commentCt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.upVoteCt = upVoteCt;
        this.downVoteCt = downVoteCt;
        this.commentCt = commentCt;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getUpVoteCt() {
        return upVoteCt;
    }

    public void setUpVoteCt(int upVoteCt) {
        this.upVoteCt = upVoteCt;
    }

    public int getDownVoteCt() {
        return downVoteCt;
    }

    public void setDownVoteCt(int downVoteCt) {
        this.downVoteCt = downVoteCt;
    }

    public int getCommentCt() {
        return commentCt;
    }

    public void setCommentCt(int commentCt) {
        this.commentCt = commentCt;
    }
}
