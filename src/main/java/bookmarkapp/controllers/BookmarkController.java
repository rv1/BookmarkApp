package bookmarkapp.controllers;

import bookmarkapp.models.Bookmark;
import bookmarkapp.models.BookmarkResponse;
import bookmarkapp.repositories.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/app/bookmarks")
public class BookmarkController {

    private BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @PostMapping
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

    @GetMapping()
    public ResponseEntity<List<Bookmark>> getAllBookmarks() {
        ArrayList<Bookmark> bookmarks = new ArrayList<>();
        bookmarkRepository.findAll().forEach(bookmarks::add);
        return new ResponseEntity(bookmarks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookmark> getBookmarkById(@PathVariable Integer id) {
        Optional<Bookmark> bookmark = bookmarkRepository.findById(id);

        BookmarkResponse response = new BookmarkResponse();
        if (bookmark.isPresent()) {
            response.setBookmark(bookmark.get());
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setError(new Error("Bookmark not found."));
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    private boolean isValid(Bookmark bookmark) {
        return !StringUtils.isEmpty(bookmark.getLink());
    }

}
