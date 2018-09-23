import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from '../../shared/entity/book';


@Injectable()
export class BookService {
  API_URL_BOOK = 'http://localhost:8080/api/books/';

  constructor(private httpClient: HttpClient) { }

  getBooks() {
    return this.httpClient.get(`${this.API_URL_BOOK}`);
  }

  getBookById(bookId: string) {
    return this.httpClient.get(`${this.API_URL_BOOK}` + bookId);
  }

  saveBook(book: Book) {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

    this.httpClient.put(`${this.API_URL_BOOK}`, JSON.stringify(book), { headers })
      .subscribe(
        val => {
          console.log('PUT call successful value returned in body',
            val);
        },
        response => {
          console.log('PUT call in error', response);
        },
        () => {
          console.log('The PUT observable is now completed.');
        }
      );
  }

  remove(id: string) {
    this.httpClient.delete(`${this.API_URL_BOOK}` + id)
      .subscribe(
        val => {
          console.log('DELETE call successful value returned in body',
            val);
        },
        response => {
          console.log('DELETE call in error', response);
        },
        () => {
          console.log('The DELETE observable is now completed.');
        }
      );
  }
}
