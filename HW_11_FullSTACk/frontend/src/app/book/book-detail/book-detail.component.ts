import { Component, OnInit } from '@angular/core';
import { BookService } from '../../shared/service/book.service';
import { Book } from '../../shared/entity/book';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {

  public book: Book;
  constructor(private route: ActivatedRoute, private bookService: BookService, private router: Router) { }

  ngOnInit() {
    const bookId: string = this.route.snapshot.params['bookId'];

    this.bookService.getBookById(bookId).subscribe(
      data => {
        console.log(data);
        this.book = JSON.parse(JSON.stringify(data)) as Book;
        console.log(this.book);
      }
    );
  }

  backClicked() {
    this.router.navigate(['/']);
  }

  remove() {
    this.bookService.remove(this.book.id);
    this.backClicked();
  }
}
