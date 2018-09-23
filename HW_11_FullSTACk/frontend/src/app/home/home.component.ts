import { Component, OnInit } from '@angular/core';
import { Book } from '../shared/entity/book';
import { BookService } from '../shared/service/book.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public books: Book[];

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.bookService.getBooks().subscribe(
      data => {
        this.books = JSON.parse(JSON.stringify(data)) as Book[];
      });
  }
}
