import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'ddf-ingestion-form';
  headerBreadcrumbs = [
    { label: 'Home', url: '/' },
    { label: '-' }
  ]
}
