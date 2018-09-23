import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Genre } from '../../shared/entity/genre';
import { GenreService } from '../../shared/service/genre.service';

@Component({
  selector: 'app-genre-edit',
  templateUrl: './genre-edit.component.html',
  styleUrls: ['./genre-edit.component.css']
})
export class GenreEditComponent implements OnInit {
  public genre: Genre;
  private genreId: string;

  constructor(private genreService: GenreService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.genreId = this.route.snapshot.paramMap.get('genreId');
    if (Number(this.genreId) === 0) {
      this.genre = new Genre();
    } else {
    this.genreService.getGenreById(this.genreId).subscribe(
      (data: Genre) => {
        this.genre = data;
      });
    }
  }

  save() {
    this.genreService.saveGenre(this.genre);
    this.backClicked();
  }

  backClicked() {
    this.router.navigate(['genres/']);
  }
}
