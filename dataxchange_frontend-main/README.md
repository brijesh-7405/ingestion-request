# DdfIngestionForm

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 17.1.1.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.

---

The global font zoom is responsive. It can be set in `src/styles.scss` (the font size of the HTML tag).

1. Pages:
  - DDF Request Form: `http://localhost:4200/#/ddf-request-form`
      - The form controls are based on JSON data in `src/dynamic-form-controls/create-ingestion-controls.ts`
  
  - Request List: `http://localhost:4200/#/manage-request`

  - View or Review request: `http://localhost:4200/#/fast-track-request/1234144/view`, `http://localhost:4200/#/fast-track-request/1234144/review`