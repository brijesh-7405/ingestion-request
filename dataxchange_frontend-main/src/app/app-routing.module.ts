import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DdfRequestComponent } from './pages/ddf-request/ddf-request.component';
import { HomeComponent } from './pages/home/home.component';
import { ViewRequestComponent } from './pages/view-request/view-request.component';
import { RequestListComponent } from './pages/request-list/request-list.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: HomeComponent,
  },
  {
    path: 'ddf-request-form',
    data: { title: 'DDF Request' },
    component: DdfRequestComponent
  },
  {
    path: 'ddf-request-form/:requestId',
    data: { title: 'DDF Request' },
    component: DdfRequestComponent
  },
  {
    path: 'fast-track-request/:requestId/:viewMode',
    data: { title: 'Fast-Track Ingestion Request' },
    component: ViewRequestComponent
  },
  {
    path: 'manage-request',
    data: { title: 'Manage Request' },
    component: RequestListComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
