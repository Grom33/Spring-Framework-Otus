import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Genre } from '../../shared/entity/genre';
import { GenreService } from '../../shared/service/genre.service';

@Component({
  selector: 'app-genre-detail',
  templateUrl: './genre-detail.component.html',
  styleUrls: ['./genre-detail.component.css']
})
export class GenreDetailComponent implements OnInit {
  public genre: Genre;
  private genreId: string;

  constructor(private genreService: GenreService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.genreId = this.route.snapshot.paramMap.get('genreId');
    this.genreService.getGenreById(this.genreId).subscribe(
      (data: Genre) => {
        this.genre = data;
      });
  }

  remove() {
    this.genreService.remove(this.genreId);
    this.backClicked();
  }

  backClicked() {
    this.router.navigate(['genres/']);
  }

}
