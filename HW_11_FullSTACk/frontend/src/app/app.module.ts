import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { BookItemComponent } from './book/book-item/book-item.component';
import { BookDetailComponent } from './book/book-detail/book-detail.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BookService } from './shared/service/book.service';
import { AuthorComponent } from './author/author.component';
import { GenreComponent } from './genre/genre.component';
import { GenreDetailComponent } from './genre/genre-detail/genre-detail.component';
import { AuthorDetailComponent } from './author/author-detail/author-detail.component';
import { AuthorItemComponent } from './author/author-item/author-item.component';
import { AuthorService } from './shared/service/author.service';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { GenreItemComponent } from './genre/genre-item/genre-item.component';
import { GenreService } from './shared/service/genre.service';
import { AuthotEditComponent } from './author/authot-edit/authot-edit.component';
import { GenreEditComponent } from './genre/genre-edit/genre-edit.component';
import { BookEditComponent } from './book/book-edit/book-edit.component';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    FooterComponent,
    BookItemComponent,
    BookDetailComponent,
    AuthorComponent,
    GenreComponent,
    GenreDetailComponent,
    AuthorDetailComponent,
    AuthorItemComponent,
    GenreItemComponent,
    AuthotEditComponent,
    GenreEditComponent,
    BookEditComponent
  ],
  imports: [
    RouterModule.forRoot([
      { path: '', component: HomeComponent },
      { path: 'books/:bookId', component: BookDetailComponent },
      { path: 'books/:bookId/edit', component: BookEditComponent },
      { path: 'authors', component: AuthorComponent },
      { path: 'authors/:authorId', component: AuthorDetailComponent },
      { path: 'authors/:authorId/edit', component: AuthotEditComponent },
      { path: 'genres', component: GenreComponent },
      { path: 'genres/:genreId', component: GenreDetailComponent },
      { path: 'genres/:genreId/edit', component: GenreEditComponent }
    ]),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule
  ],
  providers: [BookService, AuthorService, GenreService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
