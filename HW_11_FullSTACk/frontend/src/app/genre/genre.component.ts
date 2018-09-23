import {Component, OnInit} from '@angular/core';
import {Genre} from '../shared/entity/genre';
import {GenreService} from '../shared/service/genre.service';

@Component({
  selector: 'app-genre',
  templateUrl: './genre.component.html',
  styleUrls: ['./genre.component.css']
})
export class GenreComponent implements OnInit {
  genres: Genre[] = [];
  genreCount: number;

  constructor(private genreService: GenreService) {
  }

  ngOnInit() {
    this.genreService.getGenres().subscribe(
      (data: Genre[]) => {
        this.genres = data;
        this.genreCount = this.genres.length;
      });
  }

}
