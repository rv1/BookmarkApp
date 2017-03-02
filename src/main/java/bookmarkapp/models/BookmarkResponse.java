package bookmarkapp.models;

public class BookmarkResponse extends BaseResponse {

    private Bookmark bookmark;

    public Bookmark getBookmark() {
        return bookmark;
    }

    public void setBookmark(Bookmark bookmark) {
        this.bookmark = bookmark;
    }
}
