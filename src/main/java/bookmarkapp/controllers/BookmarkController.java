package bookmarkapp.controllers;

import bookmarkapp.models.Bookmark;
import bookmarkapp.models.BookmarkResponse;
import bookmarkapp.repositories.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/bookmark")
public class BookmarkController {

    private BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<BookmarkResponse> addBookmark(@RequestBody Bookmark bookmark) {
        BookmarkResponse response = new BookmarkResponse();
        if (isValid(bookmark)) {
            Bookmark savedBookmark = bookmarkRepository.save(bookmark);
            response.setBookmark(savedBookmark);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setError(new Error("Body cannot be empty."));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Bookmark>> getAllBookmarks() {
        ArrayList<Bookmark> bookmarks = new ArrayList<>();
        bookmarkRepository.findAll().forEach(bookmarks::add);
        return new ResponseEntity(bookmarks, HttpStatus.OK);
    }

    private boolean isValid(Bookmark bookmark) {
        return !StringUtils.isEmpty(bookmark.getLink());
    }

}
