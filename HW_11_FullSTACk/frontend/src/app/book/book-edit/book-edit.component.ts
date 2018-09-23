import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Book } from '../../shared/entity/book';
import { BookService } from '../../shared/service/book.service';
import { AuthorService } from '../../shared/service/author.service';
import { Author } from '../../shared/entity/author';
import { GenreService } from '../../shared/service/genre.service';
import { Genre } from '../../shared/entity/genre';
import { FormGroup, FormBuilder, FormControl, ReactiveFormsModule } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.css']
})

export class BookEditComponent implements OnInit {
  private book: Book;
  private bookId: string;
  private authors: Author[];
  private genres: Genre[];
  public authorItems: AuthorItm[];
  public genreItems: GenreItm[];
  private selectedGenre: object;
  public bookForm: FormGroup;
  public genreControl: FormControl;
  public authorControl: FormControl;
  public nameControl: FormControl;
  public descriptionControl: FormControl;

  constructor(
    private bookService: BookService,
    private genreService: GenreService,
    private authorService: AuthorService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private _location: Location) { }

  ngOnInit() {
    this.bookForm = new FormGroup({
      nameControl: new FormControl(''),
      descriptionControl: new FormControl(''),
      genreControl: new FormControl(''),
      authorControl: new FormControl('')
    });

    this.bookId = this.route.snapshot.paramMap.get('bookId');

    if (Number(this.bookId) === 0) {
      this.book = new Book();
    } else {
      this.bookService.getBookById(this.bookId).subscribe(
        (data: Book) => {
          this.book = data;
          this.bookForm.controls['nameControl'].patchValue(this.book.name);
          this.bookForm.controls['descriptionControl'].patchValue(this.book.description);
          this.loadAuthors();
          this.loadGenres();
        });
    }
  }

  save() {
    this.bookService.saveBook(this.prepareBook());
  }

  backClicked() {
    this.router.navigate(['/']);
  }

  prepareBook() {
    this.book.name = this.bookForm.controls['nameControl'].value;
    this.book.description = this.bookForm.controls['descriptionControl'].value;
    this.book.authors = [];
    const authorControlList = this.bookForm.controls['authorControl'].value;

    authorControlList.forEach(function (element) {
      let authorFromArray: Author;
      authorFromArray = this.getItemFromArray(this.authors, element);
      if (authorFromArray != null) {
        this.book.authors.push(authorFromArray);
      }
    }, this);

    this.book.genres = [];
    const genreControlList = this.bookForm.controls['genreControl'].value;
    genreControlList.forEach(function (element) {
      let genreFromArray: Genre;
      genreFromArray = this.getItemFromArray(this.genres, element);
      if (genreFromArray != null) {
        this.book.genres.push(genreFromArray);
      }
    }, this);
    return this.book;
  }

  getItemFromArray(array: any[], id: string) {
    let authorFromArray: Author;
    array.forEach(
      function (element) {
        if (element.id == id) {
          authorFromArray = element;
        }
      }, this);
    return authorFromArray;
  }

  loadAuthors() {
    this.authorService.getAuthors().subscribe(
      (data: Author[]) => {
        this.authors = data;
        this.authorItems = [];
        this.makeAuthorItems();
      });
    this.bookForm.controls['authorControl'].patchValue(this.getIdFromArrayElements(this.book.authors));
  }

  loadGenres() {
    this.genreService.getGenres().subscribe(
      (data: Genre[]) => {
        this.genres = data;
        this.selectedGenre = null;
        this.genreItems = [];
        this.makeGenreItems();
      });
    this.bookForm.controls['genreControl'].patchValue(this.getIdFromArrayElements(this.book.genres));
  }

  getIdFromArrayElements(array: any[]) {
    const idArray = [];
    array.forEach(
      function (element) {
        idArray.push(element.id);
      }, this);
    return idArray;
  }

  makeAuthorItems() {
    this.authors.forEach(function (element) {
      const aut = new AuthorItm();
      aut.author = element;
      if (this.book.authors.some(el => el.id === element.id)) {
        aut.selected = 'selected';
      } else {
        aut.selected = '';
      }
      this.authorItems.push(aut);
    }, this);
  }

  makeGenreItems() {
    this.genres.forEach(function (element) {
      const gen = new GenreItm();
      gen.genre = element;
      if (this.book.genres.some(el => el.id === element.id)) {
        gen.selected = 'selected';
      } else {
        gen.selected = '';
      }
      this.genreItems.push(gen);
    }, this);
  }
}

class AuthorItm {
  author: Author;
  selected: string;
  constructor() { }
}

class GenreItm {
  genre: Genre;
  selected: string;
  constructor() { }
}
