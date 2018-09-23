import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Author} from '../entity/author';
import {Observable} from "rxjs";

@Injectable()
export class AuthorService {
  API_URL_AUTHORS = 'http://localhost:8080/api/authors/';


  constructor(private httpClient: HttpClient) {
  }

  getAuthors(): Observable<Author[]> {
    return this.httpClient.get<Author[]>(`${this.API_URL_AUTHORS}`);
  }

  getAuthorById(authorId: string): Observable<Author> {
    return this.httpClient.get<Author>(`${this.API_URL_AUTHORS}` + authorId);
  }

  saveAuthor(author: Author) {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

    this.httpClient.put(
      `${this.API_URL_AUTHORS}`,
      JSON.stringify(author),
      {headers}
    ).subscribe(
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

  remove(authorId: string) {
    this.httpClient.delete(`${this.API_URL_AUTHORS}` + authorId).subscribe(
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
