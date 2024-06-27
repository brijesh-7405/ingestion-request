import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatTooltipModule } from '@angular/material/tooltip';
import { LocationStrategy, HashLocationStrategy, DatePipe } from "@angular/common";
import { CUSTOM_ELEMENTS_SCHEMA/*, APP_INITIALIZER*/ } from "@angular/core";

// import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MAT_MOMENT_DATE_ADAPTER_OPTIONS, MomentDateAdapter } from '@angular/material-moment-adapter';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatStepperModule } from '@angular/material/stepper';
import { MatButtonModule } from '@angular/material/button';
import { TokenInterceptor } from "./search/services/token-auth.interceptor";
import { MatChipsModule } from '@angular/material/chips';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { CdkTableModule } from '@angular/cdk/table';
import { CdkTreeModule } from '@angular/cdk/tree';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from "@angular/material/form-field";
import { provideNativeDateAdapter } from '@angular/material/core';

import { MatExpansionModule } from '@angular/material/expansion';
import { MatRadioModule } from '@angular/material/radio';
import { MatTabsModule } from '@angular/material/tabs';
import { MatInputModule } from '@angular/material/input';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { DdfRequestComponent } from './pages/ddf-request/ddf-request.component';
import { PageHeaderComponent } from './page-header/page-header.component';
import { CommonComponentsModule } from './components/common/common.module';
import { DynamicFormModule } from './components/dynamic-form/dynamic-form.module';
import { DynamicDomainFormComponent } from './components/dynamic-domain-form/dynamic-domain-form.component';
import { PortalModule } from '@angular/cdk/portal';
import { MatDividerModule } from '@angular/material/divider';
import { MatToolbarModule } from '@angular/material/toolbar';
import { HomeComponent } from './pages/home/home.component';
import { ViewRequestComponent } from './pages/view-request/view-request.component';
import { RequestListComponent } from './pages/request-list/request-list.component';
import { DynamicViewRequestComponent } from './components/dynamic-view-request/dynamic-view-request.component';
// import {MatFormFieldModule} from '@angular/material/form-field';
// import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown';
// import { NgxSpinnerModule } from 'ngx-spinner';

export const MY_FORMATS = {
  parse: {
    dateInput: 'DD MMM YYYY',
  },
  display: {
    dateInput: 'DD MMM YYYY',
    monthYearLabel: 'DD MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'DD MMM YYYY'
  },
};

@NgModule({
  declarations: [
    AppComponent,
    DynamicDomainFormComponent,
    DynamicViewRequestComponent,
    HeaderComponent,
    FooterComponent,
    DdfRequestComponent,
    PageHeaderComponent,
    HomeComponent,
    ViewRequestComponent,
    RequestListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatTooltipModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatDatepickerModule,
    MatStepperModule,
    MatButtonModule,
    MatChipsModule,
    CdkTableModule,
    CdkTreeModule,
    DragDropModule,
    MatAutocompleteModule,
    ScrollingModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    MatTabsModule,
    MatIconModule,
    MatMenuModule,
    MatRadioModule,
    CommonComponentsModule,
    DynamicFormModule,
    PortalModule,
    MatDividerModule,
    MatIconModule,
    MatToolbarModule,
    MatExpansionModule
  ],
  providers: [{
    provide: DateAdapter,
    useClass: MomentDateAdapter,
    deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
  },
  { provide: LocationStrategy, useClass: HashLocationStrategy }, DatePipe,
  { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },

  { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }, provideNativeDateAdapter(), provideAnimationsAsync()],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
