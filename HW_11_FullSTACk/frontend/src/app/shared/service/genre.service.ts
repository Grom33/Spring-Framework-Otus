import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Genre} from '../entity/genre';


@Injectable()
export class GenreService {

  API_URL_GENRE = 'http://localhost:8080/api/genres/';

  constructor(private httpClient: HttpClient) {
  }

  getGenres() {
    return this.httpClient.get(`${this.API_URL_GENRE}`);
  }

  getGenreById(genreId: string) {
    return this.httpClient.get(`${this.API_URL_GENRE}` + genreId);
  }

  saveGenre(genre: Genre) {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

    this.httpClient.put(
      `${this.API_URL_GENRE}`,
      JSON.stringify(genre),
      {headers})
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

  remove(genreId: string) {
    this.httpClient.delete(`${this.API_URL_GENRE} + genreId`).subscribe(
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
}
