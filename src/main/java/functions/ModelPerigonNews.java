package functions;

public class ModelPerigonNews {

    private String authorsByline;
    private String title;
    private String content;
    private String url;
    private String imageUrl;
    private String pubDate;
    private int upVoteCt;
    private int downVoteCt;
    private int commentCt;

    public ModelPerigonNews() {
    }

    @Override
    public String toString() {
        return "ModelNews{" +
                "author='" + authorsByline + '\'' +
                ", title='" + title + '\'' +
                ", description='" + content + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + imageUrl + '\'' +
                ", publishedAt='" + pubDate + '\'' +
                ", upVoteCt='" + upVoteCt + '\'' +
                ", downVoteCt='" + downVoteCt + '\'' +
                ", commentCt='" + commentCt + '\'' +
                '}';
    }

    public ModelPerigonNews(String authorsByline, String title, String content, String url, String imageUrl, String pubDate, int upVoteCt, int downVoteCt, int commentCt) {
        this.authorsByline = authorsByline;
        this.title = title;
        this.content = content;
        this.url = url;
        this.imageUrl = imageUrl;
        this.pubDate = pubDate;
        this.upVoteCt = upVoteCt;
        this.downVoteCt = downVoteCt;
        this.commentCt = commentCt;
    }



    public String getAuthorsByline() {
        return authorsByline;
    }

    public void setAuthorsByline(String authorsByline) {
        this.authorsByline = authorsByline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
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
